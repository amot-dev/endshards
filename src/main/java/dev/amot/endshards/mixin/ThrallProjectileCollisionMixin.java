package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IThrall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileEntity.class)
public class ThrallProjectileCollisionMixin {
    @Shadow @Nullable private Entity owner;

    @Inject(method = "canHit", at = @At(value = "RETURN"), cancellable = true)
    public void canHitThrall(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // Check if owner of projectile is a thrall
        if (this.owner != null && ((IThrall)this.owner).endshards$isThrall()) {
            IThrall shootingThrall = ((IThrall)this.owner);
            // If entity being shot is thrall owner, projectile should not hit
            if (entity.getUuid() == shootingThrall.endshards$getThrallOwnerUUID()) cir.setReturnValue(false);
            // If entity being shot is a thrall...
            else if (entity instanceof MobEntity && ((IThrall)entity).endshards$isThrall()) {
                IThrall shotThrall = ((IThrall)entity);
                // ...and both thralls have the same owner, projectile should not hit
                if (shotThrall.endshards$getThrallOwnerUUID() == shootingThrall.endshards$getThrallOwnerUUID()) cir.setReturnValue(false);
            }
        }
    }
}
