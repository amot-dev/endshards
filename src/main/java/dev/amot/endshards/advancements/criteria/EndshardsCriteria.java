package dev.amot.endshards.advancements.criteria;

import net.minecraft.advancement.criterion.Criteria;

public class EndshardsCriteria {
    public static final EndshardsCriterion ENDER_ARMOR_FALL_CRITERION = Criteria.register(new EndshardsCriterion("ender_armor_fall_trigger"));
    public static final EndshardsCriterion ENDER_SWORD_WARP_CRITERION = Criteria.register(new EndshardsCriterion("ender_sword_warp_trigger"));
    public static final EndshardsCriterion ENDER_TOOL_WARP_CRITERION = Criteria.register(new EndshardsCriterion("ender_tool_warp_trigger"));
    public static final EndshardsCriterion ENDER_ARMOR_PLAYED_SELF_CRITERION = Criteria.register(new EndshardsCriterion("ender_armor_played_self_trigger"));

    public static final EndshardsCriterion NETHERITE_ARMOR_PROTECT_CRITERION = Criteria.register(new EndshardsCriterion("netherite_armor_protect_trigger"));
    public static final EndshardsCriterion NETHERITE_SWORD_FLAME_CRITERION = Criteria.register(new EndshardsCriterion("netherite_sword_flame_trigger"));
    public static final EndshardsCriterion NETHERITE_TOOL_AUTOSMELT_CRITERION = Criteria.register(new EndshardsCriterion("netherite_tool_autosmelt_trigger"));
    public static final EndshardsCriterion NETHERITE_SWORD_SACRIFICE_CRITERION = Criteria.register(new EndshardsCriterion("netherite_sword_sacrifice_trigger"));

    public static final EndshardsCriterion SCULK_ARMOR_LIGHT = Criteria.register(new EndshardsCriterion("sculk_armor_light_trigger"));
    public static final EndshardsCriterion SCULK_SWORD_ENTHRALL = Criteria.register(new EndshardsCriterion("sculk_sword_enthrall_trigger"));
    public static final EndshardsCriterion SCULK_TOOL_XP = Criteria.register(new EndshardsCriterion("sculk_tool_xp_trigger"));
    public static final EndshardsCriterion SCULK_TOOL_MENDING_BREAK = Criteria.register(new EndshardsCriterion("sculk_tool_mending_break_trigger"));

    public static void init(){}
}
