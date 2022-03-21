package dev.amot.endshards;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BaseToolMaterial implements ToolMaterial {
    private final Item repair_ingredient;
    private final int durability;
    private final float mining_speed_multiplier;
    private final int mining_level;
    private final int enchantability;

    BaseToolMaterial(Item repair_ingredient, int durability, float mining_speed_multiplier, int mining_level, int enchantability){
        this.repair_ingredient = repair_ingredient;
        this.durability = durability;
        this.mining_speed_multiplier = mining_speed_multiplier;
        this.mining_level = mining_level;
        this.enchantability = enchantability;
    }

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
