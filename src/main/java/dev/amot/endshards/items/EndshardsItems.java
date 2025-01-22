package dev.amot.endshards.items;

import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;
import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerItem;

public class EndshardsItems {
    public static final Item ENDSHARD = registerItem(Identifier.of(modid, "endshard"), new Item(new Item.Settings()));
    public static final Item ENDER_INGOT = registerItem(Identifier.of(modid, "ender_ingot"), new Item(new Item.Settings()));
    public static final Item ENDER_INGOT_INFUSED = registerItem(Identifier.of(modid, "ender_ingot_infused"), new InfusedItem(new Item.Settings()));
    public static final Item ENDER_UPGRADE_SMITHING_TEMPLATE = registerItem(
            Identifier.of(modid, "ender_upgrade_smithing_template"),
            new Item(new Item.Settings())//SmithingTemplates.createEnderUpgrade()
    );
    public static final Item FEAR_ESSENCE = registerItem(Identifier.of(modid, "fear_essence"), new Item(new Item.Settings()));
    public static final Item INFUSION_CORE = registerItem(Identifier.of(modid, "infusion_core"), new Item(new Item.Settings()));
    public static final Item INFUSION_CORE_ENDER = registerItem(Identifier.of(modid, "infusion_core_ender"), new Item(new Item.Settings()));
    public static final Item INFUSION_CORE_NETHERITE = registerItem(Identifier.of(modid, "infusion_core_netherite"), new Item(new Item.Settings()));
    public static final Item INFUSION_CORE_SCULK = registerItem(Identifier.of(modid, "infusion_core_sculk"), new Item(new Item.Settings()));
    public static final Item INFUSION_CORE_NIGHTMARE = registerItem(Identifier.of(modid, "infusion_core_nightmare"), new Item(new Item.Settings()));
    public static final Item NETHERITE_INGOT_INFUSED = registerItem(Identifier.of(modid, "netherite_ingot_infused"), new InfusedItem(new Item.Settings()));
    public static final Item NIGHTMARE_FUEL = registerItem(Identifier.of(modid, "nightmare_fuel"), new Item(new Item.Settings()));
    public static final Item NIGHTMARE_PEARL = registerItem(Identifier.of(modid, "nightmare_pearl"), new Item(new Item.Settings()));
    public static final Item NIGHTMARE_PEARL_INFUSED = registerItem(Identifier.of(modid, "nightmare_pearl_infused"), new InfusedItem(new Item.Settings()));
    public static final Item NIGHTMARE_UPGRADE_SMITHING_TEMPLATE = registerItem(
            Identifier.of(modid, "nightmare_upgrade_smithing_template"),
            new Item(new Item.Settings())//SmithingTemplates.createEnderUpgrade()
    );
    public static final Item PHANTOM_SOUL_FRAGMENT = registerItem(Identifier.of(modid, "phantom_soul_fragment"), new Item(new Item.Settings()));
    public static final Item SCULK_GEM = registerItem(Identifier.of(modid, "sculk_gem"), new Item(new Item.Settings()));
    public static final Item SCULK_GEM_INFUSED = registerItem(Identifier.of(modid, "sculk_gem_infused"), new InfusedItem(new Item.Settings()));
    public static final Item SCULK_UPGRADE_SMITHING_TEMPLATE = registerItem(
            Identifier.of(modid, "sculk_upgrade_smithing_template"),
            new Item(new Item.Settings())//SmithingTemplates.createSculkUpgrade()
    );
    public static final Item SOUL_FRAGMENT = registerItem(Identifier.of(modid, "soul_fragment"), new Item(new Item.Settings()));
    public static final Item TERROR_EYES = registerItem(Identifier.of(modid, "terror_eyes"), new Item(new Item.Settings()));
    public static final Item WARDING_HEART = registerItem(Identifier.of(modid, "warding_heart"), new Item(new Item.Settings()));

    public static void init() {
        addToItemGroups(ENDSHARD, ItemGroups.INGREDIENTS);
        addToItemGroups(ENDER_INGOT, ItemGroups.INGREDIENTS);
        addToItemGroups(ENDER_INGOT_INFUSED, ItemGroups.INGREDIENTS);
        addToItemGroups(ENDER_UPGRADE_SMITHING_TEMPLATE, ItemGroups.INGREDIENTS);
        addToItemGroups(FEAR_ESSENCE, ItemGroups.INGREDIENTS);
        addToItemGroups(INFUSION_CORE, ItemGroups.INGREDIENTS);
        addToItemGroups(INFUSION_CORE_ENDER, ItemGroups.INGREDIENTS);
        addToItemGroups(INFUSION_CORE_NETHERITE, ItemGroups.INGREDIENTS);
        addToItemGroups(INFUSION_CORE_SCULK, ItemGroups.INGREDIENTS);
        addToItemGroups(INFUSION_CORE_NIGHTMARE, ItemGroups.INGREDIENTS);
        addToItemGroups(NETHERITE_INGOT_INFUSED, ItemGroups.INGREDIENTS);
        addToItemGroups(NIGHTMARE_UPGRADE_SMITHING_TEMPLATE, ItemGroups.INGREDIENTS);
        addToItemGroups(NIGHTMARE_FUEL, ItemGroups.INGREDIENTS);
        addToItemGroups(NIGHTMARE_PEARL, ItemGroups.INGREDIENTS);
        addToItemGroups(NIGHTMARE_PEARL_INFUSED, ItemGroups.INGREDIENTS);
        addToItemGroups(PHANTOM_SOUL_FRAGMENT, ItemGroups.INGREDIENTS);
        addToItemGroups(SCULK_GEM, ItemGroups.INGREDIENTS);
        addToItemGroups(SCULK_GEM_INFUSED, ItemGroups.INGREDIENTS);
        addToItemGroups(SCULK_UPGRADE_SMITHING_TEMPLATE, ItemGroups.INGREDIENTS);
        addToItemGroups(SOUL_FRAGMENT, ItemGroups.INGREDIENTS);
        addToItemGroups(TERROR_EYES, ItemGroups.INGREDIENTS);
        addToItemGroups(WARDING_HEART, ItemGroups.INGREDIENTS);
    }
}