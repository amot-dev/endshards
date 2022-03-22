package dev.amot.endshards.mixin;

import dev.amot.endshards.util.ISwitchArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorItem.class)
public abstract class SwitchArmorMixin implements ISwitchArmor {
    @Shadow public abstract ArmorMaterial getMaterial();
    @Shadow public abstract EquipmentSlot getSlotType();

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stackInHand);
        ItemStack equippedStack = user.getEquippedStack(equipmentSlot);
        if (equippedStack.isEmpty()) {
            user.equipStack(equipmentSlot, stackInHand.copy());
            stackInHand.setCount(0);
        }
        else {
            user.setStackInHand(hand, equippedStack);
            user.equipStack(equipmentSlot, stackInHand.copy());
        }
        if (!world.isClient()) {
            user.incrementStat(Stats.USED.getOrCreateStat(new ArmorItem(this.getMaterial(), this.getSlotType(), new Item.Settings())));
        }
        return TypedActionResult.success(stackInHand, world.isClient());
    }
}
