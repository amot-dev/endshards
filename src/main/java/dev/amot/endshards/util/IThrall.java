package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public interface IThrall {
    void makeThrallFor(PlayerEntity owner);
    void clearActiveTarget();
    boolean isThrall();
    UUID getThrallOwnerUUID();
}
