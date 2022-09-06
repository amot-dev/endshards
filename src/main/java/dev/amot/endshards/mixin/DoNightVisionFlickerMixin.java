package dev.amot.endshards.mixin;

import dev.amot.endshards.util.EndShardsGameRules;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class DoNightVisionFlickerMixin<T> {


    @Inject(method = "getNightVisionStrength", at = @At(value = "RETURN"), cancellable = true)
    private static void disableNightVisionFlicker(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> cir){
        if (!EndShardsGameRules.doNightVisionFlickerGamerule) {
            cir.setReturnValue(1.0F);
        }
    }
}
