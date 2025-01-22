package dev.amot.endshards.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;

public class SculkBlock extends Block {
    public SculkBlock() {
        super(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(6.0f).requiresTool());
    }
}
