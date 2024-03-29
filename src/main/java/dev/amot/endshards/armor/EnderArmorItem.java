package dev.amot.endshards.armor;

import dev.amot.endshards.items.EnderGear;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class EnderArmorItem extends ArmorItem {
    public EnderArmorItem(EquipmentSlot slot) {
        super(EnderGear.ENDER_ARMOR_MATERIAL, slot, new Item.Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.ender_armor.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
