package dev.amot.endshards.items;

import dev.amot.endshards.util.ICustomSmithingTemplateItem;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerItem;

public class EndshardsItems {
    public static final Item ENDSHARD = registerItem("endshard");
    public static final Item ENDER_INGOT = registerItem("ender_ingot");
    public static final Item ENDER_INGOT_INFUSED = registerItem("ender_ingot_infused", InfusedItem::new, Rarity.UNCOMMON);
    public static final Item ENDER_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "ender_upgrade_smithing_template",
            // I know this is incredibly stupid please don't shout at me, they wouldn't let me have a public static mixin method
            settings -> ((ICustomSmithingTemplateItem)Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).endshards$createCustomUpgrade(
                    "smithing_template.endshards.ender_upgrade.ingredients",
                    "smithing_template.endshards.ender_upgrade.additions_slot_description",
                    List.of(Identifier.ofVanilla("container/slot/ingot")),
                    settings.rarity(Rarity.UNCOMMON)
            )
    );
    public static final Item FEAR_ESSENCE = registerItem("fear_essence");
    public static final Item INFUSION_CORE = registerItem("infusion_core");
    public static final Item INFUSION_CORE_ENDER = registerItem(
            "infusion_core_ender",
            settings -> new RecipeRemainderItem(INFUSION_CORE, settings.rarity(Rarity.UNCOMMON))
    );
    public static final Item INFUSION_CORE_NETHERITE = registerItem(
            "infusion_core_netherite",
            settings -> new RecipeRemainderItem(INFUSION_CORE, settings.rarity(Rarity.RARE))
    );
    public static final Item INFUSION_CORE_SCULK = registerItem(
            "infusion_core_sculk",
            settings -> new RecipeRemainderItem(INFUSION_CORE, settings.rarity(Rarity.RARE))
    );
    public static final Item INFUSION_CORE_NIGHTMARE = registerItem(
            "infusion_core_nightmare",
            settings -> new RecipeRemainderItem(INFUSION_CORE, settings.rarity(Rarity.EPIC))
    );
    public static final Item NETHERITE_INGOT_INFUSED = registerItem("netherite_ingot_infused", InfusedItem::new, Rarity.RARE);
    public static final Item NIGHTMARE_FUEL = registerItem("nightmare_fuel");
    public static final Item NIGHTMARE_PEARL = registerItem("nightmare_pearl");
    public static final Item NIGHTMARE_PEARL_INFUSED = registerItem("nightmare_pearl_infused", InfusedItem::new, Rarity.EPIC);
    public static final Item PHANTOM_SOUL_FRAGMENT = registerItem("phantom_soul_fragment");
    public static final Item SCULK_GEM = registerItem("sculk_gem");
    public static final Item SCULK_GEM_INFUSED = registerItem("sculk_gem_infused", InfusedItem::new, Rarity.RARE);
    public static final Item SCULK_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "sculk_upgrade_smithing_template",
            // I know this is incredibly stupid please don't shout at me, they wouldn't let me have a public static mixin method
            settings -> ((ICustomSmithingTemplateItem)Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE).endshards$createCustomUpgrade(
                    "smithing_template.endshards.sculk_upgrade.ingredients",
                    "smithing_template.endshards.sculk_upgrade.additions_slot_description",
                    List.of(Identifier.ofVanilla("container/slot/emerald")),
                    settings.rarity(Rarity.UNCOMMON)
            )
    );
    public static final Item SOUL_FRAGMENT = registerItem("soul_fragment");
    public static final Item TERROR_EYES = registerItem("terror_eyes", Rarity.EPIC);
    public static final Item WARDING_HEART = registerItem("warding_heart", Rarity.RARE);

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