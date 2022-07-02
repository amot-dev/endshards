package dev.amot.endshards.advancements.criteria;

import net.minecraft.advancement.criterion.Criteria;

public class EndShardsCriteria {
    public static final EndShardsCriterion ENDER_ARMOR_FALL_CRITERION = Criteria.register(new EndShardsCriterion("ender_armor_fall_trigger"));
    public static final EndShardsCriterion ENDER_SWORD_WARP_CRITERION = Criteria.register(new EndShardsCriterion("ender_sword_warp_trigger"));
    public static final EndShardsCriterion ENDER_TOOL_WARP_CRITERION = Criteria.register(new EndShardsCriterion("ender_tool_warp_trigger"));
    public static final EndShardsCriterion ENDER_ARMOR_PLAYED_SELF_CRITERION = Criteria.register(new EndShardsCriterion("ender_armor_played_self_trigger"));

    public static final EndShardsCriterion NETHERITE_ARMOR_PROTECT_CRITERION = Criteria.register(new EndShardsCriterion("netherite_armor_protect_trigger"));
    public static final EndShardsCriterion NETHERITE_SWORD_FLAME_CRITERION = Criteria.register(new EndShardsCriterion("netherite_sword_flame_trigger"));
    public static final EndShardsCriterion NETHERITE_TOOL_AUTOSMELT_CRITERION = Criteria.register(new EndShardsCriterion("netherite_tool_autosmelt_trigger"));
    public static final EndShardsCriterion NETHERITE_SWORD_SACRIFICE_CRITERION = Criteria.register(new EndShardsCriterion("netherite_sword_sacrifice_trigger"));

    public static final EndShardsCriterion SCULK_ARMOR_LIGHT = Criteria.register(new EndShardsCriterion("sculk_armor_light_trigger"));
    public static final EndShardsCriterion SCULK_SWORD_ENTHRALL = Criteria.register(new EndShardsCriterion("sculk_sword_enthrall_trigger"));
    public static final EndShardsCriterion SCULK_TOOL_XP = Criteria.register(new EndShardsCriterion("sculk_tool_xp_trigger"));
    //public static final EndShardsCriterion SCULK_CHALLENGE = Criteria.register(new EndShardsCriterion("sculk_challenge_trigger"));

    public static void init(){}
}
