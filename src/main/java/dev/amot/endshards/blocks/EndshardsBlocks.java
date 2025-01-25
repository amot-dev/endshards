package dev.amot.endshards.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;

import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerBlock;

public class EndshardsBlocks {
    public static final Block ENDER_BLOCK = registerBlock(
            "ender_block",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(6.0f).requiresTool()
    );
    public static final Block SCULK_GEM_BLOCK = registerBlock(
            "sculk_gem_block",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).strength(6.0f).requiresTool()
    );
    public static final Block SOUL_SCULK = registerBlock(
            "soul_sculk",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.SCULK).strength(1.0f).requiresTool()
    );
    public static final Block STRANGE_CRYSTAL = registerBlock(
            "strange_crystal",
            (StrangeCrystal::new),
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_CLUSTER).nonOpaque().strength(4.0f).requiresTool()
    );

    public static void init() {
        addToItemGroups(ENDER_BLOCK.asItem(), ItemGroups.BUILDING_BLOCKS);
        addToItemGroups(SCULK_GEM_BLOCK.asItem(), ItemGroups.BUILDING_BLOCKS);
        addToItemGroups(SOUL_SCULK.asItem(), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
        addToItemGroups(STRANGE_CRYSTAL.asItem(), ItemGroups.NATURAL);
    }
}
