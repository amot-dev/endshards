package dev.amot.endshards.items;

import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;
import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerItem;

public class EnderGear {
    public static final TagKey<Item> REPAIRS_ENDER = TagKey.of(RegistryKeys.ITEM, Identifier.of(modid, "repairs_ender"));
    public static final RegistryKey<EquipmentAsset> ENDER_ARMOR_MATERIAL_KEY = RegistryKey.of(
            EquipmentAssetKeys.REGISTRY_KEY,
            Identifier.of(modid, "ender")
    );
    public static final TagKey<Block> INCORRECT_FOR_ENDER_TOOL = TagKey.of(RegistryKeys.BLOCK, Identifier.of(modid, "incorrect_for_ender_tool"));

    public static final ArmorMaterial ENDER_ARMOR_MATERIAL = EndshardsGear.createArmorMaterial(REPAIRS_ENDER, ENDER_ARMOR_MATERIAL_KEY, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE);
    public static final ToolMaterial ENDER_TOOL_MATERIAL = EndshardsGear.createToolMaterial(REPAIRS_ENDER, INCORRECT_FOR_ENDER_TOOL);

    public static final Item ENDER_HELMET = registerItem(
            "ender_helmet",
            settings -> new EnderArmorItem(EquipmentType.HELMET, settings),
            new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(EndshardsGear.ARMOR_DURABILITY))
    );
    public static final Item ENDER_CHESTPLATE = registerItem(
            "ender_chestplate",
            settings -> new EnderArmorItem(EquipmentType.CHESTPLATE, settings),
            new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(EndshardsGear.ARMOR_DURABILITY))
    );
    public static final Item ENDER_LEGGINGS = registerItem(
            "ender_leggings",
            settings -> new EnderArmorItem(EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(EndshardsGear.ARMOR_DURABILITY))
    );
    public static final Item ENDER_BOOTS = registerItem(
            "ender_boots",
            settings -> new EnderArmorItem(EquipmentType.BOOTS, settings),
            new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(EndshardsGear.ARMOR_DURABILITY))
    );

    public static final Item ENDER_SWORD = registerItem("ender_sword", EnderSwordItem::new);
    public static final Item ENDER_PICKAXE = registerItem("ender_pickaxe", EnderPickaxeItem::new);
    public static final Item ENDER_SHOVEL = registerItem("ender_shovel", EnderShovelItem::new);
    public static final Item ENDER_AXE = registerItem("ender_axe", EnderAxeItem::new);
    public static final Item ENDER_HOE = registerItem("ender_hoe", EnderHoeItem::new);


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
