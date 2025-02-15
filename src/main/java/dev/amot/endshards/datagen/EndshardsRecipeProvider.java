package dev.amot.endshards.datagen;

import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.items.EnderEquipment;
import dev.amot.endshards.items.EndshardsItems;
import dev.amot.endshards.items.SculkEquipment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static dev.amot.endshards.Endshards.modid;

public class EndshardsRecipeProvider extends FabricRecipeProvider {
    public EndshardsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            private RegistryKey<Recipe<?>> registerRecipe(String recipeId) {
                return RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(modid, recipeId));
            }

            private void offerInfusionCoreRecipe(ItemConvertible infusionCore, ItemConvertible powerItem) {
                createShaped(RecipeCategory.MISC, infusionCore, 3)
                        .pattern("   ")
                        .pattern("CIC")
                        .pattern(" C ")
                        .input('C', EndshardsItems.INFUSION_CORE).criterion(hasItem(EndshardsItems.INFUSION_CORE), conditionsFromItem(EndshardsItems.INFUSION_CORE))
                        .input('I', powerItem).criterion(hasItem(powerItem), conditionsFromItem(powerItem))
                        .offerTo(exporter);
            }

            private void offerCompactionRecipe(RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
                createShapeless(reverseCategory, baseItem, 9)
                        .group(getItemPath(baseItem))
                        .input(compactItem).criterion(hasItem(compactItem), this.conditionsFromItem(compactItem))
                        .offerTo(exporter, registerRecipe(getItemPath(baseItem) + "_from_block"));
                createShaped(compactingCategory, compactItem)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .input('#', baseItem).criterion(hasItem(baseItem), this.conditionsFromItem(baseItem))
                        .offerTo(exporter, registerRecipe(getItemPath(compactItem)));
            }

            private void offerInfusionRecipe(ItemConvertible infusedItem, ItemConvertible uninfusedItem, ItemConvertible infusionCore) {
                createShapeless(RecipeCategory.MISC, infusedItem)
                        .input(uninfusedItem, 1).criterion(hasItem(uninfusedItem), conditionsFromItem(uninfusedItem))
                        .input(infusionCore, 1).criterion(hasItem(infusionCore), conditionsFromItem(infusionCore))
                        .offerTo(exporter);
            }

            private void offerUninfusionRecipe(ItemConvertible uninfusedItem, ItemConvertible infusedItem) {
                createShapeless(RecipeCategory.MISC, uninfusedItem)
                        .group(getItemPath(uninfusedItem))
                        .input(infusedItem, 1).criterion(hasItem(infusedItem), conditionsFromItem(infusedItem))
                        .offerTo(exporter, registerRecipe(getItemPath(uninfusedItem) + "_from_infused"));
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
                        .offerTo(exporter, registerRecipe(getItemPath(result)));
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
                        .offerTo(exporter, registerRecipe(getItemPath(result)));
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
                offerInfusionCoreRecipe(EndshardsItems.INFUSION_CORE_ENDER, Items.DRAGON_BREATH);
                offerInfusionCoreRecipe(EndshardsItems.INFUSION_CORE_NETHERITE, Items.NETHER_STAR);
                offerInfusionCoreRecipe(EndshardsItems.INFUSION_CORE_SCULK, EndshardsItems.WARDING_HEART);
                offerInfusionCoreRecipe(EndshardsItems.INFUSION_CORE_NIGHTMARE, EndshardsItems.TERROR_EYES);

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
                offerInfusionRecipe(EndshardsItems.ENDER_INGOT_INFUSED, EndshardsItems.ENDER_INGOT, EndshardsItems.INFUSION_CORE_ENDER);
                offerInfusionRecipe(EndshardsItems.NETHERITE_INGOT_INFUSED, Items.NETHERITE_INGOT, EndshardsItems.INFUSION_CORE_NETHERITE);
                offerInfusionRecipe(EndshardsItems.SCULK_GEM_INFUSED, EndshardsItems.SCULK_GEM, EndshardsItems.INFUSION_CORE_SCULK);
                offerInfusionRecipe(EndshardsItems.NIGHTMARE_PEARL_INFUSED, EndshardsItems.NIGHTMARE_PEARL, EndshardsItems.INFUSION_CORE_NIGHTMARE);

                // Un-Infusion
                offerUninfusionRecipe(EndshardsItems.ENDER_INGOT, EndshardsItems.ENDER_INGOT_INFUSED);
                offerUninfusionRecipe(Items.NETHERITE_INGOT, EndshardsItems.NETHERITE_INGOT_INFUSED);
                offerUninfusionRecipe(EndshardsItems.SCULK_GEM, EndshardsItems.SCULK_GEM_INFUSED);
                offerUninfusionRecipe(EndshardsItems.NIGHTMARE_PEARL, EndshardsItems.NIGHTMARE_PEARL_INFUSED);

                // Smithing Template Duplication
                offerSmithingTemplateCopyingRecipe(EndshardsItems.ENDER_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);
                offerSmithingTemplateCopyingRecipe(EndshardsItems.SCULK_UPGRADE_SMITHING_TEMPLATE, Items.SCULK);

                // Ender Equipment
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderEquipment.ENDER_HELMET, Items.DIAMOND_HELMET);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderEquipment.ENDER_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderEquipment.ENDER_LEGGINGS, Items.DIAMOND_LEGGINGS);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderEquipment.ENDER_BOOTS, Items.DIAMOND_BOOTS);
                offerEnderUpgradeRecipe(RecipeCategory.COMBAT, EnderEquipment.ENDER_SWORD, Items.DIAMOND_SWORD);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderEquipment.ENDER_PICKAXE, Items.DIAMOND_PICKAXE);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderEquipment.ENDER_SHOVEL, Items.DIAMOND_SHOVEL);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderEquipment.ENDER_AXE, Items.DIAMOND_AXE);
                offerEnderUpgradeRecipe(RecipeCategory.TOOLS, EnderEquipment.ENDER_HOE, Items.DIAMOND_HOE);

                // Netherite Equipment still manually added to overwrite

                // Sculk Equipment
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkEquipment.SCULK_HELMET, Items.DIAMOND_HELMET);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkEquipment.SCULK_CHESTPLATE, Items.DIAMOND_CHESTPLATE);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkEquipment.SCULK_LEGGINGS, Items.DIAMOND_LEGGINGS);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkEquipment.SCULK_BOOTS, Items.DIAMOND_BOOTS);
                offerSculkUpgradeRecipe(RecipeCategory.COMBAT, SculkEquipment.SCULK_SWORD, Items.DIAMOND_SWORD);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkEquipment.SCULK_PICKAXE, Items.DIAMOND_PICKAXE);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkEquipment.SCULK_SHOVEL, Items.DIAMOND_SHOVEL);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkEquipment.SCULK_AXE, Items.DIAMOND_AXE);
                offerSculkUpgradeRecipe(RecipeCategory.TOOLS, SculkEquipment.SCULK_HOE, Items.DIAMOND_HOE);
            }
        };
    }

    @Override
    public String getName() {
        return "EndshardsRecipeProvider";
    }
}
