package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class EnderAxeItem extends AxeItem {
    public EnderAxeItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 10, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
