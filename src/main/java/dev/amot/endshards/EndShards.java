package dev.amot.endshards;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("endshards");

	//Create items
	public static final Item endshard = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item ender_ingot = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

	@Override
	public void onInitialize() {
		//Register items
		Registry.register(Registry.ITEM, new Identifier("endshards", "endshard"), endshard);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_ingot"), ender_ingot);

		LOGGER.info("Mod loaded!");
	}
}
