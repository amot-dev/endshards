package dev.amot.endshards.worldgen;

import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.features.EndshardsFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.*;

import static dev.amot.endshards.util.RegistryHelper.registerConfiguredFeature;

public class EndshardsConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SOUL_SCULK_CONFIGURED_KEY = registerConfiguredFeature("soul_sculk");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STRANGE_CRYSTAL_CONFIGURED_KEY = registerConfiguredFeature("strange_crystal");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        context.register(
                SOUL_SCULK_CONFIGURED_KEY,
                new ConfiguredFeature<>(
                        Feature.REPLACE_SINGLE_BLOCK,
                        new EmeraldOreFeatureConfig(
                                Blocks.SCULK.getDefaultState(), EndshardsBlocks.SOUL_SCULK.getDefaultState()
                        )
                )
        );
        context.register(
                STRANGE_CRYSTAL_CONFIGURED_KEY,
                new ConfiguredFeature<>(
                        EndshardsFeatures.STRANGE_CRYSTAL,
                        new DefaultFeatureConfig()
                )
        );
    }
}