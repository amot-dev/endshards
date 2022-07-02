package dev.amot.endshards.mixin;

import dev.amot.endshards.util.FollowPlayerGoal;
import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.ThrallTargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MobEntity.class)
public abstract class ThrallMixin implements IThrall {
    @Shadow @Final protected GoalSelector goalSelector;
    @Shadow @Final protected GoalSelector targetSelector;

    @Unique private PlayerEntity owner = null;
    @Unique private boolean isThrall = false;

    @Unique
    public void makeThrallFor(PlayerEntity owner) {
        this.isThrall = true;
        this.owner = owner;

        // Clear targeting goals and put in thrall goals
        this.clearActiveTarget();
        this.targetSelector.clear();
        this.targetSelector.add(3, new ActiveTargetGoal<>((MobEntity)(Object)this, MobEntity.class, true, new ThrallTargetPredicate<>(this.owner)));
        this.goalSelector.add(4, new FollowPlayerGoal((MobEntity)(Object)this, owner, 1.0D, 3.0F, 32.0F));
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
    public PlayerEntity getOwner() {
        return this.owner;
    }
}
