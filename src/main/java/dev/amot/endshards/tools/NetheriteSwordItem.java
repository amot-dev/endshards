package dev.amot.endshards.tools;

import dev.amot.endshards.NetheriteItems;
import net.minecraft.client.item.TooltipContext;
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.world instanceof ServerWorld) {
            if (!user.getActiveStatusEffects().containsKey(NetheriteItems.NETHERITE_COOLDOWN)) {
                //TODO: Implement
                user.addStatusEffect(new StatusEffectInstance(
                        NetheriteItems.NETHERITE_COOLDOWN, NetheriteItems.NETHERITE_COOLDOWN_DURATION_SWORD, 0, false, false, true)
                );
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
