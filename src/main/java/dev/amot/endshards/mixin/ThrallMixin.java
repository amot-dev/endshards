package dev.amot.endshards.mixin;

import dev.amot.endshards.util.FollowPlayerGoal;
import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.ThrallTargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(MobEntity.class)
public abstract class ThrallMixin implements IThrall {
    @Shadow @Final protected GoalSelector goalSelector;
    @Shadow @Final protected GoalSelector targetSelector;

    @Unique private UUID thrallOwnerUUID = null;
    @Unique private boolean isThrall = false;

    @Unique
    public void makeThrallFor(PlayerEntity thrallOwner) {
        this.isThrall = true;
        this.thrallOwnerUUID = thrallOwner.getUuid();

        // Clear targeting goals and put in thrall goals
        this.clearActiveTarget();
        this.targetSelector.clear();
        this.goalSelector.remove(new LookAtEntityGoal((MobEntity)(Object)this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(3, new ActiveTargetGoal<>((MobEntity)(Object)this, MobEntity.class, true, new ThrallTargetPredicate<>(thrallOwner)));
        this.goalSelector.add(4, new FollowPlayerGoal((MobEntity)(Object)this, thrallOwner, 1.0D, 3.0F, 32.0F));
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "RETURN"))
    public void writeThrallDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        // If this is a thrall, save the Thrall Owner Nbt
        if (thrallOwnerUUID != null) {
            nbt.putUuid("ThrallOwner", thrallOwnerUUID);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "RETURN"))
    public void readThrallDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        // If mob has ThrallOwner nbt, it must be a thrall
        if (nbt.containsUuid("ThrallOwner")) {
            this.thrallOwnerUUID = nbt.getUuid("ThrallOwner");
            this.isThrall = true;
        }
    }

    @Unique
    public void clearActiveTarget() {
        for (PrioritizedGoal prioritizedGoal : this.targetSelector.getGoals()) prioritizedGoal.stop();
    }

    @Unique
    public boolean isThrall() {
        return this.isThrall;
    }

    @Unique
    public UUID getThrallOwnerUUID() {
        return this.thrallOwnerUUID;
    }
}
