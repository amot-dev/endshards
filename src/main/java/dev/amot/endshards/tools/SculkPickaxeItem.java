package dev.amot.endshards.tools;

import dev.amot.endshards.items.SculkGear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SculkPickaxeItem extends PickaxeItem {
    public SculkPickaxeItem(Item.Settings settings) {
        super(SculkGear.SCULK_TOOL_MATERIAL, 1.0F, -2.8F, settings.fireproof());
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.sculk_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
