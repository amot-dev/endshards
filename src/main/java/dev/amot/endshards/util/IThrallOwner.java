package dev.amot.endshards.util;

import net.minecraft.entity.mob.MobEntity;

public interface IThrallOwner {
    boolean endshards$addThrall(MobEntity thrall);
    void findLostThrall(MobEntity potentialThrall);
    int endshards$getThrallCount();
}
