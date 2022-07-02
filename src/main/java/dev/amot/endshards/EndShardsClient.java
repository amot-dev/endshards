package dev.amot.endshards;

import dev.amot.endshards.blocks.EndShardsBlocks;
import dev.amot.endshards.util.EndShardsGameRules;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;

public class EndShardsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(EndShardsBlocks.STRANGE_CRYSTAL, RenderLayer.getCutout());

        ClientPlayNetworking.registerGlobalReceiver(EndShardsGameRules.DO_NIGHT_VISION_FLICKER_CHANNEL, (client, handler, buf, responseSender) -> {
            EndShardsGameRules.doNightVisionFlickerGamerule = buf.readBoolean();
        });
    }
}
