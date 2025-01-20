package dev.amot.endshards.mixin;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ScreenHandler.class)
public interface IScreenHandlerInvoker {
    @Invoker("insertItem")
    boolean invokeInsertItem(ItemStack stack, int startIndex, int endIndex, boolean reverse);
}