package dev.amot.endshards.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class InfusedEnchantment extends Enchantment {

    public InfusedEnchantment() {
        //TODO: Add EnchantmentTarget.NONE via Invoker
        super(Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1000;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
}
