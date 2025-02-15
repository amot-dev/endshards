package dev.amot.endshards.mixin;

import dev.amot.endshards.blocks.EndshardsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.block.SculkVeinBlock$SculkVeinGrowChecker")
public class PreventSculkVeinGrowthMixin {
    @Inject(method = "canGrow(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Lnet/minecraft/block/BlockState;)Z", at = @At(value = "HEAD"), cancellable = true)
    public void preventGrowthOverSoulSculk(BlockView world, BlockPos pos, BlockPos growPos, Direction direction, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        // If growth target is Soul Sculk, prevent growth
        BlockState blockState = world.getBlockState(growPos.offset(direction));
        if (blockState.isOf(EndshardsBlocks.SOUL_SCULK)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
