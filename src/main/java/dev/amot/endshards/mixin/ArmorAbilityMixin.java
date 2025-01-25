package dev.amot.endshards.mixin;

import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.util.IArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
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
    @Shadow @Final private Map<RegistryEntry<StatusEffect>, StatusEffectInstance> activeStatusEffects;
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Shadow protected abstract float modifyAppliedDamage(DamageSource source, float amount);
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();

    @Unique
    int getArmorCount(LivingEntity livingEntity, ArmorMaterial armorMaterial) {
        int armorCount = 0;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()){
            if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                if (livingEntity.getEquippedStack(equipmentSlot).getItem() instanceof ArmorItem armorItem
                        && ((IArmorMaterial)armorItem).endshards$getMaterial() == armorMaterial) {
                    armorCount++;
                }
            }
        }
        return armorCount;
    }

    // Inject after wakeUp call (marks end of invulnerability and dead checks, and we only care about fall damage so don't care about checking shield)
    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;wakeUp()V", shift = At.Shift.AFTER), cancellable = true)
    public void doEnderArmorAbility(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean wearingFullEnderArmor = getArmorCount((LivingEntity)(Object)this, EnderGear.ENDER_ARMOR_MATERIAL) == 4;

        // Check if fall damage was taken while wearing full Ender Armor
        if (wearingFullEnderArmor && source.isIn(DamageTypeTags.IS_FALL)) {
            // Ensure cooldown is not active
            RegistryEntry<StatusEffect> enderCooldownEntry = Registries.STATUS_EFFECT.getEntry(EnderGear.ENDER_COOLDOWN);
            if (!this.activeStatusEffects.containsKey(enderCooldownEntry)) {
                // Ability is good!
                this.addStatusEffect(new StatusEffectInstance(
                        enderCooldownEntry, EnderGear.ENDER_COOLDOWN_DURATION_ARMOR, 0, false, false, true)
                );

                float totalDamage = this.modifyAppliedDamage(source, amount);
                // Only trigger if lethal damage was taken
                if (totalDamage >= this.getHealth() && (LivingEntity)(Object)this instanceof ServerPlayerEntity serverPlayer) {
                    EndshardsCriteria.ENDER_ARMOR_FALL_CRITERION.trigger(serverPlayer);
                }

                // Cancel early as we don't care about other checks
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    @Inject(method = "damage", at = @At("RETURN"))
    public void enderArmorPlayedSelfTrigger(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean wearingFullEnderArmor = getArmorCount((LivingEntity)(Object)this, EnderGear.ENDER_ARMOR_MATERIAL) == 4;

        // Check if fall damage was taken while wearing full Ender Armor
        if (wearingFullEnderArmor && cir.getReturnValue() && source.isIn(DamageTypeTags.IS_FALL)) {
            // Ensure Ender Cooldown has been active for a second or less
            RegistryEntry<StatusEffect> enderCooldownEntry = Registries.STATUS_EFFECT.getEntry(EnderGear.ENDER_COOLDOWN);
            if (this.activeStatusEffects.containsKey(enderCooldownEntry)) {
                if (this.activeStatusEffects.get(enderCooldownEntry).getDuration() >= EnderGear.ENDER_COOLDOWN_DURATION_SWORD - 20) {
                    float totalDamage = this.modifyAppliedDamage(source, amount);
                    // Only trigger if lethal damage was taken
                    if ((totalDamage >= this.getHealth()) && (LivingEntity)(Object)this instanceof ServerPlayerEntity serverPlayer) {
                        EndshardsCriteria.ENDER_ARMOR_PLAYED_SELF_CRITERION.trigger(serverPlayer);
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("RETURN"))
    public void doNetheriteArmorAbility(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean wearingFullNetheriteArmor = getArmorCount((LivingEntity)(Object)this, ArmorMaterials.NETHERITE) == 4;

        // Check if non-fall damage was taken while wearing full Netherite Armor
        if (wearingFullNetheriteArmor && cir.getReturnValue() && !source.isIn(DamageTypeTags.IS_FALL)) {
            // Ensure cooldown is not active
            RegistryEntry<StatusEffect> netheriteCooldownEntry = Registries.STATUS_EFFECT.getEntry(NetheriteGear.NETHERITE_COOLDOWN);
            if (!this.activeStatusEffects.containsKey(netheriteCooldownEntry)) {
                // Only trigger ability if health is going below half
                if (this.getHealth() <= this.getMaxHealth()/2) {
                    this.addStatusEffect(new StatusEffectInstance(
                            netheriteCooldownEntry, NetheriteGear.NETHERITE_COOLDOWN_DURATION_ARMOR, 0, false, false, true)
                    );
                    this.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.RESISTANCE, NetheriteGear.ARMOR_ABILITY_DURATION, NetheriteGear.ARMOR_ABILITY_POWER, false, true, true)
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
        // Check if entity is wearing full Sculk
        if (getArmorCount((LivingEntity)(Object)this, SculkGear.SCULK_ARMOR_MATERIAL) == 4) {
            // Add 1 second of Night Vision
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20, 0, false, false, true));
            if ((LivingEntity)(Object)this instanceof ServerPlayerEntity serverPlayer) {
                EndshardsCriteria.SCULK_ARMOR_LIGHT.trigger(serverPlayer);
            }
        }
    }
}
