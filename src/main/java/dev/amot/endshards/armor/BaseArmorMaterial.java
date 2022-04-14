package dev.amot.endshards.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class BaseArmorMaterial implements ArmorMaterial {
    private final String name;
    private final Item repairIngredient;
    private final int[] protectionValues;
    private final int[] baseDurability = {13,15,16,11};
    private final int durabilityMultiplier;
    private final int enchantability;
    private final float toughness;
    private final float knockbackResistance;
    private final SoundEvent equipSound;

    public BaseArmorMaterial(String name, Item repairIngredient, int[] protectionValues, int durabilityMultiplier,
                             int enchantability, float toughness, float knockbackResistance, SoundEvent equipSound){
        this.name = name;
        this.repairIngredient = repairIngredient;
        this.protectionValues = protectionValues;
        this.durabilityMultiplier = durabilityMultiplier;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.equipSound = equipSound;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repairIngredient);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return protectionValues[slot.getEntitySlotId()];
    }

    @Override
    public int getDurability(EquipmentSlot slot) {

        return baseDurability[slot.getEntitySlotId()] * durabilityMultiplier;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }
}
