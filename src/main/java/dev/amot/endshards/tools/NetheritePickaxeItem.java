package dev.amot.endshards.tools;

import dev.amot.endshards.items.NetheriteGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetheritePickaxeItem extends PickaxeItem {
    public NetheritePickaxeItem() {
        super(NetheriteGear.NETHERITE_TOOL_MATERIAL, 6, -2.8F, new Settings().fireproof());
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_tool.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
