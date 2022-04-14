package dev.amot.endshards.tools;

import dev.amot.endshards.EnderItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class EnderSwordItem extends SwordItem {
    public EnderSwordItem() {
        super(EnderItems.ENDER_TOOL_MATERIAL, 8, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.endshards.ender_sword.tooltip").formatted(Formatting.DARK_BLUE) );
    }

    private final List<EntityType<?>> EnderSwordAbilityBannedEntities = Arrays.asList(
            EntityType.ELDER_GUARDIAN,
            EntityType.ENDER_DRAGON,
            EntityType.ENDERMAN,
            EntityType.ENDERMITE,
            EntityType.RAVAGER,
            EntityType.SHULKER,
          //EntityType.WARDEN,
            EntityType.WITHER
            );


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.world instanceof ServerWorld) {
            if (!user.getActiveStatusEffects().containsKey(EnderItems.ENDER_COOLDOWN)) {
                if (entity.getType().getSpawnGroup() == SpawnGroup.MONSTER && !EnderSwordAbilityBannedEntities.contains(entity.getType())) {
                    entity.setPos(entity.getX(), -1000F, entity.getZ());
                    user.world.sendEntityStatus(entity, (byte)46);
                    user.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, user.getSoundCategory(), 1.0f, 1.0f);
                    user.addStatusEffect(new StatusEffectInstance(EnderItems.ENDER_COOLDOWN, 1200, 0, false, false, true));
                }
                //TODO: Play a teleport fail sound if entity does not match parameters
            }
        }
        return ActionResult.PASS;
    }
}
