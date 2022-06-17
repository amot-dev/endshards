package dev.amot.endshards.tools;

import dev.amot.endshards.NetheriteItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
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
        super(NetheriteItems.NETHERITE_TOOL_MATERIAL, 8, -2.4F, new Settings().group(ItemGroup.COMBAT));
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
            if (!user.getActiveStatusEffects().containsKey(NetheriteItems.NETHERITE_COOLDOWN)) {
                List<LivingEntity> targets = serverWorld.getEntitiesByClass(LivingEntity.class, (new Box(user.getBlockPos())).expand(abilityRange), Entity::isAlive);
                for (LivingEntity target : targets) {
                    if (!target.equals(user)) target.setOnFireFor(abilityDuration);
                }

                user.addStatusEffect(new StatusEffectInstance(
                        NetheriteItems.NETHERITE_COOLDOWN, NetheriteItems.NETHERITE_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                );
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
