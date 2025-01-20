package dev.amot.endshards.mixin;

import dev.amot.endshards.util.EndshardsGameRules;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerScreenHandler.class)
public class InventoryEquipmentSwitchMixin {
    @Inject(method = "transferSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EquipmentSlot;getType()Lnet/minecraft/entity/EquipmentSlot$Type;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    void startArmorSwitch(PlayerEntity player, int index, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack, Slot slot, ItemStack itemStack2, EquipmentSlot equipmentSlot) {
        // Only apply this if the gamerule is set
        // Need to handle this on both client and server sides
        if (EndshardsGameRules.doEasyArmorSwitchGamerule) {
            if (equipmentSlot.getType() == EquipmentSlot.Type.ARMOR) {
                Slot switchSlot = (((PlayerScreenHandler) (Object) this).slots.get(8 - equipmentSlot.getEntitySlotId()));

                // This block only runs if the equipment is armor and there is already an armor stack in the slot
                if (switchSlot.hasStack()) {

                    // Deep copy the equipped item stack, then delete it
                    int i = 8 - equipmentSlot.getEntitySlotId(); // Mojang magic number yay
                    ItemStack currentEquipped = switchSlot.getStack().copy();
                    switchSlot.setStack(ItemStack.EMPTY);

                    // Attempt to equip into the now-empty slot
                    IScreenHandlerInvoker invoker = (IScreenHandlerInvoker) this;
                    if (!invoker.invokeInsertItem(itemStack2, i, i + 1, false)) {
                        // Return early on fail as we don't want to allow other possible operations
                        cir.setReturnValue(ItemStack.EMPTY);
                        cir.cancel();
                    }

                    // Set the stack in the hand to what was previously equipped
                    slot.setStack(currentEquipped);
                    slot.markDirty(); // Presumably trigger a re-render

                    // Return early on success as we don't want to allow other possible operations
                    cir.setReturnValue(ItemStack.EMPTY);
                    cir.cancel();
                }
            }
        }
    }
}
