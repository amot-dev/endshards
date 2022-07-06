package dev.amot.endshards.util;

import net.minecraft.server.network.ServerPlayerEntity;

public interface ISacrificedEntity {
    void setSacrificingPlayer(ServerPlayerEntity serverPlayer);
}
