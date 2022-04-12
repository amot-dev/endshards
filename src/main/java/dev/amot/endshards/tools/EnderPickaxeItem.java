package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class EnderPickaxeItem extends PickaxeItem {
    public EnderPickaxeItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 6, -2.8F, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
