package dev.amot.endshards.worldgen;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

import static dev.amot.endshards.util.RegistryHelper.registerPlacedFeature;

public class EndshardsPlacedFeatures {
    public static final RegistryKey<PlacedFeature> SOUL_SCULK_PLACED_KEY = registerPlacedFeature("soul_sculk");
    public static final RegistryKey<PlacedFeature> STRANGE_CRYSTAL_PLACED_KEY = registerPlacedFeature("strange_crystal");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        context.register(
                SOUL_SCULK_PLACED_KEY,
                new PlacedFeature(
                        registryLookup.getOrThrow(EndshardsConfiguredFeatures.SOUL_SCULK_CONFIGURED_KEY),
                        List.of(
                                CountPlacementModifier.of(50),
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(
                                        YOffset.aboveBottom(10),
                                        YOffset.aboveBottom(60)
                                ),
                                BiomePlacementModifier.of()
                        )
                )
        );
        context.register(
                STRANGE_CRYSTAL_PLACED_KEY,
                new PlacedFeature(
                        registryLookup.getOrThrow(EndshardsConfiguredFeatures.STRANGE_CRYSTAL_CONFIGURED_KEY),
                        List.of(
                                RandomOffsetPlacementModifier.horizontally(UniformIntProvider.create(3, 9)),
                                RarityFilterPlacementModifier.of(30)
                        )
                )
        );
    }
}