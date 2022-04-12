package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class EnderShovelItem extends ShovelItem {
    public EnderShovelItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 6.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
