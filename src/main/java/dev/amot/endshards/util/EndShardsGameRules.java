package dev.amot.endshards.util;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

import static dev.amot.endshards.EndShards.modid;

public class EndShardsGameRules {
    public static GameRules.Key<GameRules.BooleanRule> DO_NIGHT_VISION_FLICKER;
    public static Identifier DO_NIGHT_VISION_FLICKER_CHANNEL = new Identifier(modid, "do_night_vision_flicker_channel");
    public static boolean doNightVisionFlickerGamerule = false;

    public static void init() {
        DO_NIGHT_VISION_FLICKER = GameRuleRegistry.register("doNightVisionFlicker", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false,
                ((minecraftServer, booleanRule) -> {
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeBoolean(booleanRule.get());
                    for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
                        ServerPlayNetworking.send(player, DO_NIGHT_VISION_FLICKER_CHANNEL, buf);
                    }
                })));
    }
}
