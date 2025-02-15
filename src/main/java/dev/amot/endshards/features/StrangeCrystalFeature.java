package dev.amot.endshards.features;

import com.mojang.serialization.Codec;
import dev.amot.endshards.blocks.EndshardsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class StrangeCrystalFeature extends Feature<DefaultFeatureConfig> {
    public StrangeCrystalFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos pos = context.getWorld().getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, context.getOrigin());
        BlockState blockstate = EndshardsBlocks.STRANGE_CRYSTAL.getDefaultState().with(Properties.FACING, Direction.DOWN);

        while (pos.getY() > context.getWorld().getBottomY()){
            if (context.getWorld().getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                if (context.getWorld().getBlockState(pos.offset(Direction.UP)).getBlock().equals(Blocks.END_STONE)){
                    context.getWorld().setBlockState(pos, blockstate, Block.NOTIFY_ALL);
                    return true;
                }
            }
            pos = pos.offset(Direction.DOWN);
        }

        return false;
    }
}