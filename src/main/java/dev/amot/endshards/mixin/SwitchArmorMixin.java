package dev.amot.endshards.mixin;

import dev.amot.endshards.util.EndshardsGameRules;
import dev.amot.endshards.util.ISwitchArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorItem.class)
public abstract class SwitchArmorMixin implements ISwitchArmor {
    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    public void switchArmor(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        // Only do easy armor switch with gamerule
        if (world instanceof ServerWorld serverWorld && serverWorld.getGameRules().getBoolean(EndshardsGameRules.DO_EASY_ARMOR_SWITCH)) {
            // If right-click equip fails, do it anyway
            if (!cir.getReturnValue().getResult().isAccepted()) {
                ItemStack stackInHand = user.getStackInHand(hand);
                EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stackInHand);
                ItemStack equippedStack = user.getEquippedStack(equipmentSlot);

                user.setStackInHand(hand, equippedStack);
                user.equipStack(equipmentSlot, stackInHand.copy());

                user.incrementStat(Stats.USED.getOrCreateStat((ArmorItem)(Object)this));
                cir.setReturnValue(TypedActionResult.success(stackInHand, world.isClient()));
            }
        }
    }
}
