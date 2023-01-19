package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.IThrallOwner;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.world.entity.EntityLike;
import net.minecraft.world.entity.EntityLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.Stream;

@Mixin(ServerEntityManager.class)
public abstract class LostThrallMixin<T extends EntityLike> {
    @Shadow
    public abstract EntityLookup<T> getLookup();

    @Inject(method = "loadEntities", at = @At(value = "HEAD"))
    void checkForLostThrall(Stream<T> entities, CallbackInfo ci) {
        entities.forEach((entity) -> {
            if (entity instanceof MobEntity mob && ((IThrall)mob).isThrall()) {
                EntityLike entityLikeOwner = this.getLookup().get(((IThrall)mob).getThrallOwnerUUID());
                if (entityLikeOwner instanceof PlayerEntity owner) {
                    ((IThrallOwner)owner).findLostThrall(mob);
                }
            }
        });
    }
}
