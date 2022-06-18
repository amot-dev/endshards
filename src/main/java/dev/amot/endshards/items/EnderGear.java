package dev.amot.endshards.items;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import dev.amot.endshards.util.EndShardsMiningLevels;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.amot.endshards.EndShards.modid;

public class EnderGear {
    public static final ArmorMaterial ENDER_ARMOR_MATERIAL = new BaseArmorMaterial(
            "ender", EndShardsItems.ENDER_INGOT, new int[] {3,6,8,3}, 37, 15, 3F, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial ENDER_TOOL_MATERIAL = new BaseToolMaterial(
            EndShardsItems.ENDER_INGOT, 2032, 9.0F, EndShardsMiningLevels.ENDER, -1.0F, 15
    );

    public static final Item ENDER_HELMET = new EnderArmorItem(EquipmentSlot.HEAD);
    public static final Item ENDER_CHESTPLATE = new EnderArmorItem(EquipmentSlot.CHEST);
    public static final Item ENDER_LEGGINGS = new EnderArmorItem(EquipmentSlot.LEGS);
    public static final Item ENDER_BOOTS = new EnderArmorItem(EquipmentSlot.FEET);

    public static final ToolItem ENDER_SWORD = new EnderSwordItem();
    public static final ToolItem ENDER_PICKAXE = new EnderPickaxeItem();
    public static final ToolItem ENDER_SHOVEL = new EnderShovelItem();
    public static final ToolItem ENDER_AXE = new EnderAxeItem();
    public static final ToolItem ENDER_HOE = new EnderHoeItem();

    public static final StatusEffect ENDER_COOLDOWN = new CooldownEffect();
    public static final int ENDER_COOLDOWN_DURATION_ARMOR = 600;
    public static final int ENDER_COOLDOWN_DURATION_SWORD = 1200;

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_helmet"), ENDER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_chestplate"), ENDER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_leggings"), ENDER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_boots"), ENDER_BOOTS);

        Registry.register(Registry.ITEM, new Identifier(modid, "ender_sword"), ENDER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_pickaxe"), ENDER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_shovel"), ENDER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_axe"), ENDER_AXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_hoe"), ENDER_HOE);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "ender_cooldown"), ENDER_COOLDOWN);
    }
}
