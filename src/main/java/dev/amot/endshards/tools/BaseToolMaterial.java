package dev.amot.endshards.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public record BaseToolMaterial(Item repair_ingredient, int durability, float mining_speed_multiplier,
                               int mining_level, int enchantability) implements ToolMaterial {

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repair_ingredient);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return mining_speed_multiplier;
    }

    @Override
    public int getMiningLevel() {
        return mining_level;
    }

    @Override
    public float getAttackDamage() {
        return -1.0F;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }
}
