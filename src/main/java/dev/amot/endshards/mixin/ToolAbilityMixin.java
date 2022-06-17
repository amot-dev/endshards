package dev.amot.endshards.mixin;

import dev.amot.endshards.EnderItems;
import dev.amot.endshards.NetheriteItems;
import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.block.Block.*;

@Mixin(Block.class)
public abstract class ToolAbilityMixin implements dev.amot.endshards.util.IEnderToolAbility {

    @Shadow
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack) {
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void injectAfterBreakMethod(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
        if (itemInHand instanceof ToolItem toolInHand && toolInHand.getMaterial() == EnderItems.ENDER_TOOL_MATERIAL) {
            player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
            player.addExhaustion(0.005F);

            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, player, stack).forEach((stackX) -> {
                    if (!player.getInventory().insertStack(stackX)) {
                        Supplier<ItemEntity> itemEntitySupplier = () -> new ItemEntity(world, player.getEyePos().getX(),
                                player.getEyePos().getY()-1F, player.getEyePos().getZ(), stackX, 0, 0, 0);
                        dropStack(world, itemEntitySupplier, stackX);
                    }
                    else {
                        //TODO: Decide whether to increase pick up stats for inventory warps (note: this line only works on client)
                        //player.increaseStat(Stats.PICKED_UP.getOrCreateStat(stackX.getItem()), stackX.getCount());
                        player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1F, 1F);
                        if (player instanceof ServerPlayerEntity serverUser) EndShardsCriteria.ENDER_TOOL_ABILITY_USED.trigger(serverUser);
                    }
                });
                state.onStacksDropped((ServerWorld)world, pos, stack, true);
            }
            ci.cancel();
        }
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static void injectGetDroppedStacksMethod(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> smeltedDrops = new ArrayList<>();
        List<ItemStack> unsmeltedDrops = cir.getReturnValue();
        if (stack.getItem() instanceof ToolItem toolInHand && toolInHand.getMaterial() == NetheriteItems.NETHERITE_TOOL_MATERIAL) {
            for (ItemStack unsmeltedDrop : unsmeltedDrops) {
                Optional<SmeltingRecipe> recipe = world.getRecipeManager().listAllOfType(RecipeType.SMELTING).stream().filter((smeltingRecipe -> smeltingRecipe.getIngredients().get(0).test(unsmeltedDrop))).findFirst();
                if (recipe.isPresent()) {
                    ItemStack smeltedDrop = recipe.get().getOutput();
                    smeltedDrop.setCount(unsmeltedDrop.getCount());
                    smeltedDrops.add(smeltedDrop);
                }
                else smeltedDrops.add(unsmeltedDrop);
            }
            cir.setReturnValue(smeltedDrops);
        }
        else {
            cir.setReturnValue(unsmeltedDrops);
        }
    }
}
