package dev.amot.endshards.advancements.criteria;

import net.minecraft.advancement.criterion.Criteria;

public class EndShardsCriteria {
    public static final CustomCriterion ENDER_ARMOR_ABILITY_USED_CRITICAL_DAMAGE = Criteria.register(new CustomCriterion("ender_armor_ability_critical_damage"));
    public static final CustomCriterion ENDER_SWORD_ABILITY_USED = Criteria.register(new CustomCriterion("ender_sword_ability"));
    public static final CustomCriterion ENDER_TOOL_ABILITY_USED = Criteria.register(new CustomCriterion("ender_tool_ability"));
    public static final CustomCriterion ENDER_COOLDOWN_FAIL = Criteria.register(new CustomCriterion("ender_cooldown_fail"));

    public static void init(){}
}
