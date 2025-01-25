package dev.amot.endshards.mixin;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.util.ISacrificingPlayer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
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
    public void endshards$incrementSacrificeCount() {
        sacrificeCount++;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void giveSacrificeAdvancement(CallbackInfo ci) {
        // If sacrificing player still has cooldown, check if count is met
        RegistryEntry<StatusEffect> netheriteCooldownEntry = Registries.STATUS_EFFECT.getEntry(NetheriteGear.NETHERITE_COOLDOWN);
        if (((ServerPlayerEntity)(Object)this).getActiveStatusEffects().containsKey(netheriteCooldownEntry)) {
            if (sacrificeCount > 99) {
                EndshardsCriteria.NETHERITE_SWORD_SACRIFICE_CRITERION.trigger((ServerPlayerEntity)(Object)this);
                sacrificeCount = 0;
            }
        }
        // If cooldown is gone, reset count. This assumes cooldown_duration > fire_duration (which it is and always should be)
        else {
            sacrificeCount = 0;
        }
    }
}
