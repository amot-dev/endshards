package dev.amot.endshards.mixin;

import dev.amot.endshards.advancements.criteria.EndshardsCriteria;
import dev.amot.endshards.items.SculkGear;
import dev.amot.endshards.util.IMiningToolMaterial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MendingBreakAdvancementMixin {
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(method = "sendEquipmentBreakStatus", at = @At("HEAD"))
    public void triggerSculkToolMendingBreakAdvancement(Item item, EquipmentSlot slot, CallbackInfo ci) {
        // Check if item being broken is a mining tool
        if ((LivingEntity)(Object)this instanceof ServerPlayerEntity serverPlayer && this.getEquippedStack(slot).getItem() instanceof MiningToolItem toolInHand) {
            // Check if tool being broken is Sculk
            if (((IMiningToolMaterial)toolInHand).endshards$getMaterial() == SculkGear.SCULK_TOOL_MATERIAL) {
                // Only trigger achievement if Mending is on the tool
                Registry<Enchantment> enchantmentRegistry = ((LivingEntity)(Object)this).getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
                RegistryEntry<Enchantment> mendingEntry = enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.MENDING));
                if (EnchantmentHelper.getLevel(mendingEntry, this.getEquippedStack(slot)) == 1) {
                    EndshardsCriteria.SCULK_TOOL_MENDING_BREAK.trigger(serverPlayer);
                }
            }
        }
    }
}
