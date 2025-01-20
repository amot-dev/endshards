package dev.amot.endshards.mixin;

import dev.amot.endshards.armor.SculkArmorItem;
import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.armor.NetheriteArmorItem;
import dev.amot.endshards.items.SculkGear;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class ArmorAbilityMixin {
    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Shadow protected abstract float modifyAppliedDamage(DamageSource source, float amount);
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Unique
    boolean totemUsed = false;

    @Unique
    int getArmorCount(LivingEntity livingEntity, Class<?> armorItemClass) {
        int armorCount = 0;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
            if (equipmentSlot.getType() == EquipmentSlot.Type.ARMOR) {
                if (livingEntity.getEquippedStack(equipmentSlot).getItem().getClass() == armorItemClass) {
                    armorCount++;
                }
            }
        }
        return armorCount;
    }

    //TODO: Find better way to handle damage (I don't like this injection point needed in 1.19)
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void doEnderArmorAbility(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity)(Object)this).isInvulnerableTo(source)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
        else if (((LivingEntity)(Object)this).world.isClient) {
            cir.setReturnValue(false);
            cir.cancel();
        }
        else if (((LivingEntity)(Object)this).isDead()) {
            cir.setReturnValue(false);
            cir.cancel();
        }

        // Handle fall damage with full Ender Armor
        if (source.isFromFalling() && getArmorCount((LivingEntity)(Object)this, EnderArmorItem.class) == 4) {
            // Ability is good!
            if (!this.activeStatusEffects.containsKey(EnderGear.ENDER_COOLDOWN)) {
                this.addStatusEffect(new StatusEffectInstance(
                        EnderGear.ENDER_COOLDOWN, EnderGear.ENDER_COOLDOWN_DURATION_ARMOR, 0, false, false, true)
                );

                float totalDamage = this.modifyAppliedDamage(source, amount);
                LivingEntity thisEntity = (LivingEntity) (Object) this;
                if (totalDamage >= thisEntity.getHealth() && thisEntity instanceof ServerPlayerEntity serverPlayer) {
                    EndshardsCriteria.ENDER_ARMOR_FALL_CRITERION.trigger(serverPlayer);
                }

                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    @Inject(method = "tryUseTotem", at = @At("RETURN"))
    public void enderArmorPlayedSelfTotemValidator(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        totemUsed = cir.getReturnValue();
    }

    @Inject(method = "damage", at = @At("RETURN"))
    public void enderArmorPlayedSelfTrigger(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean enderArmorAbilityValid = (getArmorCount((LivingEntity)(Object)this, EnderArmorItem.class) == 4);
        // Local variable will be destroyed when out of scope
        boolean totemUsedLocal = totemUsed;
        totemUsed = false;

        // Want to trigger Played Self Criterion even if totem is used
        if (enderArmorAbilityValid && (cir.getReturnValue() || totemUsedLocal) && source.isFromFalling()) {
            // Only trigger if Ender Cooldown has been active for a second or less
            if (this.activeStatusEffects.containsKey(EnderGear.ENDER_COOLDOWN)) {
                if (this.activeStatusEffects.get(EnderGear.ENDER_COOLDOWN).getDuration() >= EnderGear.ENDER_COOLDOWN_DURATION_SWORD - 20) {
                    float totalDamage = this.modifyAppliedDamage(source, amount);
                    LivingEntity thisEntity = (LivingEntity)(Object)this;
                    // Make sure lethal damage was taken
                    if ((totalDamage >= thisEntity.getHealth() || totemUsedLocal) && thisEntity instanceof ServerPlayerEntity serverPlayer) {
                        EndshardsCriteria.ENDER_ARMOR_PLAYED_SELF_CRITERION.trigger(serverPlayer);
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("RETURN"))
    public void doNetheriteArmorAbility(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && !source.isFromFalling() && getArmorCount((LivingEntity)(Object)this, NetheriteArmorItem.class) == 4) {
            if (!this.activeStatusEffects.containsKey(NetheriteGear.NETHERITE_COOLDOWN)) {
                // Only trigger ability if health is going below half
                if (this.getHealth() <= this.getMaxHealth()/2) {
                    this.addStatusEffect(new StatusEffectInstance(
                            NetheriteGear.NETHERITE_COOLDOWN, NetheriteGear.NETHERITE_COOLDOWN_DURATION_ARMOR, 0, false, false, true)
                    );
                    this.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.RESISTANCE, NetheriteArmorItem.abilityDuration, NetheriteArmorItem.abilityPower, false, true, true)
                    );

                    if ((LivingEntity)(Object)this instanceof ServerPlayerEntity serverPlayer) {
                        EndshardsCriteria.NETHERITE_ARMOR_PROTECT_CRITERION.trigger(serverPlayer);
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void doSculkArmorAbility(CallbackInfo ci) {
        LivingEntity thisEntity = (LivingEntity)(Object)this;
        if (getArmorCount(thisEntity, SculkArmorItem.class) == 4) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20, 0, false, false, true));
            if (thisEntity instanceof ServerPlayerEntity serverPlayer) {
                EndshardsCriteria.SCULK_ARMOR_LIGHT.trigger(serverPlayer);
            }
        }
    }

    @Inject(method = "sendEquipmentBreakStatus", at = @At("HEAD"))
    public void triggerSculkToolMendingBreakAdvancement(EquipmentSlot slot, CallbackInfo ci) {
        LivingEntity thisEntity = (LivingEntity)(Object)this;
        if (thisEntity instanceof ServerPlayerEntity serverPlayer && this.getEquippedStack(slot).getItem() instanceof ToolItem toolInHand) {
            if (toolInHand.getMaterial() == SculkGear.SCULK_TOOL_MATERIAL && !(toolInHand instanceof SwordItem)) {
                if (EnchantmentHelper.getLevel(Enchantments.MENDING, this.getEquippedStack(slot)) == 1) {
                    EndshardsCriteria.SCULK_TOOL_MENDING_BREAK.trigger(serverPlayer);
                }
            }
        }
    }
}
