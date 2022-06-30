package dev.amot.endshards.util;

import net.minecraft.entity.player.PlayerEntity;

public interface IMobEntity {
    void makeThrallFor(PlayerEntity owner);
    boolean isThrall();
    boolean isThrallOf(PlayerEntity player);
}
