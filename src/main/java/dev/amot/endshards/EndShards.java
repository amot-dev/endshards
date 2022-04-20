package dev.amot.endshards;

import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final String modid = "endshards";
	public static final Logger LOGGER = LoggerFactory.getLogger("Endshards");

	@Override
	public void onInitialize() {
		EndShardsCriteria.init();
		EnderItems.register();
		LOGGER.info("Mod loaded!");
	}
}
