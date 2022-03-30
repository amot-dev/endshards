package dev.amot.endshards.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class BaseArmorMaterial implements ArmorMaterial {
    private final String name;
    private final Item repair_ingredient;
    private final int[] protection_values;
    private final int[] base_durability = {13,15,16,11};
    private final int durability_multiplier;
    private final int enchantability;
    private final float toughness;
    private final float knockback_resistance;
    private final SoundEvent equip_sound;

    public BaseArmorMaterial(String name, Item repair_ingredient, int[] protection_values, int durability_multiplier,
                             int enchantability, float toughness, float knockback_resistance, SoundEvent equip_sound){
        this.name = name;
        this.repair_ingredient = repair_ingredient;
        this.protection_values = protection_values;
        this.durability_multiplier = durability_multiplier;
        this.enchantability = enchantability;
        this.toughness = toughness;
        this.knockback_resistance = knockback_resistance;
        this.equip_sound = equip_sound;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repair_ingredient);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return protection_values[slot.getEntitySlotId()];
    }

    @Override
    public int getDurability(EquipmentSlot slot) {

        return base_durability[slot.getEntitySlotId()] * durability_multiplier;
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
        return knockback_resistance;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equip_sound;
    }
}
