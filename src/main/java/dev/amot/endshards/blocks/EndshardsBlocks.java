package dev.amot.endshards.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;
import static dev.amot.endshards.util.RegistryHelper.addToItemGroups;
import static dev.amot.endshards.util.RegistryHelper.registerBlock;

public class EndshardsBlocks {
    public static final Block ENDER_BLOCK = registerBlock(Identifier.of(modid, "ender_block"), new EnderBlock(), true);
    public static final Block SCULK_BLOCK = registerBlock(Identifier.of(modid, "sculk_block"), new SculkBlock(), true);
    public static final Block SOUL_SCULK = registerBlock(Identifier.of(modid, "soul_sculk"), new SoulSculk(), true);
    public static final Block STRANGE_CRYSTAL = registerBlock(Identifier.of(modid, "strange_crystal"), new StrangeCrystal(), true);

    public static void init() {
        addToItemGroups(ENDER_BLOCK.asItem(), ItemGroups.BUILDING_BLOCKS);
        addToItemGroups(SCULK_BLOCK.asItem(), ItemGroups.BUILDING_BLOCKS);
        addToItemGroups(SOUL_SCULK.asItem(), ItemGroups.BUILDING_BLOCKS, ItemGroups.NATURAL);
        addToItemGroups(STRANGE_CRYSTAL.asItem(), ItemGroups.NATURAL);
    }
}
