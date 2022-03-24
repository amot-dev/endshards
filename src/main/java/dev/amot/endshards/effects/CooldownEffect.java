package dev.amot.endshards.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CooldownEffect extends StatusEffect {
    public CooldownEffect(){
        super(StatusEffectCategory.NEUTRAL, 0x98D982);
    }
}
