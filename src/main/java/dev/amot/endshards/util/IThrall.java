package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public interface IThrall {
    void assignOwner(PlayerEntity owner);
    void convertToThrall();
    void clearActiveTarget();
    boolean isThrall();
    UUID getThrallOwnerUUID();
}
