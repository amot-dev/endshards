package dev.amot.endshards.mixin;

import dev.amot.endshards.util.ICustomSmithingTemplateItem;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public abstract class SmithingTemplateItemMixin implements ICustomSmithingTemplateItem {
    @Shadow @Final private static Formatting DESCRIPTION_FORMATTING;
    @Shadow @Final private static Text NETHERITE_UPGRADE_APPLIES_TO_TEXT;
    @Shadow @Final private static Text NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT;
    @Shadow @Final private static Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_SLOT_SWORD_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_SLOT_PICKAXE_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_SLOT_SHOVEL_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_SLOT_AXE_TEXTURE;
    @Shadow @Final private static Identifier EMPTY_SLOT_HOE_TEXTURE;

    @Unique private static List<Identifier> UPGRADE_EMPTY_BASE_SLOT_TEXTURES = List.of(
            EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
            EMPTY_SLOT_SWORD_TEXTURE,
            EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
            EMPTY_SLOT_PICKAXE_TEXTURE,
            EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
            EMPTY_SLOT_AXE_TEXTURE,
            EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
            EMPTY_SLOT_HOE_TEXTURE,
            EMPTY_SLOT_SHOVEL_TEXTURE
    );

    @Unique
    public SmithingTemplateItem endshards$createCustomUpgrade(String ingredientsTextTranslationKey, String additionsSlotDescriptionTextTranslationKey, List<Identifier> emptyAdditionsSlotTextures, Item.Settings settings) {
        return new SmithingTemplateItem(
                NETHERITE_UPGRADE_APPLIES_TO_TEXT,
                Text.translatable(ingredientsTextTranslationKey).formatted(DESCRIPTION_FORMATTING),
                NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                Text.translatable(additionsSlotDescriptionTextTranslationKey),
                UPGRADE_EMPTY_BASE_SLOT_TEXTURES,
                emptyAdditionsSlotTextures,
                settings
        );
    }

    @ModifyArg(method = "createNetheriteUpgrade", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/SmithingTemplateItem;<init>(Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Ljava/util/List;Ljava/util/List;Lnet/minecraft/item/Item$Settings;)V"), index = 1)
    private static Text replaceNetheriteUpgradeIngredientsTextWithInfused(Text ingredientsText) {
        return Text.translatable("smithing_template.endshards.netherite_upgrade.ingredients").formatted(DESCRIPTION_FORMATTING);
    }

    @ModifyArg(method = "createNetheriteUpgrade", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/SmithingTemplateItem;<init>(Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;Ljava/util/List;Ljava/util/List;Lnet/minecraft/item/Item$Settings;)V"), index = 3)
    private static Text replaceNetheriteUpgradeAdditionsSlotDescriptionTextWithInfused(Text additionsSlotDescriptionText) {
        return Text.translatable("smithing_template.endshards.netherite_upgrade.additions_slot_description");
    }
}