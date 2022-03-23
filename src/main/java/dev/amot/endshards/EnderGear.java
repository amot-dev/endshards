package dev.amot.endshards;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.BaseToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;

public class EnderGear {
    public static final Item ENDSHARD = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ENDER_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

    public static final ArmorMaterial ENDER_ARMOR_MATERIAL = new BaseArmorMaterial(
            "ender", ENDER_INGOT, new int[] {3,8,6,3}, 37, 15, 3F, 0F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial ENDER_TOOL_MATERIAL = new BaseToolMaterial(
            ENDER_INGOT, 2032, 9F, MiningLevels.NETHERITE, 15
    );

    public static final Item ENDER_HELMET = new EnderArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item ENDER_CHESTPLATE = new EnderArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item ENDER_LEGGINGS = new EnderArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item ENDER_BOOTS = new EnderArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    public static final ToolItem ENDER_SWORD = new SwordItem(ENDER_TOOL_MATERIAL, 8, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final ToolItem ENDER_PICKAXE = new CustomPickaxeItem(ENDER_TOOL_MATERIAL, 6, -2.8F, new Item.Settings().group(ItemGroup.TOOLS));
    public static final ToolItem ENDER_SHOVEL = new ShovelItem(ENDER_TOOL_MATERIAL, 6.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    public static final ToolItem ENDER_AXE = new CustomAxeItem(ENDER_TOOL_MATERIAL, 10, -3.0F, new Item.Settings().group(ItemGroup.TOOLS));
    public static final ToolItem ENDER_HOE = new CustomHoeItem(ENDER_TOOL_MATERIAL, 1, 0F, new Item.Settings().group(ItemGroup.TOOLS));

    public static final StatusEffect ENDER_COOLDOWN = new CooldownEffect();

    public static class CustomPickaxeItem extends PickaxeItem {
        public CustomPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }
    public static class CustomAxeItem extends AxeItem {
        public CustomAxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }
    public static class CustomHoeItem extends HoeItem {
        public CustomHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }
}
