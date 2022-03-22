package dev.amot.endshards.mixin;

import dev.amot.endshards.armor.EnderArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EnderArmorAbilityMixin {
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void injectDamageMethod(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isFromFalling()){
            int ender_armor_equipped_count = 0;
            for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
                if (this.getEquippedStack(equipmentSlot).getItem().getClass() == EnderArmorItem.class) {
                    ender_armor_equipped_count++;
                }
            }
            if (ender_armor_equipped_count == 4) cir.setReturnValue(false);
        }
    }
}
