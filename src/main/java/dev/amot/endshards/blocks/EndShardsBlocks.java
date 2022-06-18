package dev.amot.endshards.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.amot.endshards.EndShards.modid;

public class EndShardsBlocks {
    public static final Block ENDER_BLOCK = new EnderBlock();
    public static final Block STRANGE_CRYSTAL = new StrangeCrystal();

    public static void register(){
        Registry.register(Registry.BLOCK, new Identifier(modid, "ender_block"), ENDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_block"),
                new BlockItem(ENDER_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(modid, "strange_crystal"), STRANGE_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(modid, "strange_crystal"),
                new BlockItem(STRANGE_CRYSTAL, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}