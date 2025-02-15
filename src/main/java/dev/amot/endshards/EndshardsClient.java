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

        ClientPlayNetworking.registerGlobalReceiver(EndshardsGameRules.DoInventoryEquipmentSwitchGamerulePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                EndshardsGameRules.doInventoryEquipmentSwitchGamerule = payload.value();
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(EndshardsGameRules.DoNightVisionFlickerGamerulePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                EndshardsGameRules.doNightVisionFlickerGamerule = payload.value();
            });
        });
    }
}
