package dev.amot.endshards;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.blocks.EndshardsBlocks;
import dev.amot.endshards.features.EndshardsFeatures;
import dev.amot.endshards.worldgen.EndshardsBiomeModifications;
import dev.amot.endshards.items.EndshardsItems;
import dev.amot.endshards.items.EnderEquipment;
import dev.amot.endshards.items.NetheriteEquipment;
import dev.amot.endshards.items.SculkEquipment;
import dev.amot.endshards.util.EndshardsGameRules;
import dev.amot.endshards.util.EndshardsModifiedLootTables;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Endshards implements ModInitializer {
	public static final String modid = "endshards";
	public static final Logger LOGGER = LoggerFactory.getLogger("Endshards");

	@Override
	public void onInitialize() {
		EndshardsGameRules.init();
		EndshardsCriteria.init();
		EndshardsItems.init();
		EndshardsBlocks.init();
		EndshardsFeatures.init();
		EndshardsBiomeModifications.init();
		EnderEquipment.init();
		NetheriteEquipment.init();
		SculkEquipment.init();
		EndshardsModifiedLootTables.init();
		LOGGER.info("Mod loaded!");
	}
}