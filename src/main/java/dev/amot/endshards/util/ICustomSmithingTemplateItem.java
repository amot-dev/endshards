package dev.amot.endshards.util;

import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Identifier;

import java.util.List;

public interface ICustomSmithingTemplateItem {
    SmithingTemplateItem endshards$createCustomUpgrade(String ingredientsTextTranslationKey, String additionsSlotDescriptionTextTranslationKey, List<Identifier> emptyAdditionsSlotTextures, Item.Settings settings);
}
