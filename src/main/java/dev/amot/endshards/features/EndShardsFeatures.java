package dev.amot.endshards.features;

import dev.amot.endshards.blocks.EndShardsBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

import static dev.amot.endshards.EndShards.modid;

public class EndShardsFeatures {
    private static final ConfiguredFeature<?, ?> SOUL_SCULK_CONFIGURED_FEATURE = new ConfiguredFeature<>(
            Feature.ORE, new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.SCULK),
                    EndShardsBlocks.SOUL_SCULK.getDefaultState(),
                    4
            )
    );
    public static final PlacedFeature SOUL_SCULK_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(SOUL_SCULK_CONFIGURED_FEATURE),
            List.of(
                    CountPlacementModifier.of(50),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))
            )
    );

    public static final Feature<DefaultFeatureConfig> STRANGE_CRYSTAL_FEATURE = new StrangeCrystalFeature(DefaultFeatureConfig.CODEC);
    private static final ConfiguredFeature<?, ?> STRANGE_CRYSTAL_CONFIGURED_FEATURE = new ConfiguredFeature<>(
            STRANGE_CRYSTAL_FEATURE,
            DefaultFeatureConfig.INSTANCE
    );
    public static final PlacedFeature STRANGE_CRYSTAL_PLACED_FEATURE = new PlacedFeature(
            RegistryEntry.of(STRANGE_CRYSTAL_CONFIGURED_FEATURE),
            List.of(
                    RandomOffsetPlacementModifier.horizontally(UniformIntProvider.create(3, 9)),
                    RarityFilterPlacementModifier.of(30)
            )
    );

    public static void register(){
        final Identifier SOUL_SCULK_ID = new Identifier(modid, "soul_sculk_ore");
        Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                SOUL_SCULK_ID,
                SOUL_SCULK_CONFIGURED_FEATURE
        );
        Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                SOUL_SCULK_ID,
                SOUL_SCULK_PLACED_FEATURE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, SOUL_SCULK_ID)
        );

        final Identifier STRANGE_CRYSTAL_ID = new Identifier(modid, "strange_crystal_feature");
        Registry.register(Registry.FEATURE, STRANGE_CRYSTAL_ID, STRANGE_CRYSTAL_FEATURE);
        Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                STRANGE_CRYSTAL_ID,
                STRANGE_CRYSTAL_CONFIGURED_FEATURE
        );
        Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                STRANGE_CRYSTAL_ID,
                STRANGE_CRYSTAL_PLACED_FEATURE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, STRANGE_CRYSTAL_ID)
        );
    }
}
