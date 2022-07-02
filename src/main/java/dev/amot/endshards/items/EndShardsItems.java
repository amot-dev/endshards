package dev.amot.endshards.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static dev.amot.endshards.EndShards.modid;

public class EndShardsItems {
    public static final Item ENDSHARD = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ENDER_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item FEAR_ESSENCE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item INFUSION_CORE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item INFUSION_CORE_ENDER = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item INFUSION_CORE_NETHERITE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item INFUSION_CORE_SCULK = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item INFUSION_CORE_NIGHTMARE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item NIGHTMARE_FUEL = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item NIGHTMARE_PEARL = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item PHANTOM_SOUL_FRAGMENT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item SCULK_GEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item SOUL_FRAGMENT = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item TERROR_EYES = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item WARDING_HEART = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(modid, "endshard"), ENDSHARD);
        Registry.register(Registry.ITEM, new Identifier(modid, "ender_ingot"), ENDER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(modid, "fear_essence"), FEAR_ESSENCE);
        Registry.register(Registry.ITEM, new Identifier(modid, "infusion_core"), INFUSION_CORE);
        Registry.register(Registry.ITEM, new Identifier(modid, "infusion_core_ender"), INFUSION_CORE_ENDER);
        Registry.register(Registry.ITEM, new Identifier(modid, "infusion_core_netherite"), INFUSION_CORE_NETHERITE);
        Registry.register(Registry.ITEM, new Identifier(modid, "infusion_core_sculk"), INFUSION_CORE_SCULK);
        Registry.register(Registry.ITEM, new Identifier(modid, "infusion_core_nightmare"), INFUSION_CORE_NIGHTMARE);
        Registry.register(Registry.ITEM, new Identifier(modid, "nightmare_fuel"), NIGHTMARE_FUEL);
        Registry.register(Registry.ITEM, new Identifier(modid, "nightmare_pearl"), NIGHTMARE_PEARL);
        Registry.register(Registry.ITEM, new Identifier(modid, "phantom_soul_fragment"), PHANTOM_SOUL_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(modid, "sculk_gem"), SCULK_GEM);
        Registry.register(Registry.ITEM, new Identifier(modid, "soul_fragment"), SOUL_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(modid, "terror_eyes"), TERROR_EYES);
        Registry.register(Registry.ITEM, new Identifier(modid, "warding_heart"), WARDING_HEART);
    }
}