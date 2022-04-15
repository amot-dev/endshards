package dev.amot.endshards.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class EnderBlock extends Block {
    public EnderBlock() {
        super(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool());
    }
}
