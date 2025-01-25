package dev.amot.endshards.tools;

import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

import java.util.List;

import static dev.amot.endshards.util.AbilityConstants.ENDER_WARP_BANNED_ENTITIES;

public class EnderSwordItem extends SwordItem {
    public EnderSwordItem(Item.Settings settings) {
        super(EnderGear.ENDER_TOOL_MATERIAL, 3.0F, -2.4F, settings.fireproof());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.endshards.ender_sword.tooltip").formatted(Formatting.DARK_BLUE));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            RegistryEntry<StatusEffect> enderCooldownEntry = Registries.STATUS_EFFECT.getEntry(EnderGear.ENDER_COOLDOWN);
            if (!user.getActiveStatusEffects().containsKey(enderCooldownEntry)) {
                if (entity.getType().getSpawnGroup() == SpawnGroup.MONSTER && !ENDER_WARP_BANNED_ENTITIES.contains(entity.getType())) {
                    entity.setPos(entity.getX(), -1000F, entity.getZ());
                    serverWorld.sendEntityStatus(entity, (byte)46);
                    stack.damage(1, user, EquipmentSlot.MAINHAND);
                    user.playSoundToPlayer(SoundEvents.ENTITY_ENDERMAN_TELEPORT, user.getSoundCategory(), 1.0f, 1.0f);

                    user.addStatusEffect(new StatusEffectInstance(
                            enderCooldownEntry, EnderGear.ENDER_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                    );

                    if (user instanceof ServerPlayerEntity serverUser) EndshardsCriteria.ENDER_SWORD_WARP_CRITERION.trigger(serverUser);
                }
                else {
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.ender_sword_fail").formatted(Formatting.RED), false);
                }
            }
            else {
                MinecraftClient client = MinecraftClient.getInstance();
                client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.cooldown_active").formatted(Formatting.RED), false);
            }
        }
        return ActionResult.PASS;
    }
}
