package dev.amot.endshards.mixin;

import dev.amot.endshards.EnderGear;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Supplier;

import static net.minecraft.block.Block.*;

@Mixin(Block.class)
public abstract class EnderToolAbilityMixin implements dev.amot.endshards.util.IEnderToolAbility {

    @Shadow
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack) {
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
        player.addExhaustion(0.005F);
        Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
        if (itemInHand instanceof ToolItem toolInHand && toolInHand.getMaterial() == EnderGear.ENDER_TOOL_MATERIAL) {
            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, player, stack).forEach((stackX) -> {
                    if (!player.getInventory().insertStack(stackX)) {
                        Supplier<ItemEntity> itemEntitySupplier = () -> new ItemEntity(world, player.getEyePos().getX(),
                                player.getEyePos().getY()-1F, player.getEyePos().getZ(), stackX, 0, 0, 0);
                        dropStack(world, itemEntitySupplier, stackX);
                    }
                    else {
                        player.increaseStat(Stats.PICKED_UP.getOrCreateStat(stackX.getItem()), stackX.getCount());
                        player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1F, 1F);
                    }
                });
                state.onStacksDropped((ServerWorld)world, pos, stack);
            }
        }
        else dropStacks(state, world, pos, blockEntity, player, stack);
    }
}
