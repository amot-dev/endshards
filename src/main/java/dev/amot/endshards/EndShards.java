package dev.amot.endshards;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final String modid = "endshards";
	public static final Logger LOGGER = LoggerFactory.getLogger("endshards");

	@Override
	public void onInitialize() {
		//Register items
		Registry.register(Registry.ITEM, new Identifier(modid, "endshard"), EnderGear.ENDSHARD);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_ingot"), EnderGear.ENDER_INGOT);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_helmet"), EnderGear.ENDER_HELMET);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_chestplate"), EnderGear.ENDER_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_leggings"), EnderGear.ENDER_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_boots"), EnderGear.ENDER_BOOTS);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_sword"), EnderGear.ENDER_SWORD);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_pickaxe"), EnderGear.ENDER_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_shovel"), EnderGear.ENDER_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_axe"), EnderGear.ENDER_AXE);
		Registry.register(Registry.ITEM, new Identifier(modid, "ender_hoe"), EnderGear.ENDER_HOE);
		Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "ender_cooldown"), EnderGear.ENDER_COOLDOWN);

		LOGGER.info("Mod loaded!");
	}
}
