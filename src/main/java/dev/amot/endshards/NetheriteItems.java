package dev.amot.endshards;

import com.mojang.serialization.Lifecycle;
import dev.amot.endshards.effects.CooldownEffect;
import dev.amot.endshards.tools.*;
import dev.amot.endshards.util.EndShardsMiningLevels;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

import java.util.OptionalInt;

import static dev.amot.endshards.EndShards.modid;

public class NetheriteItems {
    public static final ToolMaterial NETHERITE_TOOL_MATERIAL = new BaseToolMaterial(
            Items.NETHERITE_INGOT, 2032, 9.0F, EndShardsMiningLevels.NETHERITE, 4.0F, 15
    );

    //public static final ToolItem NETHERITE_SWORD = new NetheriteSwordItem();
    //public static final ToolItem NETHERITE_PICKAXE = new NetheritePickaxeItem();
    //public static final ToolItem NETHERITE_SHOVEL = new NetheriteShovelItem();
    // public static final ToolItem NETHERITE_AXE = new NetheriteAxeItem();
    //public static final ToolItem NETHERITE_HOE = new NetheriteHoeItem();

    public static final int NETHERITE_COOLDOWN_DURATION_ARMOR = 1200;
    public static final int NETHERITE_COOLDOWN_DURATION_SWORD = 600;
    public static final StatusEffect NETHERITE_COOLDOWN = new CooldownEffect();

    public static void register(){
        //Registry.register(Registry.ITEM, new Identifier("minecraft", "netherite_sword"), NETHERITE_SWORD);
        //Registry.register(Registry.ITEM, new Identifier("minecraft", "netherite_pickaxe"), NETHERITE_PICKAXE);
        //Registry.register(Registry.ITEM, new Identifier("minecraft", "netherite_shovel"), NETHERITE_SHOVEL);
        //((MutableRegistry<Item>)Registry.ITEM).replace(
        //        OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_AXE)),
        //        Registry.ITEM.getKey(Items.NETHERITE_AXE).orElse(null),
        //        Items.NETHERITE_AXE,
        //        Lifecycle.stable()
        //);
        //Registry.register(Registry.ITEM, new Identifier("minecraft", "netherite_hoe"), NETHERITE_HOE);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "netherite_cooldown"), NETHERITE_COOLDOWN);
    }
}

