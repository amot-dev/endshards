package dev.amot.endshards.tools;

import dev.amot.endshards.items.SculkEquipment;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SculkHoeItem extends HoeItem {
    public SculkHoeItem(Item.Settings settings) {
        super(SculkEquipment.SCULK_TOOL_MATERIAL, -4.0F, 0.0F, settings.fireproof());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.sculk_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
