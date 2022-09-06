package dev.amot.endshards.mixin;

import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.util.ISacrificingPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class SacrificingPlayerMixin implements ISacrificingPlayer {
    @Unique private int sacrificeCount = 0;

    @Unique
    public void incrementSacrificeCount() {
        sacrificeCount++;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void giveSacrificeAdvancement(CallbackInfo ci) {
        // If sacrificing player still has cooldown, check if count is met
        if (((ServerPlayerEntity)(Object)this).getActiveStatusEffects().containsKey(NetheriteGear.NETHERITE_COOLDOWN)) {
            if (sacrificeCount > 99) {
                EndShardsCriteria.NETHERITE_SWORD_SACRIFICE_CRITERION.trigger((ServerPlayerEntity)(Object)this);
                sacrificeCount = 0;
            }
        }
        // If cooldown is gone, reset count. This assumes cooldown_duration > fire_duration (which it is)
        else {
            sacrificeCount = 0;
        }
    }
}
