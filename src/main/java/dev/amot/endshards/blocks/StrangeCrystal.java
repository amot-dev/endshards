package dev.amot.endshards.blocks;

import dev.amot.endshards.items.EnderEquipment;
import dev.amot.endshards.util.IMiningToolMaterial;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StrangeCrystal extends AmethystClusterBlock {
    private static final int WARP_RANGE = 16;
    private static final int WARP_ATTEMPTS = 16;
    private static final int HEIGHT = 7;
    private static final int OFFSET = 3;

    public StrangeCrystal(AbstractBlock.Settings settings) {
        super(HEIGHT, OFFSET, settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        // Try teleport drop WARP_ATTEMPTS times
        boolean successfulTeleport = false;
        for (int i = 0; i < WARP_ATTEMPTS; ++i) {
            // Pick random position within WARP_RANGE and world bounds
            BlockPos randomPos = BlockPos.ofFloored(
                    pos.getX() + (world.getRandom().nextDouble() - 0.5) * WARP_RANGE,
                    MathHelper.clamp(pos.getY() + (world.getRandom().nextDouble() - 0.5) * WARP_RANGE, world.getBottomY(), world.getTopYInclusive()),
                    pos.getZ() + (world.getRandom().nextDouble() - 0.5) * WARP_RANGE
            );

            // Ensure position picked is air
            if (world.getBlockState(randomPos).getBlock() == Blocks.AIR){
                // Scan downward to land on solid ground
                while (randomPos.getY() > world.getBottomY() && world.getBlockState(randomPos.offset(Direction.DOWN)).getBlock() == Blocks.AIR) {
                    randomPos = randomPos.down();
                }
                // Break block
                super.afterBreak(world, player, randomPos, state, blockEntity, stack);

                // Play teleport sound upon break unless broken by Ender tool (which would cause it to teleport into inventory directly)
                Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
                if (!(itemInHand instanceof MiningToolItem toolInHand && ((IMiningToolMaterial)toolInHand).endshards$getMaterial() == EnderEquipment.ENDER_TOOL_MATERIAL)) {
                    world.playSound(
                            null,
                            randomPos.getX(),
                            randomPos.getY(),
                            randomPos.getZ(),
                            SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
                            SoundCategory.BLOCKS,
                            1.0f,
                            1.0f
                    );
                }

                // Exit loop
                successfulTeleport = true;
                break;
            }
        }

        // If no successful teleport, drop block at original position
        if (!successfulTeleport) super.afterBreak(world, player, pos, state, blockEntity, stack);
    }
}
