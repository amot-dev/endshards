package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EnderHoeItem extends HoeItem {
    public EnderHoeItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 1, 0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
