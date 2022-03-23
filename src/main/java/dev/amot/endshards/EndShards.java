package dev.amot.endshards;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final String modid = "endshards";
	public static final Logger LOGGER = LoggerFactory.getLogger("endshards");

	@Override
	public void onInitialize() {
		EnderGear.register();
		LOGGER.info("Mod loaded!");
	}
}
