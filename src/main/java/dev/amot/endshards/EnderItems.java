package dev.amot.endshards;

import dev.amot.endshards.armor.BaseArmorMaterial;
import dev.amot.endshards.armor.EnderArmorItem;
import dev.amot.endshards.blocks.EnderBlock;
import dev.amot.endshards.blocks.StrangeCrystal;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.features.StrangeCrystalFeature;
import dev.amot.endshards.tools.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;

import java.util.List;

import static dev.amot.endshards.EndShards.modid;

public class EnderItems {
    public static final Block STRANGE_CRYSTAL = new StrangeCrystal();
    public static final Feature<DefaultFeatureConfig> STRANGE_CRYSTAL_FEATURE = new StrangeCrystalFeature(DefaultFeatureConfig.CODEC);

    public static final Item ENDSHARD = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ENDER_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Block ENDER_BLOCK = new EnderBlock();

    public static final ArmorMaterial ENDER_ARMOR_MATERIAL = new BaseArmorMaterial(
            "ender", ENDER_INGOT, new int[] {3,6,8,3}, 37, 15, 3F, 1F, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC
    );
    public static final ToolMaterial ENDER_TOOL_MATERIAL = new BaseToolMaterial(
            ENDER_INGOT, 2032, 9F, MiningLevels.NETHERITE, 15
    );

    public static final Item ENDER_HELMET = new EnderArmorItem(EquipmentSlot.HEAD);
    public static final Item ENDER_CHESTPLATE = new EnderArmorItem(EquipmentSlot.CHEST);
    public static final Item ENDER_LEGGINGS = new EnderArmorItem(EquipmentSlot.LEGS);
    public static final Item ENDER_BOOTS = new EnderArmorItem(EquipmentSlot.FEET);

    public static final ToolItem ENDER_SWORD = new EnderSwordItem();
    public static final ToolItem ENDER_PICKAXE = new EnderPickaxeItem();
    public static final ToolItem ENDER_SHOVEL = new EnderShovelItem();
    public static final ToolItem ENDER_AXE = new EnderAxeItem();
    public static final ToolItem ENDER_HOE = new EnderHoeItem();

    public static final StatusEffect ENDER_COOLDOWN = new CooldownEffect();

    public static void register(){
        Registry.register(Registry.BLOCK, new Identifier(modid, "strange_crystal"), STRANGE_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(modid, "strange_crystal"),
                new BlockItem(STRANGE_CRYSTAL, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        //TODO: Figure out how to make strange crystals NOT generate in a grid
        Registry.register(Registry.FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE);
        ConfiguredFeature<?, ?> STRANGE_CRYSTAL_FEATURE_CONFIGURED = new ConfiguredFeature<>(STRANGE_CRYSTAL_FEATURE, DefaultFeatureConfig.INSTANCE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE_CONFIGURED);
        RegistryEntry<ConfiguredFeature<?, ?>> STRANGE_CRYSTAL_FEATURE_ENTRY = BuiltinRegistries.CONFIGURED_FEATURE
                .getOrCreateEntry(BuiltinRegistries.CONFIGURED_FEATURE.getKey(STRANGE_CRYSTAL_FEATURE_CONFIGURED).orElseThrow());
        PlacedFeature STRANGE_CRYSTAL_FEATURE_PLACED = new PlacedFeature(STRANGE_CRYSTAL_FEATURE_ENTRY, List.of(
                RandomOffsetPlacementModifier.of(ConstantIntProvider.create(16), ConstantIntProvider.create(0)),
                RarityFilterPlacementModifier.of(1)
        ));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(modid, "strange_crystal_feature"), STRANGE_CRYSTAL_FEATURE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, BuiltinRegistries.PLACED_FEATURE.getKey(STRANGE_CRYSTAL_FEATURE_PLACED).orElseThrow());

        Registry.register(Registry.ITEM, new Identifier(modid, "endshard"), ENDSHARD);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_ingot"), ENDER_INGOT);
        Registry.register(Registry.BLOCK, new Identifier(modid, "ender_block"), ENDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_block"),
                new BlockItem(ENDER_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier(modid, "ender_helmet"), ENDER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_chestplate"), ENDER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_leggings"), ENDER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_boots"), ENDER_BOOTS);

        Registry.register(Registry.ITEM, new Identifier(modid, "ender_sword"), ENDER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_pickaxe"), ENDER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_shovel"), ENDER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_axe"), ENDER_AXE);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_hoe"), ENDER_HOE);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "ender_cooldown"), ENDER_COOLDOWN);
    }
}
