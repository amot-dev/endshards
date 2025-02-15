package dev.amot.endshards.tools;

import dev.amot.endshards.items.EnderEquipment;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class EnderHoeItem extends HoeItem {
    public EnderHoeItem(Item.Settings settings) {
        super(EnderEquipment.ENDER_TOOL_MATERIAL, -4.0F, 0.0F, settings.fireproof());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.ender_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
