package dev.amot.endshards.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.amot.endshards.Endshards.modid;

public class EndshardsEnchantments {
    public static final Enchantment INFUSED = new InfusedEnchantment();

    public static void init(){
        Registry.register(Registry.ENCHANTMENT, new Identifier(modid, "infused"), INFUSED);
    }
}