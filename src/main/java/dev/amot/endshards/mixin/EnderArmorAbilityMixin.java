package dev.amot.endshards.mixin;

import dev.amot.endshards.EnderItems;
import dev.amot.endshards.armor.EnderArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class EnderArmorAbilityMixin {
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void injectDamageMethod(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isFromFalling()){
            int ender_armor_equipped_count = 0;
            for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
                if (equipmentSlot.getType() == EquipmentSlot.Type.ARMOR) {
                    if (this.getEquippedStack(equipmentSlot).getItem().getClass() == EnderArmorItem.class) {
                        ender_armor_equipped_count++;
                    }
                }
            }
            if (ender_armor_equipped_count == 4) {
                if (!this.activeStatusEffects.containsKey(EnderItems.ENDER_COOLDOWN)) {
                    this.addStatusEffect(new StatusEffectInstance(EnderItems.ENDER_COOLDOWN, 600, 0, false, false, true));
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
