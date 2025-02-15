package dev.amot.endshards.features;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class EndshardsFeatures {
    public static final Feature<DefaultFeatureConfig> STRANGE_CRYSTAL = Registry.register(
            Registries.FEATURE,
            "strange_crystal_feature",
            new StrangeCrystalFeature(DefaultFeatureConfig.CODEC)
    );

    public static void init() {
    }
}
