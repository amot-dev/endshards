package dev.amot.endshards.util;

import net.minecraft.entity.mob.MobEntity;

public interface IThrallOwner {
    boolean addThrall(MobEntity thrall);
    int getThrallCount();
}
