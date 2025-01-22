package dev.amot.endshards.items;

import com.mojang.serialization.Lifecycle;
import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.NetheriteArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import dev.amot.endshards.util.EndshardsMiningLevels;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;

public class NetheriteGear {
    public static final ArmorMaterial NETHERITE_ARMOR_MATERIAL = new BaseArmorMaterial(
            "netherite", Items.NETHERITE_INGOT, new int[] {3,6,8,3}, 37, 15, 3F, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial NETHERITE_TOOL_MATERIAL = new BaseToolMaterial(
            Items.NETHERITE_INGOT, 2032, 9.0F, EndshardsMiningLevels.NETHERITE, -1.0F, 15
    );

    public static final Item NETHERITE_HELMET = new NetheriteArmorItem(ArmorItem.Type.HELMET);
    public static final Item NETHERITE_CHESTPLATE = new NetheriteArmorItem(ArmorItem.Type.CHESTPLATE);
    public static final Item NETHERITE_LEGGINGS = new NetheriteArmorItem(ArmorItem.Type.LEGGINGS);
    public static final Item NETHERITE_BOOTS = new NetheriteArmorItem(ArmorItem.Type.BOOTS);

    public static final ToolItem NETHERITE_SWORD = new NetheriteSwordItem();
    public static final ToolItem NETHERITE_PICKAXE = new NetheritePickaxeItem();
    public static final ToolItem NETHERITE_SHOVEL = new NetheriteShovelItem();
    public static final ToolItem NETHERITE_AXE = new NetheriteAxeItem();
    public static final ToolItem NETHERITE_HOE = new NetheriteHoeItem();

    public static final StatusEffect NETHERITE_COOLDOWN = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier.of(modid, "netherite_cooldown"),
            new CooldownEffect()
    );
    public static final int NETHERITE_COOLDOWN_DURATION_ARMOR = 1200;
    public static final int NETHERITE_COOLDOWN_DURATION_SWORD = 600;

    public static void init(){
        replaceRegistries();
    }

    private static void replaceRegistries() {
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_HELMET),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_helmet")),
                NETHERITE_HELMET,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_CHESTPLATE),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_chestplate")),
                NETHERITE_CHESTPLATE,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_LEGGINGS),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_leggings")),
                NETHERITE_LEGGINGS,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_BOOTS),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_boots")),
                NETHERITE_BOOTS,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_SWORD),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_sword")),
                NETHERITE_SWORD,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_PICKAXE),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_pickaxe")),
                NETHERITE_PICKAXE,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_SHOVEL),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_shovel")),
                NETHERITE_SHOVEL,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_AXE),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_axe")),
                NETHERITE_AXE,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registries.ITEM).set(
                Registries.ITEM.getRawId(Items.NETHERITE_HOE),
                RegistryKey.of(Registries.ITEM.getKey(), Identifier.of("minecraft", "netherite_hoe")),
                NETHERITE_HOE,
                Lifecycle.stable()
        );
    }
}