package dev.amot.endshards.items;

import dev.amot.endshards.armor.SculkArmorItem;
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

public class SculkEquipment {
    public static final TagKey<Item> REPAIRS_SCULK = TagKey.of(RegistryKeys.ITEM, Identifier.of(modid, "repairs_sculk"));
    public static final RegistryKey<EquipmentAsset> SCULK_ARMOR_MATERIAL_KEY = RegistryKey.of(
            EquipmentAssetKeys.REGISTRY_KEY,
            Identifier.of(modid, "sculk")
    );
    public static final TagKey<Block> INCORRECT_FOR_SCULK_TOOL = TagKey.of(RegistryKeys.BLOCK, Identifier.of(modid, "incorrect_for_sculk_tool"));

    public static final ArmorMaterial SCULK_ARMOR_MATERIAL = EndshardsEquipment.createArmorMaterial(REPAIRS_SCULK, SCULK_ARMOR_MATERIAL_KEY, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE);
    public static final ToolMaterial SCULK_TOOL_MATERIAL = EndshardsEquipment.createToolMaterial(REPAIRS_SCULK, INCORRECT_FOR_SCULK_TOOL);

    public static final Item SCULK_HELMET = registerItem(
            "sculk_helmet",
            settings -> new SculkArmorItem(EquipmentType.HELMET, settings),
            new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(EndshardsEquipment.ARMOR_DURABILITY))
    );
    public static final Item SCULK_CHESTPLATE = registerItem(
            "sculk_chestplate",
            settings -> new SculkArmorItem(EquipmentType.CHESTPLATE, settings),
            new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(EndshardsEquipment.ARMOR_DURABILITY))
    );
    public static final Item SCULK_LEGGINGS = registerItem(
            "sculk_leggings",
            settings -> new SculkArmorItem(EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(EndshardsEquipment.ARMOR_DURABILITY))
    );
    public static final Item SCULK_BOOTS = registerItem(
            "sculk_boots",
            settings -> new SculkArmorItem(EquipmentType.BOOTS, settings),
            new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(EndshardsEquipment.ARMOR_DURABILITY))
    );

    public static final Item SCULK_SWORD = registerItem("sculk_sword", SculkSwordItem::new);
    public static final Item SCULK_PICKAXE = registerItem("sculk_pickaxe", SculkPickaxeItem::new);
    public static final Item SCULK_SHOVEL = registerItem("sculk_shovel", SculkShovelItem::new);
    public static final Item SCULK_AXE = registerItem("sculk_axe", SculkAxeItem::new);
    public static final Item SCULK_HOE = registerItem("sculk_hoe", SculkHoeItem::new);

    public static final StatusEffect SCULK_COOLDOWN = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier.of(modid, "sculk_cooldown"),
            new CooldownEffect()
    );
    public static final int SCULK_COOLDOWN_DURATION_SWORD = 200;

    public static void init(){
        addToItemGroups(SCULK_HELMET, ItemGroups.COMBAT);
        addToItemGroups(SCULK_CHESTPLATE, ItemGroups.COMBAT);
        addToItemGroups(SCULK_LEGGINGS, ItemGroups.COMBAT);
        addToItemGroups(SCULK_BOOTS, ItemGroups.COMBAT);

        addToItemGroups(SCULK_SWORD, ItemGroups.COMBAT);
        addToItemGroups(SCULK_PICKAXE, ItemGroups.TOOLS);
        addToItemGroups(SCULK_SHOVEL, ItemGroups.TOOLS);
        addToItemGroups(SCULK_AXE, ItemGroups.TOOLS, ItemGroups.COMBAT);
        addToItemGroups(SCULK_HOE, ItemGroups.TOOLS);
    }
}
