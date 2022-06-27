package dev.amot.endshards.features;

import dev.amot.endshards.blocks.EndShardsBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.Arrays;
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
            Arrays.asList(
                    CountPlacementModifier.of(50),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))
            )
    );

    public static final Feature<DefaultFeatureConfig> STRANGE_CRYSTAL_FEATURE = new StrangeCrystalFeature(DefaultFeatureConfig.CODEC);

    public static void register(){
        Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier("endshards", "soul_sculk_ore"),
                SOUL_SCULK_CONFIGURED_FEATURE
        );
        Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                new Identifier("endshards", "soul_sculk_ore"),
                SOUL_SCULK_PLACED_FEATURE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("endshards", "soul_sculk_ore"))
        );

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
