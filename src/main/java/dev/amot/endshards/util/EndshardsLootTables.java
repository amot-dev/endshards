package dev.amot.endshards.util;

import dev.amot.endshards.items.EndshardsItems;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.tools.SculkSwordItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;

public class EndshardsLootTables {

    public static void init() {
        // Add Warding Heart drop to Warden
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (EntityType.WARDEN.getLootTableKey().isPresent() && EntityType.WARDEN.getLootTableKey().get().equals(key) && source.isBuiltin()) {
                LootPool pool = LootPool.builder()
                        .with(ItemEntry.builder(EndshardsItems.WARDING_HEART))
                        .build();

                tableBuilder.pool(pool);
            }
        });

        // Add Soul Fragment to all hostile mob loot tables killed by Sculk Sword
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            // Get entity ID in simple form (e.g. "husk")
            String entityTypeId = key.getValue().toString().substring(key.getValue().toString().lastIndexOf("/") + 1);

            // If an entity matching that ID exists, and it's a monster, modify loot table
            if (EntityType.get(entityTypeId).isPresent()) {
                if (EntityType.get(entityTypeId).get().getSpawnGroup() == SpawnGroup.MONSTER) {
                    LootPool pool = LootPool.builder()
                            .with(ItemEntry.builder(EndshardsItems.SOUL_FRAGMENT))
                            .conditionally(EntityPropertiesLootCondition.builder(
                                    LootContext.EntityTarget.DIRECT_ATTACKER,
                                    EntityPredicate.Builder.create().equipment(
                                            EntityEquipmentPredicate.Builder.create().mainhand(
                                                    ItemPredicate.Builder.create().items(registries.getOrThrow(RegistryKeys.ITEM), SculkGear.SCULK_SWORD)
                                            ).build()
                                    ).build()
                            ))
                            .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(
                                    registries,
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
