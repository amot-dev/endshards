package dev.amot.endshards.armor;

import dev.amot.endshards.items.EnderGear;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class EnderArmorItem extends ArmorItem {
    public EnderArmorItem(EquipmentType type, Item.Settings settings) {
        super(EnderGear.ENDER_ARMOR_MATERIAL, type, settings.fireproof());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.ender_armor.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
