package dev.amot.endshards.advancements.criteria;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static dev.amot.endshards.Endshards.modid;

public class EndshardsCriteria {
    // For some reason, Java doesn't like the below method of registering with a helper method, like everything else
    //public static final EndshardsCriterion ENDER_ARMOR_FALL_CRITERION = registerCriterion(Identifier.of(modid, "ender_armor_fall_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion ENDER_ARMOR_FALL_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "ender_armor_fall_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion ENDER_SWORD_WARP_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "ender_sword_warp_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion ENDER_TOOL_WARP_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "ender_tool_warp_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion ENDER_ARMOR_PLAYED_SELF_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "ender_armor_played_self_trigger"), new EndshardsCriterion());

    public static final EndshardsCriterion NETHERITE_ARMOR_PROTECT_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "netherite_armor_protect_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion NETHERITE_SWORD_FLAME_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "netherite_sword_flame_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion NETHERITE_TOOL_AUTOSMELT_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "netherite_tool_autosmelt_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion NETHERITE_SWORD_SACRIFICE_CRITERION = Registry.register(Registries.CRITERION, Identifier.of(modid, "netherite_sword_sacrifice_trigger"), new EndshardsCriterion());

    public static final EndshardsCriterion SCULK_ARMOR_LIGHT = Registry.register(Registries.CRITERION, Identifier.of(modid, "sculk_armor_light_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion SCULK_SWORD_ENTHRALL = Registry.register(Registries.CRITERION, Identifier.of(modid, "sculk_sword_enthrall_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion SCULK_TOOL_XP = Registry.register(Registries.CRITERION, Identifier.of(modid, "sculk_tool_xp_trigger"), new EndshardsCriterion());
    public static final EndshardsCriterion SCULK_TOOL_MENDING_BREAK = Registry.register(Registries.CRITERION, Identifier.of(modid, "sculk_tool_mending_break_trigger"), new EndshardsCriterion());

    public static void init(){}
}
