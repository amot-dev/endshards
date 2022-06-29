package dev.amot.endshards.tools;

import dev.amot.endshards.items.SculkGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class SculkSwordItem extends SwordItem {
    public SculkSwordItem() {
        super(SculkGear.SCULK_TOOL_MATERIAL, 8, -2.4F, new Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.sculk_sword.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
