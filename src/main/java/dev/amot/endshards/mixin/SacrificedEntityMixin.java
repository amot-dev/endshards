package dev.amot.endshards.mixin;

import dev.amot.endshards.util.ISacrificedEntity;
import dev.amot.endshards.util.ISacrificingPlayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class SacrificedEntityMixin implements ISacrificedEntity {

    @Unique private ServerPlayerEntity sacrificingPlayer = null;

    @Unique
    public void endshards$setSacrificingPlayer(ServerPlayerEntity serverPlayer) {
        sacrificingPlayer = serverPlayer;
    }

    @Inject(method = "onDeath", at = @At("RETURN"))
    public void incrementSacrificeCount(DamageSource damageSource, CallbackInfo ci) {
        // If entity has a sacrificing player and died from fire, count is as a sacrifice
        if (this.sacrificingPlayer != null && damageSource.isIn(DamageTypeTags.IS_FIRE)) {
            ((ISacrificingPlayer)this.sacrificingPlayer).incrementSacrificeCount();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void removeSacrificingPlayer(CallbackInfo ci) {
        // Once entity with sacrificing player is no longer on fire, unset sacrificing player
        if (this.sacrificingPlayer != null && !((LivingEntity)(Object)this).isOnFire()) {
            sacrificingPlayer = null;
        }
    }
}
