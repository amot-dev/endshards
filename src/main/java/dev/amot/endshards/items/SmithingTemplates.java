package dev.amot.endshards.items;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class SmithingTemplates {
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;
    private static final String TRANSLATION_KEY = Util.createTranslationKey("item", new Identifier("smithing_template"));

    private static final Text INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.ingredients")))
            .formatted(TITLE_FORMATTING);

    private static final Text APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.applies_to")))
            .formatted(TITLE_FORMATTING);

    private static final Text NETHERITE_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", new Identifier("netherite_upgrade")))
            .formatted(TITLE_FORMATTING);

    private static final Text NETHERITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);

    private static final Text NETHERITE_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
                    Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.ingredients"))
            )
            .formatted(DESCRIPTION_FORMATTING);

    private static final Text NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.base_slot_description"))
    );

    private static final Text NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.additions_slot_description"))
    );

//    public static SmithingTemplateItem createEnderUpgrade() {
//        return new SmithingTemplateItem(
//                NETHERITE_UPGRADE_APPLIES_TO_TEXT,
//                NETHERITE_UPGRADE_INGREDIENTS_TEXT,
//                NETHERITE_UPGRADE_TEXT,
//                NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
//                NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
//                getNetheriteUpgradeEmptyBaseSlotTextures(),
//                getNetheriteUpgradeEmptyAdditionsSlotTextures()
//        );
//    }

//    public static SmithingTemplateItem createSculkUpgrade() {
//        return new SmithingTemplateItem(
//                NETHERITE_UPGRADE_APPLIES_TO_TEXT,
//                NETHERITE_UPGRADE_INGREDIENTS_TEXT,
//                NETHERITE_UPGRADE_TEXT,
//                NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
//                NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
//                getNetheriteUpgradeEmptyBaseSlotTextures(),
//                getNetheriteUpgradeEmptyAdditionsSlotTextures()
//        );
//    }
}
