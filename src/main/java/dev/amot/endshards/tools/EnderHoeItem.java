package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EnderHoeItem extends HoeItem {
    public EnderHoeItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 1, 0F, new Item.Settings().group(ItemGroup.TOOLS));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.ender_tool.tooltip").formatted(Formatting.DARK_BLUE) );
    }
}
