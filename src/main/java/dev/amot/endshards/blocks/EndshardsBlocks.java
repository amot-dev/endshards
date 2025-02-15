package dev.amot.endshards.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;

import static dev.amot.endshards.util.RegistryHelper.*;

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
        addToItemGroupAfter(ItemGroups.BUILDING_BLOCKS, Items.DIAMOND_BLOCK,
                ENDER_BLOCK
        );
        addToItemGroupAfter(ItemGroups.BUILDING_BLOCKS, Items.NETHERITE_BLOCK,
                SCULK_GEM_BLOCK
        );
        addToItemGroupBefore(ItemGroups.NATURAL, Items.ANCIENT_DEBRIS,
                STRANGE_CRYSTAL
        );
        addToItemGroupAfter(ItemGroups.NATURAL, Items.ANCIENT_DEBRIS,
                SOUL_SCULK
        );
    }
}
