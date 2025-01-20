package dev.amot.endshards.items;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.SculkArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import dev.amot.endshards.util.EndshardsMiningLevels;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.amot.endshards.Endshards.modid;

public class SculkGear {
    public static final ArmorMaterial SCULK_ARMOR_MATERIAL = new BaseArmorMaterial(
            "sculk", EndshardsItems.SCULK_GEM, new int[] {3,6,8,3}, 37, 15, 3F, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial SCULK_TOOL_MATERIAL = new BaseToolMaterial(
            EndshardsItems.SCULK_GEM, 2032, 9.0F, EndshardsMiningLevels.SCULK, -1.0F, 15
    );

    public static final Item SCULK_HELMET = new SculkArmorItem(EquipmentSlot.HEAD);
    public static final Item SCULK_CHESTPLATE = new SculkArmorItem(EquipmentSlot.CHEST);
    public static final Item SCULK_LEGGINGS = new SculkArmorItem(EquipmentSlot.LEGS);
    public static final Item SCULK_BOOTS = new SculkArmorItem(EquipmentSlot.FEET);

    public static final ToolItem SCULK_SWORD = new SculkSwordItem();
    public static final ToolItem SCULK_PICKAXE = new SculkPickaxeItem();
    public static final ToolItem SCULK_SHOVEL = new SculkShovelItem();
    public static final ToolItem SCULK_AXE = new SculkAxeItem();
    public static final ToolItem SCULK_HOE = new SculkHoeItem();

    public static final StatusEffect SCULK_COOLDOWN = new CooldownEffect();
    public static final int SCULK_COOLDOWN_DURATION_SWORD = 200;

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_helmet"), SCULK_HELMET);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_chestplate"), SCULK_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_leggings"), SCULK_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_boots"), SCULK_BOOTS);

        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_sword"), SCULK_SWORD);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_pickaxe"), SCULK_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_shovel"), SCULK_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_axe"), SCULK_AXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_hoe"), SCULK_HOE);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "sculk_cooldown"), SCULK_COOLDOWN);
    }
}
