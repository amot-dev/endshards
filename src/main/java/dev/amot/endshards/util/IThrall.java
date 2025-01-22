package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public interface IThrall {
    boolean endshards$isThrall();
    UUID endshards$getThrallOwnerUUID();
    void endshards$assignOwner(PlayerEntity owner);
    void endshards$convertToThrall();
    void endshards$clearActiveTarget();

}
