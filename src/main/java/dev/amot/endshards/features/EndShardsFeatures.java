package dev.amot.endshards.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

import java.util.List;

import static dev.amot.endshards.EndShards.modid;

public class EndShardsFeatures {
    public static final Feature<DefaultFeatureConfig> STRANGE_CRYSTAL_FEATURE = new StrangeCrystalFeature(DefaultFeatureConfig.CODEC);

    public static void register(){
        //TODO: Figure out how to make strange crystals NOT generate in a grid
        Registry.register(Registry.FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE);
        ConfiguredFeature<?, ?> STRANGE_CRYSTAL_FEATURE_CONFIGURED = new ConfiguredFeature<>(STRANGE_CRYSTAL_FEATURE, DefaultFeatureConfig.INSTANCE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE_CONFIGURED);
        RegistryEntry<ConfiguredFeature<?, ?>> STRANGE_CRYSTAL_FEATURE_ENTRY = BuiltinRegistries.CONFIGURED_FEATURE
                .entryOf(BuiltinRegistries.CONFIGURED_FEATURE.getKey(STRANGE_CRYSTAL_FEATURE_CONFIGURED).orElse(null));
        PlacedFeature STRANGE_CRYSTAL_FEATURE_PLACED = new PlacedFeature(STRANGE_CRYSTAL_FEATURE_ENTRY, List.of(
                RandomOffsetPlacementModifier.of(ConstantIntProvider.create(16), ConstantIntProvider.create(0)),
                RarityFilterPlacementModifier.of(1)
        ));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, BuiltinRegistries.PLACED_FEATURE.getKey(STRANGE_CRYSTAL_FEATURE_PLACED).orElseThrow());
    }
}
