package dev.amot.endshards.util;

import dev.amot.endshards.items.EndShardsItems;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.tools.SculkSwordItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;

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

        // Add Soul Fragment to all hostile mob loot tables killed by Sculk Sword
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Get entity ID in simple form (eg. "husk")
            String entityTypeId = id.toString().substring(id.toString().lastIndexOf("/") + 1);
            // If an entity matching that ID exists, and it's a monster, modify loot table
            if (EntityType.get(entityTypeId).isPresent()) {
                if (EntityType.get(entityTypeId).get().getSpawnGroup() == SpawnGroup.MONSTER) {
                    LootPool pool = LootPool.builder()
                            .with(ItemEntry.builder(EndShardsItems.SOUL_FRAGMENT))
                            .conditionally(EntityPropertiesLootCondition.builder(
                                    LootContext.EntityTarget.DIRECT_KILLER,
                                    EntityPredicate.Builder.create().equipment(
                                            EntityEquipmentPredicate.Builder.create().mainhand(
                                                    ItemPredicate.Builder.create().items(SculkGear.SCULK_SWORD).build()
                                            ).build()
                                    ).build()
                            ))
                            .conditionally(RandomChanceWithLootingLootCondition.builder(
                                    SculkSwordItem.SoulFragmentDropChance,
                                    SculkSwordItem.SoulFragmentLootingMultiplier
                            ))
                            .build();
                    tableBuilder.pool(pool);
                }
            }
        });
    }
}
