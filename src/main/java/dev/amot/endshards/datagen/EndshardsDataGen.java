package dev.amot.endshards.datagen;

import dev.amot.endshards.worldgen.EndshardsConfiguredFeatures;
import dev.amot.endshards.worldgen.EndshardsPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class EndshardsDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(EndshardsAdvancementProvider::new);
        pack.addProvider(EndshardsRecipeProvider::new);
        pack.addProvider(EndshardsWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, EndshardsConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, EndshardsPlacedFeatures::bootstrap);
    }
}