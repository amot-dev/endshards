package dev.amot.endshards.tools;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.util.IThrallOwner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

import static dev.amot.endshards.util.AbilityConstants.THRALL_ALLOWED_ENTITIES;

public class SculkSwordItem extends SwordItem {
    public SculkSwordItem() {
        super(SculkGear.SCULK_TOOL_MATERIAL, 8, -2.4F, new Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.sculk_sword.tooltip").formatted(Formatting.DARK_BLUE));
    }

    private static final int AbilityMaxThrallCount = 3;

    public static final float SoulFragmentDropChance = 0.005F;
    public static final float SoulFragmentLootingMultiplier = 0.002F;

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!serverPlayer.getActiveStatusEffects().containsKey(SculkGear.SCULK_COOLDOWN)) {
                if (THRALL_ALLOWED_ENTITIES.contains(entity.getType())) {
                    if (((IThrallOwner)serverPlayer).getThrallCount() < AbilityMaxThrallCount) {
                        // Try to add thrall
                        if (((IThrallOwner)serverPlayer).addThrall((MobEntity)entity)) {
                            serverPlayer.addStatusEffect(new StatusEffectInstance(
                                    SculkGear.SCULK_COOLDOWN, SculkGear.SCULK_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                            );
                            client.inGameHud.setOverlayMessage(Text.translatable(
                                    "message.endshards.sculk_sword_thrall_count",
                                    AbilityMaxThrallCount - ((IThrallOwner)serverPlayer).getThrallCount()
                            ).formatted(Formatting.DARK_GREEN), false);
                            EndshardsCriteria.SCULK_SWORD_ENTHRALL.trigger(serverPlayer);
                        }
                    }
                    else {
                        client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.sculk_sword_thrall_count_over").formatted(Formatting.RED), false);
                    }
                }
                else {
                    client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.sculk_sword_fail").formatted(Formatting.RED), false);
                }
            }
            else {
                client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.cooldown_active").formatted(Formatting.RED), false);
            }
        }
        return ActionResult.PASS;
    }
}
