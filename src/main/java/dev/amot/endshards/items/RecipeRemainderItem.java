package dev.amot.endshards.items;

import net.minecraft.item.Item;

public class RecipeRemainderItem extends Item {
    public RecipeRemainderItem(Item remainder, Settings settings) {
        super(settings.recipeRemainder(remainder));
    }
}