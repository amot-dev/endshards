package dev.amot.endshards.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockInvoker {
    @Invoker("dropExperienceWhenMined")
    void invokeDropExperienceWhenMined(ServerWorld world, BlockPos pos, ItemStack tool, IntProvider experience);
}
