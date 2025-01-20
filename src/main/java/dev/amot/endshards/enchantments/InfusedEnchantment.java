package dev.amot.endshards.enchantments;

import dev.amot.endshards.items.EndshardsItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

//TODO: Remove enchantment book
public class InfusedEnchantment extends Enchantment {

    public InfusedEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    List<Item> acceptableItems = Arrays.asList(
            EndshardsItems.ENDER_INGOT,
            Items.NETHERITE_INGOT,
            EndshardsItems.SCULK_GEM,
            EndshardsItems.NIGHTMARE_PEARL
    );

    @Override
    public int getMinPower(int level) {
        return 1000;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();

        return acceptableItems.contains(item);
    }
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }
    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }
}
