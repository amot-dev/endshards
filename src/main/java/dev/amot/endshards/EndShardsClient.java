package dev.amot.endshards;

import dev.amot.endshards.blocks.EndShardsBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class EndShardsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(EndShardsBlocks.STRANGE_CRYSTAL, RenderLayer.getCutout());
    }
}
