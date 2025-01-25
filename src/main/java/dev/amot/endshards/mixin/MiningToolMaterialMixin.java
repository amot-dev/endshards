package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IMiningToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MiningToolItem.class)
public abstract class MiningToolMaterialMixin implements IMiningToolMaterial {
    @Unique
    private ToolMaterial toolMaterial;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void saveToolMaterial(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, Item.Settings settings, CallbackInfo ci) {
        this.toolMaterial = material;
    }

    @Unique
    public ToolMaterial endshards$getMaterial() {
        return this.toolMaterial;
    }
}
