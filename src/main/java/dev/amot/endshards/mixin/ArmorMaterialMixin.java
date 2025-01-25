package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorItem.class)
public abstract class ArmorMaterialMixin implements IArmorMaterial {
    @Unique
    private ArmorMaterial armorMaterial;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void saveArmorMaterial(ArmorMaterial material, EquipmentType type, Item.Settings settings, CallbackInfo ci) {
        this.armorMaterial = material;
    }

    @Unique
    public ArmorMaterial endshards$getMaterial() {
        return this.armorMaterial;
    }
}
