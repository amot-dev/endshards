package dev.amot.endshards.items;

import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.util.IArmorMaterial;
import dev.amot.endshards.util.IMiningToolMaterial;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.item.equipment.*;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;

public class NetheriteGear {
    public static final StatusEffect NETHERITE_COOLDOWN = Registry.register(
            Registries.STATUS_EFFECT,
            Identifier.of(modid, "netherite_cooldown"),
            new CooldownEffect()
    );

    public static final int NETHERITE_COOLDOWN_DURATION_ARMOR = 1200;
    public static final int NETHERITE_COOLDOWN_DURATION_SWORD = 600;

    public static final int ARMOR_ABILITY_DURATION = 200;
    public static final int ARMOR_ABILITY_POWER = 3;

    public static final int SWORD_ABILITY_DURATION = 10;
    public static final double SWORD_ABILITY_RANGE = 10.0F;

    public static void init(){
        ItemTooltipCallback.EVENT.register((stack, context, tooltip, lines) -> {
            // Add tooltip for Netherite Armor
            if (stack.getItem() instanceof ArmorItem armorItem && ((IArmorMaterial)armorItem).endshards$getMaterial() == ArmorMaterials.NETHERITE) {
                lines.add(1, Text.translatable("item.endshards.netherite_armor.tooltip").formatted(Formatting.DARK_BLUE));
            }
            else if (stack.getItem() instanceof MiningToolItem toolItem && ((IMiningToolMaterial)toolItem).endshards$getMaterial() == ToolMaterial.NETHERITE) {
                lines.add(1, Text.translatable("item.endshards.netherite_tool.tooltip").formatted(Formatting.DARK_BLUE));
            }
            else if (stack.getItem() == Items.NETHERITE_SWORD) {
                lines.add(1, Text.translatable("item.endshards.netherite_sword.tooltip").formatted(Formatting.DARK_BLUE));
            }
        });
    }
}