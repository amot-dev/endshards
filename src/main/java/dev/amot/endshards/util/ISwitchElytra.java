package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface ISwitchElytra {
    TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);
}
