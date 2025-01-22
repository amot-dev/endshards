package dev.amot.endshards.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class RegistryHelper {
    public static Item registerItem(Identifier id, Item item) {
        return Registry.register(Registries.ITEM, id, item);
    }

    public static Block registerBlock(Identifier id, Block block, boolean shouldRegisterItem) {
        // Sometimes, you may not want to register an item for the block
        if (shouldRegisterItem) {
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    @SafeVarargs
    public static void addToItemGroups(ItemConvertible item, RegistryKey<ItemGroup>... groups) {
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> itemGroup.add(item));
        }
    }
}
