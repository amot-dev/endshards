package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

public interface IThrall {
    void makeThrallFor(PlayerEntity owner);
    void clearActiveTarget();
    boolean isThrall();
    PlayerEntity getOwner();
}
