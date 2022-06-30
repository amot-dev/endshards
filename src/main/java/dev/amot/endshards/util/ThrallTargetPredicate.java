package dev.amot.endshards.util;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class ThrallTargetPredicate<LivingEntity> implements Predicate<LivingEntity> {
    PlayerEntity thrallOwner;

    public ThrallTargetPredicate(PlayerEntity thrallOwner) {
        this.thrallOwner = thrallOwner;
    }
    @Override
    public boolean test(LivingEntity targetEntity) {
        if (targetEntity instanceof MobEntity targetMob) {
            // Return true if mob is targeting or target of player
            if (((IMobEntity)targetMob).isThrallOf(this.thrallOwner)) return false;
            if (targetMob.getTarget() == null) return false;
            if (targetMob.getTarget().equals(this.thrallOwner)) return true;
            if (this.thrallOwner.getAttacking() == null) return false;
            return this.thrallOwner.getAttacking().equals(targetMob);
        }
        else return true;
    }
}
