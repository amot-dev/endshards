package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public interface IThrall {
    boolean isThrall();
    UUID getThrallOwnerUUID();
    void assignOwner(PlayerEntity owner);
    void convertToThrall();
    void clearActiveTarget();

}
