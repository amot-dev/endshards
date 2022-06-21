package dev.amot.endshards.blocks;

import dev.amot.endshards.items.EnderGear;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class StrangeCrystal extends AmethystClusterBlock {
    private static final int warpRange = 16;
    private static final int warpAttempts = 16;
    private static final int height = 7;
    private static final int offset = 3;

    public StrangeCrystal() {
        super(height, offset, FabricBlockSettings.of(Material.AMETHYST).nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(4.0f).requiresTool());
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005f);

        Random random = new Random();
        boolean blockDropped = false;
        for (int i = 0; i < warpAttempts; ++i) {
            BlockPos randomPos = new BlockPos(
                    pos.getX() + (random.nextDouble() - 0.5) * warpRange,
                    MathHelper.clamp(pos.getY() + (random.nextDouble() - 0.5) * warpRange, world.getBottomY(), world.getTopY()),
                    pos.getZ() + (random.nextDouble() - 0.5) * warpRange
            );
            if (world.getBlockState(randomPos).getBlock() == Blocks.AIR){
                if (world.getBlockState(randomPos.offset(Direction.DOWN)).getBlock() != Blocks.AIR){
                    super.afterBreak(world, player, randomPos, state, blockEntity, stack);

                    Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
                    if (!(itemInHand instanceof ToolItem toolInHand && toolInHand.getMaterial() == EnderGear.ENDER_TOOL_MATERIAL)) {
                        world.playSound(null, randomPos.getX(), randomPos.getY(), randomPos.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    }
                    blockDropped = true;
                }
            }
        }
        if (!blockDropped) super.afterBreak(world, player, pos, state, blockEntity, stack);
    }
}
