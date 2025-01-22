package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.IThrallOwner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
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

@Mixin(ServerPlayerEntity.class)
public abstract class ThrallOwnerMixin implements IThrallOwner {
    @Unique
    private List<UUID> thrallUUIDs = new LinkedList<>();

    @Unique
    public boolean endshards$addThrall(MobEntity thrall) {
        if (((IThrall)thrall).endshards$isThrall()) return false;

        ((IThrall)thrall).endshards$convertToThrall();
        ((IThrall)thrall).endshards$assignOwner((PlayerEntity)(Object)this);
        this.thrallUUIDs.add(thrall.getUuid());

        thrall.setGlowing(true);//debug

        // Clear targeting of all thralls to prevent thralls attacking each other (temp measure)
        ServerWorld serverWorld = ((ServerPlayerEntity)(Object)this).getServerWorld();
        for (UUID ownedThrallUUID : this.thrallUUIDs) {
            ((IThrall)Objects.requireNonNull(serverWorld.getEntity(ownedThrallUUID))).endshards$clearActiveTarget();
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
            nbt.put("ownedThralls", thrallsNbt);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "RETURN"))
    public void readThrallDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        // Get NbtList of OwnedThralls
        NbtList thrallsNbt = nbt.getList("ownedThralls", NbtElement.COMPOUND_TYPE);
        for (NbtElement nbtElement : thrallsNbt) {
            // Search World for thrall matching UUID and reinstate Thrall
            UUID thrallUUID = ((NbtCompound)nbtElement).getUuid("Thrall");
            ServerWorld serverWorld = ((ServerPlayerEntity)(Object)this).getServerWorld();
            IThrall thrall = (IThrall)serverWorld.getEntity(thrallUUID);

            // Only add loaded thralls; if the thrall is not loaded, sayonara baby
            if (thrall != null) this.endshards$addThrall((MobEntity)thrall);
        }
    }

    @Unique
    public int endshards$getThrallCount() {
        ServerWorld serverWorld = ((ServerPlayerEntity)(Object)this).getServerWorld();
        // Remove dead and no longer existing thralls before taking count
        this.thrallUUIDs.removeIf(ownedThrallUUID -> {
            Entity thrall = serverWorld.getEntity(ownedThrallUUID);
            return thrall == null || !thrall.isAlive();
        });
        return thrallUUIDs.size();
    }
}
