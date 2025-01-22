package dev.amot.endshards.items;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.SculkArmorItem;
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

public class SculkGear {
    public static final ArmorMaterial SCULK_ARMOR_MATERIAL = new BaseArmorMaterial(
            "sculk", EndshardsItems.SCULK_GEM, new int[] {3,6,8,3}, 37, 15, 3F, 0.1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial SCULK_TOOL_MATERIAL = new BaseToolMaterial(
            EndshardsItems.SCULK_GEM, 2032, 9.0F, EndshardsMiningLevels.SCULK, -1.0F, 15
    );

    public static final Item SCULK_HELMET = registerItem(Identifier.of(modid, "sculk_helmet"), new SculkArmorItem(ArmorItem.Type.HELMET));
    public static final Item SCULK_CHESTPLATE = registerItem(Identifier.of(modid, "sculk_chestplate"), new SculkArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Item SCULK_LEGGINGS = registerItem(Identifier.of(modid, "sculk_leggings"), new SculkArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Item SCULK_BOOTS = registerItem(Identifier.of(modid, "sculk_boots"), new SculkArmorItem(ArmorItem.Type.BOOTS));

    public static final Item SCULK_SWORD = registerItem(Identifier.of(modid, "sculk_sword"), new SculkSwordItem());
    public static final Item SCULK_PICKAXE = registerItem(Identifier.of(modid, "sculk_pickaxe"), new SculkPickaxeItem());
    public static final Item SCULK_SHOVEL = registerItem(Identifier.of(modid, "sculk_shovel"), new SculkShovelItem());
    public static final Item SCULK_AXE = registerItem(Identifier.of(modid, "sculk_axe"), new SculkAxeItem());
    public static final Item SCULK_HOE = registerItem(Identifier.of(modid, "sculk_hoe"), new SculkHoeItem());

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
