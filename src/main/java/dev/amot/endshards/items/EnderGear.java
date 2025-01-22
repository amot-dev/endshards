package dev.amot.endshards.items;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import dev.amot.endshards.util.EndshardsMiningLevels;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;
import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerItem;

public class EnderGear {
    public static final ArmorMaterial ENDER_ARMOR_MATERIAL = new BaseArmorMaterial(
            "ender", EndshardsItems.ENDER_INGOT, new int[] {3,6,8,3}, 37, 15, 3F, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial ENDER_TOOL_MATERIAL = new BaseToolMaterial(
            EndshardsItems.ENDER_INGOT, 2032, 9.0F, EndshardsMiningLevels.ENDER, -1.0F, 15
    );

    public static final Item ENDER_HELMET = registerItem(Identifier.of(modid, "ender_helmet"), new EnderArmorItem(ArmorItem.Type.HELMET));
    public static final Item ENDER_CHESTPLATE = registerItem(Identifier.of(modid, "ender_chestplate"), new EnderArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Item ENDER_LEGGINGS = registerItem(Identifier.of(modid, "ender_leggings"), new EnderArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Item ENDER_BOOTS = registerItem(Identifier.of(modid, "ender_boots"), new EnderArmorItem(ArmorItem.Type.BOOTS));

    public static final Item ENDER_SWORD = registerItem(Identifier.of(modid, "ender_sword"), new EnderSwordItem());
    public static final Item ENDER_PICKAXE = registerItem(Identifier.of(modid, "ender_pickaxe"), new EnderPickaxeItem());
    public static final Item ENDER_SHOVEL = registerItem(Identifier.of(modid, "ender_shovel"), new EnderShovelItem());
    public static final Item ENDER_AXE = registerItem(Identifier.of(modid, "ender_axe"), new EnderAxeItem());
    public static final Item ENDER_HOE = registerItem(Identifier.of(modid, "ender_hoe"), new EnderHoeItem());


    public static final StatusEffect ENDER_COOLDOWN = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier.of(modid, "ender_cooldown"),
            new CooldownEffect()
    );
    public static final int ENDER_COOLDOWN_DURATION_ARMOR = 600;
    public static final int ENDER_COOLDOWN_DURATION_SWORD = 1200;

    public static void init(){
        addToItemGroups(ENDER_HELMET, ItemGroups.COMBAT);
        addToItemGroups(ENDER_CHESTPLATE, ItemGroups.COMBAT);
        addToItemGroups(ENDER_LEGGINGS, ItemGroups.COMBAT);
        addToItemGroups(ENDER_BOOTS, ItemGroups.COMBAT);

        addToItemGroups(ENDER_SWORD, ItemGroups.COMBAT);
        addToItemGroups(ENDER_PICKAXE, ItemGroups.TOOLS);
        addToItemGroups(ENDER_SHOVEL, ItemGroups.TOOLS);
        addToItemGroups(ENDER_AXE, ItemGroups.TOOLS, ItemGroups.COMBAT);
        addToItemGroups(ENDER_HOE, ItemGroups.TOOLS);
    }
}
