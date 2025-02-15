package dev.amot.endshards.armor;

import dev.amot.endshards.items.SculkEquipment;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SculkArmorItem extends ArmorItem {
    public SculkArmorItem(EquipmentType type, Item.Settings settings) {
        super(SculkEquipment.SCULK_ARMOR_MATERIAL, type, settings.fireproof());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.sculk_armor.tooltip").formatted(Formatting.DARK_BLUE));
    }
}
