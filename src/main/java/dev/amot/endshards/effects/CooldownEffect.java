package dev.amot.endshards.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CooldownEffect extends StatusEffect {
    private static final int color = 0x98D982;
    public CooldownEffect(){
        super(StatusEffectCategory.NEUTRAL, color);
    }
}
