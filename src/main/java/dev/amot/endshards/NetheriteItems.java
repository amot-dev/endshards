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
import net.minecraft.util.registry.RegistryKey;

import java.util.OptionalInt;

import static dev.amot.endshards.EndShards.modid;

public class NetheriteItems {
    public static final ToolMaterial NETHERITE_TOOL_MATERIAL = new BaseToolMaterial(
            Items.NETHERITE_INGOT, 2032, 9.0F, EndShardsMiningLevels.NETHERITE, 4.0F, 15
    );

    public static final ToolItem NETHERITE_SWORD = new NetheriteSwordItem();
    public static final ToolItem NETHERITE_PICKAXE = new NetheritePickaxeItem();
    public static final ToolItem NETHERITE_SHOVEL = new NetheriteShovelItem();
    public static final ToolItem NETHERITE_AXE = new NetheriteAxeItem();
    public static final ToolItem NETHERITE_HOE = new NetheriteHoeItem();

    public static final int NETHERITE_COOLDOWN_DURATION_ARMOR = 1200;
    public static final int NETHERITE_COOLDOWN_DURATION_SWORD = 600;
    public static final StatusEffect NETHERITE_COOLDOWN = new CooldownEffect();

    public static void register(){
        ((MutableRegistry<Item>)Registry.ITEM).replace(
                OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_SWORD)),
                RegistryKey.of(Registry.ITEM.getKey(), new Identifier("minecraft", "netherite_sword")),
                NETHERITE_SWORD,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registry.ITEM).replace(
                OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_PICKAXE)),
                RegistryKey.of(Registry.ITEM.getKey(), new Identifier("minecraft", "netherite_pickaxe")),
                NETHERITE_PICKAXE,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registry.ITEM).replace(
                OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_SHOVEL)),
                RegistryKey.of(Registry.ITEM.getKey(), new Identifier("minecraft", "netherite_shovel")),
                NETHERITE_SHOVEL,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registry.ITEM).replace(
                OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_AXE)),
                RegistryKey.of(Registry.ITEM.getKey(), new Identifier("minecraft", "netherite_axe")),
                NETHERITE_AXE,
                Lifecycle.stable()
        );
        ((MutableRegistry<Item>)Registry.ITEM).replace(
                OptionalInt.of(Registry.ITEM.getRawId(Items.NETHERITE_HOE)),
                RegistryKey.of(Registry.ITEM.getKey(), new Identifier("minecraft", "netherite_hoe")),
                NETHERITE_HOE,
                Lifecycle.stable()
        );

        Registry.register(Registry.STATUS_EFFECT, new Identifier(modid, "netherite_cooldown"), NETHERITE_COOLDOWN);
    }
}

