package dev.amot.endshards.tools;

import dev.amot.endshards.items.EnderGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EnderAxeItem extends AxeItem {
    public EnderAxeItem() {
        super(EnderGear.ENDER_TOOL_MATERIAL, 10.0F, -3.0F, new Item.Settings().fireproof());
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.ender_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
