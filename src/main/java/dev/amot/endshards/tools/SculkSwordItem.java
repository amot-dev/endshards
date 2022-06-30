package dev.amot.endshards.tools;

import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.util.IMobEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

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

    private final List<EntityType<?>> AbilityAllowedEntities = List.of(
            EntityType.CAVE_SPIDER,
            EntityType.DROWNED,
            EntityType.HUSK,
            //EntityType.PIGLIN,
            //EntityType.PIGLIN_BRUTE,
            //EntityType.PILLAGER,
            EntityType.SKELETON,
            EntityType.SPIDER,
            EntityType.STRAY,
            EntityType.WITHER_SKELETON,
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.world instanceof ServerWorld) {
            if (!user.getActiveStatusEffects().containsKey(SculkGear.SCULK_COOLDOWN)) {
                if (AbilityAllowedEntities.contains(entity.getType())) {
                    // All allowed entities are mob entities
                    if (!((IMobEntity)entity).isThrall()) {
                        ((IMobEntity)entity).makeThrallFor(user);
                    }
                }
            }
        }
        return ActionResult.PASS;
    }
}
