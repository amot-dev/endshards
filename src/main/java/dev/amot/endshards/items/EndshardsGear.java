package dev.amot.endshards.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;

import java.util.Map;

public class EndshardsGear {
    public static final int ARMOR_DURABILITY = 37;
    private static final Map<EquipmentType, Integer> ARMOR_PROTECTION_VALUES = Map.of(
            EquipmentType.HELMET, 3,
            EquipmentType.CHESTPLATE, 8,
            EquipmentType.LEGGINGS, 6,
            EquipmentType.BOOTS, 3,
            EquipmentType.BODY, 11
    );
    private static final float ARMOR_TOUGHNESS = 3.0F;
    private static final float ARMOR_KNOCKBACK_RESISTANCE = 0.1F;

    private static final int TOOL_DURABILITY = 2031;
    private static final float TOOL_MINING_SPEED = 9.0F;
    private static final float TOOL_ATTACK_DAMAGE = 4.0F;

    private static final int ENCHANTABILITY = 15;

    public static ArmorMaterial createArmorMaterial(TagKey<Item> repair_tag, RegistryKey<EquipmentAsset> material_key, RegistryEntry<SoundEvent> equip_sound) {
        return new ArmorMaterial(
                ARMOR_DURABILITY,
                ARMOR_PROTECTION_VALUES,
                ENCHANTABILITY,
                equip_sound,
                ARMOR_TOUGHNESS,
                ARMOR_KNOCKBACK_RESISTANCE,  // knockback resistance
                repair_tag,
                material_key
        );
    }

    public static ToolMaterial createToolMaterial(TagKey<Item> repair_tag, TagKey<Block> incorrect_for_tag) {
        return new ToolMaterial(
                incorrect_for_tag,
                TOOL_DURABILITY,
                TOOL_MINING_SPEED,
                TOOL_ATTACK_DAMAGE,
                ENCHANTABILITY,
                repair_tag
        );
    }
}

