package dev.amot.endshards.tools;

import dev.amot.endshards.items.EnderGear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class EnderShovelItem extends ShovelItem {
    public EnderShovelItem(Item.Settings settings) {
        super(EnderGear.ENDER_TOOL_MATERIAL, 1.5F, -3.0F, settings.fireproof());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.ender_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
