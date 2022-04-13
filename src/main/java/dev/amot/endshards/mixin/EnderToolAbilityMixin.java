package dev.amot.endshards.mixin;

import dev.amot.endshards.EnderItems;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

import static net.minecraft.block.Block.*;

@Mixin(Block.class)
public abstract class EnderToolAbilityMixin implements dev.amot.endshards.util.IEnderToolAbility {

    @Shadow
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack) {
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void injectAfterBreakMethod(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
        if (itemInHand instanceof ToolItem toolInHand && toolInHand.getMaterial() == EnderItems.ENDER_TOOL_MATERIAL) {
            player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
            player.addExhaustion(0.005F);

            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, player, stack).forEach((stackX) -> {
                    if (!player.getInventory().insertStack(stackX)) {
                        Supplier<ItemEntity> itemEntitySupplier = () -> new ItemEntity(world, player.getEyePos().getX(),
                                player.getEyePos().getY()-1F, player.getEyePos().getZ(), stackX, 0, 0, 0);
                        dropStack(world, itemEntitySupplier, stackX);
                    }
                    else {
                        //TODO: Decide whether to increase pick up stats for inventory warps (note: this line only works on client)
                        //player.increaseStat(Stats.PICKED_UP.getOrCreateStat(stackX.getItem()), stackX.getCount());
                        player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1F, 1F);
                    }
                });
                state.onStacksDropped((ServerWorld)world, pos, stack);
            }
            ci.cancel();
        }
    }
}
