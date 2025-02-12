package dev.amot.endshards.datagen;

import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.EndshardsItems;
import dev.amot.endshards.items.SculkGear;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EndshardsRecipeProvider extends FabricRecipeProvider {
    public EndshardsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            private void offerCompactionRecipe(RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
                offerReversibleCompactingRecipesWithReverseRecipeGroup(reverseCategory, baseItem, compactingCategory, compactItem, getItemPath(baseItem) + "_from_block", getItemPath(baseItem));
            }
            private void offerEnderUpgradeRecipe(RecipeCategory category, Item result, Item base) {
                SmithingTransformRecipeJsonBuilder.create(
                                Ingredient.ofItem(EndshardsItems.ENDER_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.ofItem(base),
                                Ingredient.ofItem(EndshardsItems.ENDER_INGOT_INFUSED),
                                category,
                                result
                        )
                        .criterion(hasItem(EndshardsItems.ENDER_INGOT_INFUSED), conditionsFromItem(EndshardsItems.ENDER_INGOT_INFUSED))
                        .criterion(hasItem(EndshardsItems.ENDER_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(EndshardsItems.ENDER_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, getItemPath(result));
            }
            private void offerNetheriteUpgradeRecipe(RecipeCategory category, Item result, Item base) {
                SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(base),
                        Ingredient.ofItem(EndshardsItems.NETHERITE_INGOT_INFUSED),
                        category,
                        result
                )
                        .criterion(hasItem(EndshardsItems.NETHERITE_INGOT_INFUSED), conditionsFromItem(EndshardsItems.NETHERITE_INGOT_INFUSED))
                        .criterion(hasItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, getItemPath(result));
            }

            private void offerSculkUpgradeRecipe(RecipeCategory category, Item result, Item base) {
                SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(EndshardsItems.SCULK_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(base),
                        Ingredient.ofItem(EndshardsItems.SCULK_GEM_INFUSED),
                        category,
                        result
                )
                        .criterion(hasItem(EndshardsItems.SCULK_GEM_INFUSED), conditionsFromItem(EndshardsItems.SCULK_GEM_INFUSED))
                        .criterion(hasItem(EndshardsItems.SCULK_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(EndshardsItems.SCULK_UPGRADE_SMITHING_TEMPLATE))
                        .offerTo(exporter, getItemPath(result));
            }

            @Override
            public void generate() {
                // Infusion Cores
                createShaped(RecipeCategory.MISC, EndshardsItems.INFUSION_CORE)
                        .pattern("ODO")
                        .pattern("DGD")
                        .pattern("ODO")
                        .input('O', Items.OBSIDIAN).criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .input('D', Items.DIAMOND).criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .input('G', Items.GLASS).criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, EndshardsItems.INFUSION_CORE_ENDER, 3)
                        .pattern("   ")
                        .pattern("CIC")
                        .pattern(" C ")
                        .input('C', EndshardsItems.INFUSION_CORE).criterion(hasItem(EndshardsItems.INFUSION_CORE), conditionsFromItem(EndshardsItems.INFUSION_CORE))
                        .input('I', Items.DRAGON_BREATH).criterion(hasItem(Items.DRAGON_BREATH), conditionsFromItem(Items.DRAGON_BREATH))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, EndshardsItems.INFUSION_CORE_NETHERITE, 3)
                        .pattern("   ")
                        .pattern("CIC")
                        .pattern(" C ")
                        .input('C', EndshardsItems.INFUSION_CORE).criterion(hasItem(EndshardsItems.INFUSION_CORE), conditionsFromItem(EndshardsItems.INFUSION_CORE))
                        .input('I', Items.NETHER_STAR).criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, EndshardsItems.INFUSION_CORE_SCULK, 3)
                        .pattern("   ")
                        .pattern("CIC")
                        .pattern(" C ")
                        .input('C', EndshardsItems.INFUSION_CORE).criterion(hasItem(EndshardsItems.INFUSION_CORE), conditionsFromItem(EndshardsItems.INFUSION_CORE))
                        .input('I', EndshardsItems.WARDING_HEART).criterion(hasItem(EndshardsItems.WARDING_HEART), conditionsFromItem(EndshardsItems.WARDING_HEART))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, EndshardsItems.INFUSION_CORE_NIGHTMARE, 3)
                        .pattern("   ")
                        .pattern("CIC")
                        .pattern(" C ")
                        .input('C', EndshardsItems.INFUSION_CORE).criterion(hasItem(EndshardsItems.INFUSION_CORE), conditionsFromItem(EndshardsItems.INFUSION_CORE))
                        .input('I', EndshardsItems.TERROR_EYES).criterion(hasItem(EndshardsItems.TERROR_EYES), conditionsFromItem(EndshardsItems.TERROR_EYES))
                        .offerTo(exporter);

                // Ingots/Gems
                createShapeless(RecipeCategory.MISC, EndshardsItems.ENDER_INGOT)
                        .group(getItemPath(EndshardsItems.ENDER_INGOT))
                        .input(EndshardsItems.ENDSHARD, 4).criterion(hasItem(EndshardsItems.ENDSHARD), conditionsFromItem(EndshardsItems.ENDSHARD))
                        .input(Items.POPPED_CHORUS_FRUIT, 4).criterion(hasItem(Items.POPPED_CHORUS_FRUIT), conditionsFromItem(Items.POPPED_CHORUS_FRUIT))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, EndshardsItems.SCULK_GEM)
                        .group(getItemPath(EndshardsItems.SCULK_GEM))
                        .input(EndshardsItems.SOUL_FRAGMENT, 4).criterion(hasItem(EndshardsItems.SOUL_FRAGMENT), conditionsFromItem(EndshardsItems.SOUL_FRAGMENT))
                        .input(Items.REDSTONE, 4).criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, EndshardsItems.NIGHTMARE_PEARL)
                        .input(EndshardsItems.NIGHTMARE_FUEL, 4).criterion(hasItem(EndshardsItems.NIGHTMARE_FUEL), conditionsFromItem(EndshardsItems.NIGHTMARE_FUEL))
                        .input(EndshardsItems.FEAR_ESSENCE, 4).criterion(hasItem(EndshardsItems.FEAR_ESSENCE), conditionsFromItem(EndshardsItems.FEAR_ESSENCE))
                        .offerTo(exporter);

                // Storage Blocks
                offerCompactionRecipe(RecipeCategory.MISC, EndshardsItems.ENDER_INGOT, RecipeCategory.BUILDING_BLOCKS, EndshardsBlocks.ENDER_BLOCK);
                offerCompactionRecipe(RecipeCategory.MISC, EndshardsItems.SCULK_GEM, RecipeCategory.BUILDING_BLOCKS, EndshardsBlocks.SCULK_GEM_BLOCK);

                // Infused Ingots/Gems
                createShapeless(RecipeCategory.MISC, EndshardsItems.ENDER_INGOT_INFUSED)
                        .input(EndshardsItems.ENDER_INGOT, 1).criterion(hasItem(EndshardsItems.ENDER_INGOT), conditionsFromItem(EndshardsItems.ENDER_INGOT))
                        .input(EndshardsItems.INFUSION_CORE_ENDER, 1).criterion(hasItem(EndshardsItems.INFUSION_CORE_ENDER), conditionsFromItem(EndshardsItems.INFUSION_CORE_ENDER))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, EndshardsItems.NETHERITE_INGOT_INFUSED)
                        .input(Items.NETHERITE_INGOT, 1).criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .input(EndshardsItems.INFUSION_CORE_NETHERITE, 1).criterion(hasItem(EndshardsItems.INFUSION_CORE_NETHERITE), conditionsFromItem(EndshardsItems.INFUSION_CORE_NETHERITE))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, EndshardsItems.SCULK_GEM_INFUSED)
                        .input(EndshardsItems.SCULK_GEM, 1).criterion(hasItem(EndshardsItems.SCULK_GEM), conditionsFromItem(EndshardsItems.SCULK_GEM))
                        .input(EndshardsItems.INFUSION_CORE_SCULK, 1).criterion(hasItem(EndshardsItems.INFUSION_CORE_SCULK), conditionsFromItem(EndshardsItems.INFUSION_CORE_SCULK))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, EndshardsItems.NIGHTMARE_PEARL_INFUSED)
                        .input(EndshardsItems.NIGHTMARE_PEARL, 1).criterion(hasItem(EndshardsItems.NIGHTMARE_PEARL), conditionsFromItem(EndshardsItems.NIGHTMARE_PEARL))
                        .input(EndshardsItems.INFUSION_CORE_NIGHTMARE, 1).criterion(hasItem(EndshardsItems.INFUSION_CORE_NIGHTMARE), conditionsFromItem(EndshardsItems.INFUSION_CORE_NIGHTMARE))
                        .offerTo(exporter);

                // Un-Infusion
                createShapeless(RecipeCategory.MISC, EndshardsItems.ENDER_INGOT)
                        .group(getItemPath(EndshardsItems.ENDER_INGOT))
                        .input(EndshardsItems.ENDER_INGOT_INFUSED, 1).criterion(hasItem(EndshardsItems.ENDER_INGOT_INFUSED), conditionsFromItem(EndshardsItems.ENDER_INGOT_INFUSED))
                        .offerTo(exporter, getItemPath(EndshardsItems.ENDER_INGOT) + "_from_infused");
                createShapeless(RecipeCategory.MISC, Items.NETHERITE_INGOT)
                        .group(getItemPath(Items.NETHERITE_INGOT))
                        .input(EndshardsItems.NETHERITE_INGOT_INFUSED, 1).criterion(hasItem(EndshardsItems.NETHERITE_INGOT_INFUSED), conditionsFromItem(EndshardsItems.NETHERITE_INGOT_INFUSED))
                        .offerTo(exporter, getItemPath(Items.NETHERITE_INGOT) + "_from_infused");
                createShapeless(RecipeCategory.MISC, EndshardsItems.SCULK_GEM)
                        .group(getItemPath(EndshardsItems.SCULK_GEM))
                        .input(EndshardsItems.SCULK_GEM_INFUSED, 1).criterion(hasItem(EndshardsItems.SCULK_GEM_INFUSED), conditionsFromItem(EndshardsItems.SCULK_GEM_INFUSED))
                        .offerTo(exporter, getItemPath(EndshardsItems.SCULK_GEM) + "_from_infused");
                createShapeless(RecipeCategory.MISC, EndshardsItems.NIGHTMARE_PEARL)
                        .group(getItemPath(EndshardsItems.NIGHTMARE_PEARL))
                        .input(EndshardsItems.NIGHTMARE_PEARL_INFUSED, 1).criterion(hasItem(EndshardsItems.NIGHTMARE_PEARL_INFUSED), conditionsFromItem(EndshardsItems.NIGHTMARE_PEARL_INFUSED))
                        .offerTo(exporter, getItemPath(EndshardsItems.NIGHTMARE_PEARL) + "_from_infused");

                // Ender Gear
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderGear.ENDER_HELMET, Items.DIAMOND_HELMET);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderGear.ENDER_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderGear.ENDER_LEGGINGS, Items.DIAMOND_LEGGINGS);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderGear.ENDER_BOOTS, Items.DIAMOND_BOOTS);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderGear.ENDER_SWORD, Items.DIAMOND_SWORD);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderGear.ENDER_PICKAXE, Items.DIAMOND_PICKAXE);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderGear.ENDER_SHOVEL, Items.DIAMOND_SHOVEL);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderGear.ENDER_AXE, Items.DIAMOND_AXE);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderGear.ENDER_HOE, Items.DIAMOND_HOE);

                // Netherite Gear still manually added to overwrite

                // Sculk Gear
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkGear.SCULK_HELMET, Items.DIAMOND_HELMET);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkGear.SCULK_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkGear.SCULK_LEGGINGS, Items.DIAMOND_LEGGINGS);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkGear.SCULK_BOOTS, Items.DIAMOND_BOOTS);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkGear.SCULK_SWORD, Items.DIAMOND_SWORD);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkGear.SCULK_PICKAXE, Items.DIAMOND_PICKAXE);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkGear.SCULK_SHOVEL, Items.DIAMOND_SHOVEL);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkGear.SCULK_AXE, Items.DIAMOND_AXE);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkGear.SCULK_HOE, Items.DIAMOND_HOE);
            }
        };
    }

    @Override
    public String getName() {
        return "EndshardsRecipeProviderRecipeProvider";
    }
}
