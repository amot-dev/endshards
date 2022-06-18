package dev.amot.endshards.armor;

import dev.amot.endshards.items.NetheriteGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NetheriteArmorItem extends ArmorItem {
    public NetheriteArmorItem(EquipmentSlot slot) {
        super(NetheriteGear.NETHERITE_ARMOR_MATERIAL, slot, new Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_armor.tooltip").formatted(Formatting.DARK_BLUE));
    }

    public static final int abilityDuration = 200;
    public static final int abilityPower = 3;
}
