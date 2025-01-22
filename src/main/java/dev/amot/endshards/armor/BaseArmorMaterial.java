package dev.amot.endshards.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class BaseArmorMaterial implements ArmorMaterial {
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

    private final String name;
    private final Item repairIngredient;
    private final int[] protectionValues;
    private final int durabilityMultiplier;
    private final int enchantability;
    private final float toughness;
    private final float knockbackResistance;
    private final SoundEvent equipSound;

    @Override
    public int getDurability(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 13 * durabilityMultiplier;
            case LEGGINGS -> 15 * durabilityMultiplier;
            case CHESTPLATE -> 16 * durabilityMultiplier;
            case HELMET -> 11 * durabilityMultiplier;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> protectionValues[0];
            case LEGGINGS -> protectionValues[1];
            case CHESTPLATE -> protectionValues[2];
            case HELMET -> protectionValues[3];
        };
    }

    @Override
    public int getEnchantability() { return enchantability; }

    @Override
    public SoundEvent getEquipSound() { return equipSound; }

    @Override
    public Ingredient getRepairIngredient() { return Ingredient.ofItems(repairIngredient); }

    @Override
    public String getName() { return name; }

    @Override
    public float getToughness() { return toughness; }

    @Override
    public float getKnockbackResistance() { return knockbackResistance; }
}
