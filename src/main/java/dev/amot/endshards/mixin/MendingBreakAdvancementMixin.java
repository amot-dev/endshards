package dev.amot.endshards.mixin;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.SculkEquipment;
import dev.amot.endshards.util.IMiningToolMaterial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class MendingBreakAdvancementMixin {
    @Inject(method = "onDurabilityChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    public void triggerSculkToolMendingBreakAdvancement(int damage, ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        // Check if item being broken is a mining tool
        Item thisAsItem = ((ItemStack)(Object)this).getItem();
        if (thisAsItem instanceof MiningToolItem toolInHand) {
            // Check if tool being broken is Sculk
            if (((IMiningToolMaterial)toolInHand).endshards$getMaterial() == SculkEquipment.SCULK_TOOL_MATERIAL) {
                // Only trigger achievement if Mending is on the tool
                Registry<Enchantment> enchantmentRegistry = player.getServerWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                RegistryEntry<Enchantment> mendingEntry = enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.MENDING));
                if (EnchantmentHelper.getLevel(mendingEntry, ((ItemStack)(Object)this)) == 1) {
                    EndshardsCriteria.SCULK_TOOL_MENDING_BREAK.trigger(player);
                }
            }
        }
    }
}
