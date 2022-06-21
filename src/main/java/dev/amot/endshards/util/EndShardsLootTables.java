package dev.amot.endshards.util;

import dev.amot.endshards.items.EndShardsItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;

public class EndShardsLootTables {

    public static void init() {
        // Add Warding Heart drop to Warden
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (EntityType.WARDEN.getLootTableId().equals(id)) {
                LootPool pool = LootPool.builder()
                        .with(ItemEntry.builder(EndShardsItems.WARDING_HEART))
                        .build();

                tableBuilder.pool(pool);
            }
        });
    }
}
