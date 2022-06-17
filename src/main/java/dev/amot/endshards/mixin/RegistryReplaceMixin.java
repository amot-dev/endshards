package dev.amot.endshards.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.OptionalInt;

@Mixin(SimpleRegistry.class)
public abstract class RegistryReplaceMixin<T> {

    @Shadow @Final private Map<RegistryKey<T>, RegistryEntry.Reference<T>> keyToEntry;

    @Inject(method = "replace", at = @At(value = "INVOKE", target = "Ljava/util/Map;remove(Ljava/lang/Object;)Ljava/lang/Object;"))
    void injectReplaceMethod(OptionalInt rawId, RegistryKey<T> key, T newEntry, Lifecycle lifecycle, CallbackInfoReturnable<RegistryEntry<T>> cir){
        this.keyToEntry.remove(key);
    }
}
