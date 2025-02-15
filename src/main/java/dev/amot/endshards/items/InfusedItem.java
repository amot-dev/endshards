package dev.amot.endshards.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusedItem extends Item {
    public InfusedItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }
}