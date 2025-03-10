package dev.amot.endshards.mixin;

import dev.amot.endshards.util.AlwaysTruePredicate;
import dev.amot.endshards.util.FollowPlayerGoal;
import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.ThrallTargetPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(MobEntity.class)
public abstract class ThrallMixin implements IThrall {
    @Shadow @Final protected GoalSelector goalSelector;
    @Shadow @Final protected GoalSelector targetSelector;

    @Unique private UUID thrallOwnerUUID = Util.NIL_UUID;
    @Unique private boolean isThrall = false;

    @Unique
    public boolean endshards$isThrall() {
        return this.isThrall;
    }

    @Unique
    public UUID endshards$getThrallOwnerUUID() {
        return this.thrallOwnerUUID;
    }

    @Unique
    public void endshards$assignOwner(PlayerEntity thrallOwner) {
        this.thrallOwnerUUID = thrallOwner.getUuid();

        // Set targeting goals
        this.endshards$clearActiveTarget();
        this.targetSelector.clear(new AlwaysTruePredicate<>());

        MobEntity thisThrall = (MobEntity)(Object)this;
        this.targetSelector.add(
                2,
                new ActiveTargetGoal<>(
                        thisThrall,
                        LivingEntity.class,
                        true,
                        new ThrallTargetPredicate(
                                thisThrall,
                                thrallOwner,
                                ThrallTargetPredicate.TargetMode.SELF_DEFENSE
                        )
                )
        );
        this.targetSelector.add(
                3,
                new ActiveTargetGoal<>(
                        thisThrall,
                        LivingEntity.class,
                        true,
                        new ThrallTargetPredicate(
                                thisThrall,
                                thrallOwner,
                                ThrallTargetPredicate.TargetMode.DEFENSE
                        )
                )
        );
        this.targetSelector.add(
                4,
                new ActiveTargetGoal<>(
                        thisThrall,
                        LivingEntity.class,
                        true,
                        new ThrallTargetPredicate(
                                thisThrall,
                                thrallOwner,
                                ThrallTargetPredicate.TargetMode.OFFENSE
                        )
                )
        );
        this.goalSelector.add(
                1,
                new FollowPlayerGoal(
                        thisThrall,
                        thrallOwner,
                        1.0D,
                        10.0F,
                        4.0F
                )
        );
    }

    @Unique
    public void endshards$convertToThrall() {
        this.isThrall = true;

        // Clear targeting goals
        this.endshards$clearActiveTarget();
        this.targetSelector.clear(new AlwaysTruePredicate<>());

        // Clear other goals
        removeGoal(LookAtEntityGoal.class);
        removeGoal(WanderAroundGoal.class);
        removeGoal(WanderAroundFarGoal.class);

        // Zombie-type mobs
        removeGoal(StepAndDestroyBlockGoal.class);
        removeGoal(MoveThroughVillageGoal.class);

        // Get rid of zombie reinforcements
        if ((MobEntity)(Object)this instanceof ZombieEntity zombie) {
            EntityAttributeInstance reinforcements = zombie.getAttributeInstance(EntityAttributes.SPAWN_REINFORCEMENTS);
            if (reinforcements != null) reinforcements.setBaseValue(0.0);
        }
    }

    @Unique
    private void removeGoal(Class<? extends Goal> goalToRemove) {
        this.goalSelector.getGoals().removeIf(goal -> goalToRemove.isInstance(goal.getGoal()));
    }

    @Unique
    public void endshards$clearActiveTarget() {
        for (PrioritizedGoal prioritizedGoal : this.targetSelector.getGoals()) prioritizedGoal.stop();
        ((MobEntity)(Object)this).setTarget(null);
    }

    //TODO: Find a way to clear invalid targets without doing all these checks every tick
    @Inject(method = "baseTick", at = @At(value = "RETURN"))
    public void clearInvalidTarget(CallbackInfo ci) {
        // Return if mob is not a thrall
        if (!this.endshards$isThrall()) return;

        // Return if thrall does not have a target
        LivingEntity target = ((MobEntity)(Object)this).getTarget();
        if (target == null) return;

        // Find owner (can be null)
        PlayerEntity owner = target.getWorld().getPlayerByUuid(thrallOwnerUUID);
        PlayerEntity enemyPlayer = null;

        // Find enemy player (targeted player or owner of targeted thrall)
        if (target instanceof MobEntity targetMob) {
            if (((IThrall)targetMob).endshards$isThrall()) {
                enemyPlayer = targetMob.getWorld().getPlayerByUuid(((IThrall)targetMob).endshards$getThrallOwnerUUID());
            }
        }
        else if (target instanceof PlayerEntity targetPlayer) enemyPlayer = targetPlayer;

        // If not dealing with a player, all targets are valid
        if (owner == null) return;
        if (enemyPlayer == null) return;

        // If enemy player is owner, or enemy player is not attacking/attacked, clear target
        if (Objects.equals(enemyPlayer, owner)) this.endshards$clearActiveTarget();
        if (!Objects.equals(enemyPlayer.getAttacking(), owner) && !Objects.equals(owner.getAttacking(), enemyPlayer))
            this.endshards$clearActiveTarget();
    }
}
