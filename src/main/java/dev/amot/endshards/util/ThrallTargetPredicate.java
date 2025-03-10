package dev.amot.endshards.util;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Objects;

public class ThrallTargetPredicate implements TargetPredicate.EntityPredicate {
    public enum TargetMode {
        SELF_DEFENSE,
        DEFENSE,
        OFFENSE
    }
    MobEntity thrall;
    PlayerEntity thrallOwner;
    TargetMode targetMode;

    public ThrallTargetPredicate(MobEntity thrall, PlayerEntity thrallOwner, TargetMode targetMode) {
        this.thrall = thrall;
        this.thrallOwner = thrallOwner;
        this.targetMode = targetMode;
    }

    @Override
    public boolean test(net.minecraft.entity.LivingEntity targetEntity, ServerWorld world) {
        // Do not attack creepers unless gamerule set
        if (targetEntity instanceof CreeperEntity) {
            if (!world.getGameRules().getBoolean(EndshardsGameRules.THRALLS_ATTACK_CREEPERS)) return false;
        }

        // Assign target based on mode
        if (this.targetMode == TargetMode.SELF_DEFENSE) {
            if (targetEntity instanceof MobEntity targetMob)
                return Objects.equals(targetMob.getTarget(), this.thrall);
            else if (targetEntity instanceof PlayerEntity targetPlayer)
                return Objects.equals(targetPlayer.getAttacking(), this.thrall);
            else return false;
        }
        else if (this.targetMode == TargetMode.DEFENSE) {
            if (targetEntity instanceof MobEntity targetMob)
                return Objects.equals(targetMob.getTarget(), this.thrallOwner);
            else if (targetEntity instanceof PlayerEntity targetPlayer)
                return Objects.equals(targetPlayer.getAttacking(), this.thrallOwner);
            else return false;
        }
        else if (this.targetMode == TargetMode.OFFENSE) {
            return Objects.equals(this.thrallOwner.getAttacking(), targetEntity);
        }
        return false;
    }
}
