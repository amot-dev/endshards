package dev.amot.endshards.armor;

import dev.amot.endshards.items.NetheriteGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetheriteArmorItem extends ArmorItem {
    public NetheriteArmorItem(ArmorItem.Type type) {
        super(NetheriteGear.NETHERITE_ARMOR_MATERIAL, type, new Settings().fireproof());
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_armor.tooltip").formatted(Formatting.DARK_BLUE));
    }

    public static final int abilityDuration = 200;
    public static final int abilityPower = 3;
}
