package dev.amot.endshards.tools;

import dev.amot.endshards.EndShards;
import dev.amot.endshards.EnderGear;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.Arrays;
import java.util.List;

public class EnderSwordItem extends SwordItem {
    public EnderSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private final List<EntityType<?>> EnderSwordAbilityBannedEntities = Arrays.asList(
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDER_DRAGON,
            EntityType.RAVAGER,
            EntityType.SHULKER,
          //EntityType.WARDEN,
            EntityType.WITHER
            );

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.world.isClient()) {
            if (!user.getActiveStatusEffects().containsKey(EnderGear.ENDER_COOLDOWN)) {
                if (entity.getType().getSpawnGroup() == SpawnGroup.MONSTER) {
                    if (!EnderSwordAbilityBannedEntities.contains(entity.getType())) {
                        entity.setPos(entity.getX(), -1000F, entity.getZ());
                        user.world.sendEntityStatus(entity, (byte)46);
                        user.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, user.getSoundCategory(), 1.0f, 1.0f);
                        user.addStatusEffect(new StatusEffectInstance(EnderGear.ENDER_COOLDOWN, 1200, 0, false, false, true));EndShards.LOGGER.info("Entity teleported");
                    }
                }
            }
        }
        return ActionResult.PASS;
    }
}
