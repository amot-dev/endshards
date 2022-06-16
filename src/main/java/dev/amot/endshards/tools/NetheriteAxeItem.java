package dev.amot.endshards.tools;

import dev.amot.endshards.NetheriteItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetheriteAxeItem extends AxeItem {
    public NetheriteAxeItem() {
        //super(NetheriteItems.NETHERITE_TOOL_MATERIAL, 5.0F, -3.0F, new Settings().group(ItemGroup.TOOLS));
        super(NetheriteItems.NETHERITE_TOOL_MATERIAL, 50.0F, -3.0F, new Settings().group(ItemGroup.TOOLS));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_tool.tooltip").formatted(Formatting.DARK_BLUE) );
    }
}
