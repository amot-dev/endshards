package dev.amot.endshards.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static dev.amot.endshards.Endshards.modid;

public class RegistryHelper {

    public static Item registerItem(String id) {
        return registerItem(id, Item::new, new Item.Settings());
    }

    public static Item registerItem(String id, Function<Item.Settings, Item> factory) {
        return registerItem(id, factory, new Item.Settings());
    }

    public static Item registerItem(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(modid, id));
        return Registry.register(Registries.ITEM, key, factory.apply(settings.registryKey(key)));
    }

    public static Block registerBlock(String id, Function<Block.Settings, Block> factory) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, id));
        return registerBlock(id, factory, AbstractBlock.Settings.create().registryKey(blockKey), true);
    }

    public static Block registerBlock(String id, Function<Block.Settings, Block> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, id));
        return registerBlock(id, factory, settings.registryKey(blockKey), true);
    }

    public static Block registerBlock(String id, Function<Block.Settings, Block> factory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(modid, id));
        Block block = factory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(modid, id));
            Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemKey)));
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    @SafeVarargs
    public static void addToItemGroups(ItemConvertible item, RegistryKey<ItemGroup>... groups) {
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> itemGroup.add(item));
        }
    }
}
