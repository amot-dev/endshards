package dev.amot.endshards.tools;

import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.util.ISacrificedEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class NetheriteSwordItem extends SwordItem {
    public NetheriteSwordItem() {
        super(NetheriteGear.NETHERITE_TOOL_MATERIAL, 8, -2.4F, new Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.endshards.netherite_sword.tooltip").formatted(Formatting.DARK_BLUE));
    }

    double abilityRange = 10.0F;
    int abilityDuration = 10;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.world instanceof ServerWorld serverWorld) {
            if (!user.getActiveStatusEffects().containsKey(NetheriteGear.NETHERITE_COOLDOWN)) {
                // Get living entities in range
                List<LivingEntity> targets = serverWorld.getEntitiesByClass(
                        LivingEntity.class, (new Box(user.getBlockPos())).expand(abilityRange), Entity::isAlive
                );
                int burnedMobs = 0;

                // Burn all valid targets
                for (LivingEntity target : targets) {
                    if (target.equals(user)) continue;
                    if (target.isWet() || target.inPowderSnow) continue;
                    target.setOnFireFor(abilityDuration);
                    burnedMobs++;
                }

                // Only do stuff if targets were burned
                if (burnedMobs > 0) {
                    user.addStatusEffect(new StatusEffectInstance(
                            NetheriteGear.NETHERITE_COOLDOWN, NetheriteGear.NETHERITE_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                    );
                    if (user instanceof ServerPlayerEntity serverPlayer) {
                        EndshardsCriteria.NETHERITE_SWORD_FLAME_CRITERION.trigger(serverPlayer);
                    }
                }
                else {
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.netherite_sword_fail").formatted(Formatting.RED), false);
                }

                // If Sacrifice advancement is possible, set targets to check for this
                if (burnedMobs > 99) {
                    if (user instanceof ServerPlayerEntity serverPlayer) {
                        for (LivingEntity target : targets) {
                            ((ISacrificedEntity)target).setSacrificingPlayer(serverPlayer);
                        }
                    }
                }
            }
            else {
                MinecraftClient client = MinecraftClient.getInstance();
                client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.cooldown_active").formatted(Formatting.RED), false);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
