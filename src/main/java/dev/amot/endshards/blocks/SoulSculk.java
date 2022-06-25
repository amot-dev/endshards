package dev.amot.endshards.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class SoulSculk extends Block {
    public SoulSculk() {
        super(FabricBlockSettings.of(Material.SCULK).sounds(BlockSoundGroup.SCULK).strength(1.0f).requiresTool());
    }
}
