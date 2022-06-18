package dev.amot.endshards.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public record BaseToolMaterial(Item repairIngredient, int durability, float miningSpeedMultiplier,
                               int miningLevel, float attackDamage, int enchantability) implements ToolMaterial {

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repairIngredient);
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeedMultiplier;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }
}
