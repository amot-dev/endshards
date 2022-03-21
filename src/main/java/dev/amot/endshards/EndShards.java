package dev.amot.endshards;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndShards implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("endshards");

	//Create items
	public static final Item ENDSHARD = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
	public static final Item ENDER_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

	//Create armor
	public static final ArmorMaterial ENDER_ARMOR_MATERIAL = new EnderArmorMaterial();
	public static final Item ENDER_HELMET = new ArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item ENDER_CHESTPLATE = new ArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item ENDER_LEGGINGS = new ArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item ENDER_BOOTS = new ArmorItem(ENDER_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	@Override
	public void onInitialize() {
		//Register items
		Registry.register(Registry.ITEM, new Identifier("endshards", "endshard"), ENDSHARD);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_ingot"), ENDER_INGOT);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_helmet"), ENDER_HELMET);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_chestplate"), ENDER_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_leggings"), ENDER_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("endshards", "ender_boots"), ENDER_BOOTS);

		LOGGER.info("Mod loaded!");
	}
}
