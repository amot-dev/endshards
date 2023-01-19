package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.IThrallOwner;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class ThrallOwnerMixin implements IThrallOwner {
    @Unique
    private List<UUID> thrallUUIDs = new LinkedList<>();
    private List<UUID> lostThrallUUIDs = new LinkedList<>();

    @Unique
    public boolean addThrall(MobEntity thrall) {
        if (((IThrall)thrall).isThrall()) return false;

        ((IThrall)thrall).convertToThrall();
        ((IThrall)thrall).assignOwner((PlayerEntity)(Object)this);
        this.thrallUUIDs.add(thrall.getUuid());

        thrall.setGlowing(true);//debug

        // Clear targeting of all thralls to prevent thralls attacking each other (temp measure)
        if (((PlayerEntity)(Object)this).getWorld() instanceof ServerWorld serverWorld) {
            for (UUID ownedThrallUUID : this.thrallUUIDs) {
                ((IThrall)Objects.requireNonNull(serverWorld.getEntity(ownedThrallUUID))).clearActiveTarget();
            }
        }
        return true;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "RETURN"))
    public void writeThrallDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        // Ensure this is a Thrall Owner
        if (!thrallUUIDs.isEmpty()) {
            // Make NbtList of OwnedThralls
            NbtList thrallsNbt = new NbtList();
            NbtCompound thrallNbt;
            // Add each Thrall to NbtList
            for (UUID thrallUUID : thrallUUIDs) {
                thrallNbt = new NbtCompound();
                thrallNbt.putUuid("Thrall", thrallUUID);
                thrallsNbt.add(thrallNbt);
            }
            // Save Nbt
            nbt.put("OwnedThralls", thrallsNbt);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "RETURN"))
    public void readThrallDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        // Get NbtList of OwnedThralls
        NbtList thrallsNbt = nbt.getList("OwnedThralls", 9);
        for (NbtElement nbtElement : thrallsNbt) {
            UUID thrallUUID = UUID.fromString(nbtElement.asString());
            // Search World for thrall matching UUID and reinstate Thrall
            if (((PlayerEntity)(Object)this).getWorld() instanceof ServerWorld serverWorld) {

                this.thrallUUIDs.add(thrallUUID);
                IThrall thrall = (IThrall)serverWorld.getEntity(thrallUUID);
                // Thrall is unloaded
                if (thrall == null) lostThrallUUIDs.add(thrallUUID);
                // Thrall is loaded
                else thrall.assignOwner(((PlayerEntity)(Object)this));
            }
        }
    }

    @Unique
    public void findLostThrall(MobEntity potentialThrall) {
        if (lostThrallUUIDs.contains(potentialThrall.getUuid())) {
            ((IThrall)potentialThrall).assignOwner(((PlayerEntity)(Object)this));
            lostThrallUUIDs.remove(potentialThrall.getUuid());
        }
    }

    @Unique
    public int getThrallCount() {
        if (((PlayerEntity)(Object)this).getWorld() instanceof ServerWorld serverWorld) {
            // Remove dead thralls before taking count
            this.thrallUUIDs.removeIf(ownedThrallUUID -> !Objects.requireNonNull(serverWorld.getEntity(ownedThrallUUID)).isAlive());
            return thrallUUIDs.size();
        }
        return -1;
    }
}
