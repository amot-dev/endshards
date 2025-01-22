package dev.amot.endshards.tools;

import dev.amot.endshards.items.SculkGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class SculkHoeItem extends HoeItem {
    public SculkHoeItem() {
        super(SculkGear.SCULK_TOOL_MATERIAL, 1, 0F, new Settings().fireproof());
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.sculk_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
