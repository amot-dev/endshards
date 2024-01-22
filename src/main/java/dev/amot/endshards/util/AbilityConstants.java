package dev.amot.endshards.util;

import net.minecraft.entity.EntityType;

import java.util.List;

public class AbilityConstants {
    public static final List<EntityType<?>> ENDER_WARP_BANNED_ENTITIES = List.of(
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDER_DRAGON,
            EntityType.ENDERMAN,
            EntityType.ENDERMITE,
            EntityType.RAVAGER,
            EntityType.SHULKER,
            EntityType.WARDEN,
            EntityType.WITHER
    );
    public static final List<EntityType<?>> THRALL_ALLOWED_ENTITIES = List.of(
            EntityType.CAVE_SPIDER,
            EntityType.DROWNED,
            EntityType.HUSK,
            EntityType.SKELETON,
            EntityType.SPIDER,
            EntityType.STRAY,
            EntityType.WITHER_SKELETON,
            EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN
    );
}
