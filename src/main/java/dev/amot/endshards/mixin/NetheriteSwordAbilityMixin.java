package dev.amot.endshards.mixin;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.util.ISacrificedEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class NetheriteSwordAbilityMixin {
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    public void doNetheriteSwordAbility(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // Check if this item is a Netherite Sword
        if ((Object)this == Items.NETHERITE_SWORD) {
            if (user.getWorld() instanceof ServerWorld serverWorld) {
                RegistryEntry<StatusEffect> netheriteCooldownEntry = Registries.STATUS_EFFECT.getEntry(NetheriteGear.NETHERITE_COOLDOWN);
                if (!user.getActiveStatusEffects().containsKey(netheriteCooldownEntry)) {
                    // Get living entities in range
                    List<LivingEntity> targets = serverWorld.getEntitiesByClass(
                            LivingEntity.class, (new Box(user.getBlockPos())).expand(NetheriteGear.SWORD_ABILITY_RANGE), Entity::isAlive
                    );
                    int burnedMobs = 0;

                    // Burn all valid targets
                    for (LivingEntity target : targets) {
                        if (target.equals(user)) continue;
                        if (target.isWet() || target.inPowderSnow) continue;
                        target.setOnFireFor(NetheriteGear.SWORD_ABILITY_DURATION);
                        burnedMobs++;
                    }

                    // Only do stuff if targets were burned
                    if (burnedMobs > 0) {
                        user.addStatusEffect(new StatusEffectInstance(
                                netheriteCooldownEntry, NetheriteGear.NETHERITE_COOLDOWN_DURATION_SWORD, 0, false, false, true)
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
                                ((ISacrificedEntity)target).endshards$setSacrificingPlayer(serverPlayer);
                            }
                        }
                    }
                }
                else {
                    MinecraftClient client = MinecraftClient.getInstance();
                    client.inGameHud.setOverlayMessage(Text.translatable("message.endshards.cooldown_active").formatted(Formatting.RED), false);
                }
            }

            // Return early
            cir.setReturnValue(ActionResult.PASS);
            cir.cancel();
        }
    }
}
