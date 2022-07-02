package dev.amot.endshards.util;

import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Objects;
import java.util.function.Predicate;

public class ThrallTargetPredicate<LivingEntity> implements Predicate<LivingEntity> {
    PlayerEntity thrallOwner;

    public ThrallTargetPredicate(PlayerEntity thrallOwner) {
        this.thrallOwner = thrallOwner;
    }
    @Override
    public boolean test(LivingEntity targetEntity) {
        if (targetEntity instanceof MobEntity targetMob) {
            // Return true if mob is targeting or player
            if (Objects.equals(targetMob.getTarget(), this.thrallOwner)) {
                // Don't attack creepers unless enabled
                if (targetMob instanceof CreeperEntity) {
                    return targetMob.world.getGameRules().getBoolean(EndShardsGameRules.THRALLS_ATTACK_CREEPERS);
                }
                else return true;
            }
        }
        return false;
    }
}
