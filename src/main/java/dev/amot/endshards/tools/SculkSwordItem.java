package dev.amot.endshards.tools;

import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class SculkSwordItem extends SwordItem {
    public SculkSwordItem() {
        super(SculkGear.SCULK_TOOL_MATERIAL, 8, -2.4F, new Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.sculk_sword.tooltip").formatted(Formatting.DARK_BLUE));
    }

    private static final List<EntityType<?>> AbilityAllowedEntities = List.of(
            EntityType.CAVE_SPIDER,
            EntityType.DROWNED,
            EntityType.HUSK,
            EntityType.SKELETON,
            EntityType.SPIDER,
            EntityType.STRAY,
            EntityType.WITHER_SKELETON,
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );
    private static final int AbilityMaxThrallCount = 3;

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.world instanceof ServerWorld) {
            //EndShards.LOGGER.info("Info for " + entity.getUuidAsString());
            //EndShards.LOGGER.info("\tThrall? " + ((IThrall)entity).isThrall());
            //EndShards.LOGGER.info("\tOwner? " + ((IThrall)entity).getOwner());
            if (!user.getActiveStatusEffects().containsKey(SculkGear.SCULK_COOLDOWN)) {
                if (AbilityAllowedEntities.contains(entity.getType())) {
                    if (((IThrallOwner)user).getThrallCount() < AbilityMaxThrallCount) {
                        // Try to add thrall
                        if (((IThrallOwner)user).addThrall((MobEntity)entity)) {
                            user.addStatusEffect(new StatusEffectInstance(
                                    SculkGear.SCULK_COOLDOWN, SculkGear.SCULK_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                            );

                            if (user instanceof ServerPlayerEntity serverPlayer) {
                                EndShardsCriteria.SCULK_SWORD_ENTHRALL.trigger(serverPlayer);
                            }
                        }
                    }
                }
                else {
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.sculk_sword_fail").formatted(Formatting.RED), false);
                }
            }
        }
        return ActionResult.PASS;
    }
}
