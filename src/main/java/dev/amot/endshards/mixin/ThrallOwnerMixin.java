package dev.amot.endshards.mixin;

import dev.amot.endshards.util.IThrall;
import dev.amot.endshards.util.IThrallOwner;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.LinkedList;
import java.util.List;

@Mixin(PlayerEntity.class)
public class ThrallOwnerMixin implements IThrallOwner {
    @Unique
    private List<MobEntity> thralls = new LinkedList<>();

    @Unique
    public boolean addThrall(MobEntity thrall) {
        if (((IThrall)thrall).isThrall()) return false;

        ((IThrall)thrall).makeThrallFor((PlayerEntity)(Object)this);
        thrall.setGlowing(true);

        for (MobEntity ownedThrall : this.thralls) {
            ((IThrall)ownedThrall).clearActiveTarget();
        }
        this.thralls.add(thrall);
        return true;
    }

    @Unique
    public int getThrallCount() {
        this.thralls.removeIf(ownedThrall -> !ownedThrall.isAlive());
        return thralls.size();
    }
}
