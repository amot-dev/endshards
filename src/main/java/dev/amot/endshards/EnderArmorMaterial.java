package dev.amot.endshards;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import static dev.amot.endshards.EndShards.ENDER_INGOT;

public class EnderArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {3, 6, 8, 3};

    private static final int ENDER_DURABILITY_MULTIPLIER = 74;
    private static final int ENDER_ENCHANTABILITY = 15;
    private static final int ENDER_TOUGHNESS = 3;

    @Override
    public int getDurability(EquipmentSlot slot) {

        return BASE_DURABILITY[slot.getEntitySlotId()] * ENDER_DURABILITY_MULTIPLIER;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return ENDER_ENCHANTABILITY;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ENDER_INGOT);
    }

    @Override
    public String getName() {
        return "ender";
    }

    @Override
    public float getToughness() {
        return ENDER_TOUGHNESS;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
