package dev.amot.endshards;

import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import dev.amot.endshards.blocks.EndShardsBlocks;
import dev.amot.endshards.enchantments.EndShardsEnchantments;
import dev.amot.endshards.features.EndShardsFeatures;
import dev.amot.endshards.items.EndShardsItems;
import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.util.EndShardsGameRules;
import dev.amot.endshards.util.EndShardsLootTables;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final String modid = "endshards";
	public static final Logger LOGGER = LoggerFactory.getLogger("Endshards");

	@Override
	public void onInitialize() {
		EndShardsGameRules.init();
		EndShardsCriteria.init();
		EndShardsEnchantments.init();
		EndShardsItems.register();
		EndShardsBlocks.register();
		EndShardsFeatures.register();
		EnderGear.register();
		NetheriteGear.register();
		SculkGear.register();
		EndShardsLootTables.init();
		LOGGER.info("Mod loaded!");
	}
}
