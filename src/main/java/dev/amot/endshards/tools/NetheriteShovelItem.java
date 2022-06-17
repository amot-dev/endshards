package dev.amot.endshards.tools;

import dev.amot.endshards.NetheriteItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetheriteShovelItem extends ShovelItem {
    public NetheriteShovelItem() {
        super(NetheriteItems.NETHERITE_TOOL_MATERIAL, 6.5F, -3.0F, new Settings().group(ItemGroup.TOOLS));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
