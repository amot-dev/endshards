package dev.amot.endshards.util;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

import static dev.amot.endshards.Endshards.modid;

public class EndshardsGameRules {
    public static GameRules.Key<GameRules.BooleanRule> DO_INVENTORY_EQUIPMENT_SWITCH;
    public static GameRules.Key<GameRules.BooleanRule> DO_NIGHT_VISION_FLICKER;
    public static GameRules.Key<GameRules.BooleanRule> THRALLS_ATTACK_CREEPERS;

    public static final boolean DO_INVENTORY_EQUIPMENT_SWITCH_DEFAULT = true;
    public static final boolean DO_NIGHT_VISION_FLICKER_DEFAULT = false;
    public static final boolean THRALLS_ATTACK_CREEPERS_DEFAULT = false;

    public static boolean doInventoryEquipmentSwitchGamerule = DO_INVENTORY_EQUIPMENT_SWITCH_DEFAULT;
    public static boolean doNightVisionFlickerGamerule = DO_NIGHT_VISION_FLICKER_DEFAULT;

    public static void init() {
        PayloadTypeRegistry.playS2C().register(DoInventoryEquipmentSwitchGamerulePayload.ID, DoInventoryEquipmentSwitchGamerulePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EndshardsGameRules.DoNightVisionFlickerGamerulePayload.ID, EndshardsGameRules.DoNightVisionFlickerGamerulePayload.CODEC);

        DO_INVENTORY_EQUIPMENT_SWITCH = GameRuleRegistry.register("doInventoryEquipmentSwitch", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(DO_INVENTORY_EQUIPMENT_SWITCH_DEFAULT,
                ((minecraftServer, booleanRule) -> {
                    for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
                        ServerPlayNetworking.send(player, new DoInventoryEquipmentSwitchGamerulePayload(booleanRule.get()));
                    }
                })));
        DO_NIGHT_VISION_FLICKER = GameRuleRegistry.register("doNightVisionFlicker", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(DO_NIGHT_VISION_FLICKER_DEFAULT,
                ((minecraftServer, booleanRule) -> {
                    for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
                        ServerPlayNetworking.send(player, new DoNightVisionFlickerGamerulePayload(booleanRule.get()));
                    }
                })));

        THRALLS_ATTACK_CREEPERS = GameRuleRegistry.register("thrallsAttackCreepers", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(THRALLS_ATTACK_CREEPERS_DEFAULT));
    }

    public record DoInventoryEquipmentSwitchGamerulePayload(boolean value) implements CustomPayload {
        public static final CustomPayload.Id<DoInventoryEquipmentSwitchGamerulePayload> ID = new CustomPayload.Id<>(
                Identifier.of(modid, "do_inventory_equipment_switch_gamerule_payload")
        );
        public static final PacketCodec<RegistryByteBuf, DoInventoryEquipmentSwitchGamerulePayload> CODEC = PacketCodec.tuple(
                PacketCodecs.BOOLEAN,
                DoInventoryEquipmentSwitchGamerulePayload::value,
                DoInventoryEquipmentSwitchGamerulePayload::new
        );

        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record DoNightVisionFlickerGamerulePayload(boolean value) implements CustomPayload {
        public static final CustomPayload.Id<DoNightVisionFlickerGamerulePayload> ID = new CustomPayload.Id<>(
                Identifier.of(modid, "do_night_vision_flicker_gamerule_payload")
        );
        public static final PacketCodec<RegistryByteBuf, DoNightVisionFlickerGamerulePayload> CODEC = PacketCodec.tuple(
                PacketCodecs.BOOLEAN,
                DoNightVisionFlickerGamerulePayload::value,
                DoNightVisionFlickerGamerulePayload::new
        );

        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}