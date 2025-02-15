package dev.amot.endshards.items;

import dev.amot.endshards.util.ICustomSmithingTemplateItem;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

import static dev.amot.endshards.util.RegistryHelper.*;

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
        addToItemGroupBefore(ItemGroups.INGREDIENTS, Items.NETHERITE_SCRAP,
                ENDSHARD,
                ENDER_INGOT,
                ENDER_INGOT_INFUSED
        );
        addToItemGroupAfter(ItemGroups.INGREDIENTS, Items.NETHERITE_INGOT,
                NETHERITE_INGOT_INFUSED,
                SOUL_FRAGMENT,
                SCULK_GEM,
                SCULK_GEM_INFUSED,
                NIGHTMARE_FUEL,
                NIGHTMARE_PEARL,
                NIGHTMARE_PEARL_INFUSED
        );
        addToItemGroupAfter(ItemGroups.INGREDIENTS, Items.NETHER_STAR,
                WARDING_HEART,
                TERROR_EYES
        );
        addToItemGroupAfter(ItemGroups.INGREDIENTS, Items.ECHO_SHARD,
                FEAR_ESSENCE
        );
        addToItemGroupAfter(ItemGroups.INGREDIENTS, Items.PHANTOM_MEMBRANE,
                PHANTOM_SOUL_FRAGMENT
        );
        addToItemGroupBefore(ItemGroups.INGREDIENTS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                INFUSION_CORE,
                INFUSION_CORE_ENDER,
                INFUSION_CORE_NETHERITE,
                INFUSION_CORE_SCULK,
                INFUSION_CORE_NIGHTMARE,
                ENDER_UPGRADE_SMITHING_TEMPLATE
        );
        addToItemGroupAfter(ItemGroups.INGREDIENTS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                SCULK_UPGRADE_SMITHING_TEMPLATE
        );
    }
}