package dev.amot.endshards;

import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.util.EndshardsGameRules;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;

public class EndshardsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(EndshardsBlocks.STRANGE_CRYSTAL, RenderLayer.getCutout());

        ClientPlayNetworking.registerGlobalReceiver(EndshardsGameRules.DO_NIGHT_VISION_FLICKER_CHANNEL, (client, handler, buf, responseSender) -> {
            EndshardsGameRules.doNightVisionFlickerGamerule = buf.readBoolean();
        });
    }
}
