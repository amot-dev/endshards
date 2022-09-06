package dev.amot.endshards.mixin;

import dev.amot.endshards.items.EnderGear;
import dev.amot.endshards.items.NetheriteGear;
import dev.amot.endshards.advancements.criteria.EndShardsCriteria;
import dev.amot.endshards.items.SculkGear;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
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
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.Supplier;

import static net.minecraft.block.Block.*;

@Mixin(Block.class)
public abstract class ToolAbilityMixin {
    private static Map<BlockPos, ServerPlayerEntity> playersWhoMinedBlockAtPos = new LinkedHashMap<>();

    @Shadow
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack) {
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void doEnderToolAbility(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        Item itemInHand = player.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
        // Only run for Ender Tools
        if (itemInHand instanceof ToolItem toolInHand && toolInHand.getMaterial() == EnderGear.ENDER_TOOL_MATERIAL && !(toolInHand instanceof SwordItem)) {
            // This only runs once as the method cancels early for Ender Tools
            player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
            player.addExhaustion(0.005F);

            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, player, stack).forEach((stackX) -> {
                    // Teleport items to player's feet if inventory is full
                    if (!player.getInventory().insertStack(stackX)) {
                        Supplier<ItemEntity> itemEntitySupplier = () -> new ItemEntity(world, player.getEyePos().getX(),
                                player.getEyePos().getY()-1F, player.getEyePos().getZ(), stackX, 0, 0, 0);
                        dropStack(world, itemEntitySupplier, stackX);
                    }
                    // Otherwise, just play pickup sound (items were inserted into inventory)
                    else {
                        //TODO: Decide whether to increase pick up stats for inventory warps (note: this line only works on client)
                        //player.increaseStat(Stats.PICKED_UP.getOrCreateStat(stackX.getItem()), stackX.getCount());
                        player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, player.getSoundCategory(), 1F, 1F);
                        if (player instanceof ServerPlayerEntity serverPlayer) EndShardsCriteria.ENDER_TOOL_WARP_CRITERION.trigger(serverPlayer);
                    }
                });
                state.onStacksDropped((ServerWorld)world, pos, stack, true);
            }
            ci.cancel();
        }
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static void doNetheriteToolAbility(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> smeltedDrops = new ArrayList<>();
        List<ItemStack> unsmeltedDrops = cir.getReturnValue();
        // Only run for Netherite Tools
        if (stack.getItem() instanceof ToolItem toolInHand && toolInHand.getMaterial() == NetheriteGear.NETHERITE_TOOL_MATERIAL && !(toolInHand instanceof SwordItem)) {
            for (ItemStack unsmeltedDrop : unsmeltedDrops) {
                // Smelt items if possible, else return unsmelted items
                Optional<SmeltingRecipe> recipe = world.getRecipeManager().listAllOfType(RecipeType.SMELTING).stream().filter((smeltingRecipe -> smeltingRecipe.getIngredients().get(0).test(unsmeltedDrop))).findFirst();
                if (recipe.isPresent()) {
                    ItemStack smeltedDrop = recipe.get().getOutput();
                    smeltedDrop.setCount(unsmeltedDrop.getCount());
                    smeltedDrops.add(smeltedDrop);

                    if (entity instanceof ServerPlayerEntity serverPlayer) {
                        EndShardsCriteria.NETHERITE_TOOL_AUTOSMELT_CRITERION.trigger(serverPlayer);
                    }
                }
                else smeltedDrops.add(unsmeltedDrop);
            }
            cir.setReturnValue(smeltedDrops);
        }
        else {
            cir.setReturnValue(unsmeltedDrops);
        }
    }

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;onStacksDropped(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Z)V"))
    private static void doSculkToolAbility(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo ci) {
        if (world instanceof ServerWorld serverWorld) {
            // Only run for Sculk Tools
            if (stack.getItem() instanceof ToolItem toolInHand && toolInHand.getMaterial() == SculkGear.SCULK_TOOL_MATERIAL && !(toolInHand instanceof SwordItem)) {
                // Ore Blocks already do their own XP handling
                if (!(state.getBlock() instanceof OreBlock)) {
                    // Don't want to trigger advancement for player unless XP is actually dropped
                    /*
                        This is a hacky way of doing this, but to prevent this triggering for the wrong player,
                        I map the position of the block being broken to the player. This is mainly because
                        dropStacks is static, which means I can't otherwise map the block being broken to the player
                     */
                    if (entity instanceof ServerPlayerEntity serverPlayer) {
                        playersWhoMinedBlockAtPos.put(pos, serverPlayer);
                    }

                    // Drop 0-1 XP for each block (weighted more to 0)
                    ((IBlockInvoker)state.getBlock()).invokeDropExperienceWhenMined(serverWorld, pos, stack, BiasedToBottomIntProvider.create(0, 1));
                }
            }
        }
    }

    @Inject(method = "dropExperienceWhenMined", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;dropExperience(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;I)V"))
    protected void triggerSculkToolXPAdvancement(ServerWorld world, BlockPos pos, ItemStack tool, IntProvider experience, CallbackInfo ci) {
        // If XP is dropped, trigger advancement for player who broke the block at pos
        if (playersWhoMinedBlockAtPos.containsKey(pos)) {
            EndShardsCriteria.SCULK_TOOL_XP.trigger(playersWhoMinedBlockAtPos.get(pos));
        }
    }

    @Inject(method = "dropExperienceWhenMined", at = @At("RETURN"))
    protected void clearTriggerForSculkToolXPAdvancement(ServerWorld world, BlockPos pos, ItemStack tool, IntProvider experience, CallbackInfo ci) {
        // Remove entry for position of broken block
        playersWhoMinedBlockAtPos.remove(pos);
    }
}
