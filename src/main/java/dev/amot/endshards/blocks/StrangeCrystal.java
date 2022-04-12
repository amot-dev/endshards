package dev.amot.endshards.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class StrangeCrystal extends AmethystClusterBlock {
    private static final int height = 7;
    private static final int offset = 3;

    public StrangeCrystal() {
        super(height, offset, FabricBlockSettings.of(Material.AMETHYST).nonOpaque().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(4.0f).requiresTool());
    }
}
