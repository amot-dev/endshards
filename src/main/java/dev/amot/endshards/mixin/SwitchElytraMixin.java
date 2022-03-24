package dev.amot.endshards.mixin;

import dev.amot.endshards.util.ISwitchElytra;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ElytraItem.class)
public abstract class SwitchElytraMixin implements ISwitchElytra {

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stackInHand);
        ItemStack equippedStack = user.getEquippedStack(equipmentSlot);
        if (equippedStack.isEmpty()) {
            user.equipStack(equipmentSlot, stackInHand.copy());
            stackInHand.setCount(0);
        }
        else {
            user.setStackInHand(hand, equippedStack);
            user.equipStack(equipmentSlot, stackInHand.copy());
        }
        if (!world.isClient()) {
            user.incrementStat(Stats.USED.getOrCreateStat(stackInHand.getItem()));
        }
        return TypedActionResult.success(stackInHand, world.isClient());
    }
}
