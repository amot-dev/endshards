package dev.amot.endshards.mixin;

import dev.amot.endshards.EndShards;
import dev.amot.endshards.EnderItems;
import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import dev.amot.endshards.armor.EnderArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
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
    @Shadow protected abstract float modifyAppliedDamage(DamageSource source, float amount);

    private int enderArmorEquippedCount = 0;

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void injectDamageMethodHead(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.isFromFalling()){
            enderArmorEquippedCount = 0;
            for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
                if (equipmentSlot.getType() == EquipmentSlot.Type.ARMOR) {
                    if (this.getEquippedStack(equipmentSlot).getItem().getClass() == EnderArmorItem.class) {
                        enderArmorEquippedCount++;
                    }
                }
            }
            if (enderArmorEquippedCount == 4) {
                if (!this.activeStatusEffects.containsKey(EnderItems.ENDER_COOLDOWN)) {
                    this.addStatusEffect(new StatusEffectInstance(
                            EnderItems.ENDER_COOLDOWN, EnderItems.ENDER_COOLDOWN_DURATION_ARMOR, 0, false, false, true)
                    );

                    float totalDamage = this.modifyAppliedDamage(source, amount);
                    LivingEntity thisEntity = (LivingEntity)(Object)this;
                    if (totalDamage >= thisEntity.getHealth() && thisEntity instanceof ServerPlayerEntity serverPlayer){
                        EndShardsCriteria.ENDER_ARMOR_ABILITY_USED_CRITICAL_DAMAGE.trigger(serverPlayer);
                    }

                    cir.setReturnValue(false);
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("RETURN"), cancellable = true)
    public void injectDamageMethodReturn(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (cir.getReturnValue() && source.isFromFalling() && enderArmorEquippedCount == 4){
            if (this.activeStatusEffects.containsKey(EnderItems.ENDER_COOLDOWN)){
                if (this.activeStatusEffects.get(EnderItems.ENDER_COOLDOWN).getDuration() >= EnderItems.ENDER_COOLDOWN_DURATION_SWORD - 20) {
                    float totalDamage = this.modifyAppliedDamage(source, amount);
                    LivingEntity thisEntity = (LivingEntity) (Object) this;
                    if (totalDamage >= thisEntity.getHealth() && thisEntity instanceof ServerPlayerEntity serverPlayer) {
                        EndShardsCriteria.ENDER_COOLDOWN_FAIL.trigger(serverPlayer);
                    }
                }
            }
        }
    }
}
