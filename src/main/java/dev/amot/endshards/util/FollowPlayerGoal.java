package dev.amot.endshards.util;

import java.util.EnumSet;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FollowPlayerGoal extends Goal {
    private final MobEntity mob;
    private final PlayerEntity player;
    private final WorldView world;
    private final double speed;
    private final EntityNavigation navigation;
    private int updateCountdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private float oldWaterPathfindingPenalty;

    public FollowPlayerGoal(MobEntity mob, PlayerEntity player, double speed, float minDistance, float maxDistance) {
        this.mob = mob;
        this.world = mob.getWorld();
        this.navigation = mob.getNavigation();
        this.speed = speed;
        this.player = player;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        if (!(mob.getNavigation() instanceof MobNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowPlayerGoal");
        }
    }

    public boolean canStart() {
        if (this.player == null) {
            return false;
        } else if (this.player.isSpectator()) {
            return false;
        } else return !(this.mob.squaredDistanceTo(this.player) < (double) (this.minDistance * this.minDistance));
    }

    public boolean shouldContinue() {
        if (this.navigation.isIdle()) {
            return false;
        } else {
            return !(this.mob.squaredDistanceTo(this.player) <= (double)(this.maxDistance * this.maxDistance));
        }
    }

    public void start() {
        this.updateCountdownTicks = 0;
        this.oldWaterPathfindingPenalty = this.mob.getPathfindingPenalty(PathNodeType.WATER);
        this.mob.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public void stop() {
        this.navigation.stop();
        this.mob.setPathfindingPenalty(PathNodeType.WATER, this.oldWaterPathfindingPenalty);
    }

    public void tick() {
        this.mob.getLookControl().lookAt(this.player, 10.0F, (float)this.mob.getMaxLookPitchChange());
        if (--this.updateCountdownTicks <= 0) {
            this.updateCountdownTicks = this.getTickCount(10);
            if (!this.mob.hasVehicle()) {
                if (this.mob.squaredDistanceTo(this.player) >= 144.0D) {
                    this.tryTeleport();
                } else {
                    this.navigation.startMovingTo(this.player, this.speed);
                }

            }
        }
    }

    private void tryTeleport() {
        BlockPos blockPos = this.player.getBlockPos();

        for(int i = 0; i < 10; ++i) {
            int j = this.getRandomInt(-3, 3);
            int k = this.getRandomInt(-1, 1);
            int l = this.getRandomInt(-3, 3);
            boolean bl = this.tryTeleportTo(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l);
            if (bl) {
                return;
            }
        }

    }

    private boolean tryTeleportTo(int x, int y, int z) {
        if (Math.abs((double)x - this.player.getX()) < 2.0D && Math.abs((double)z - this.player.getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
            return false;
        } else {
            this.mob.refreshPositionAndAngles((double)x + 0.5D, y, (double)z + 0.5D, this.mob.getYaw(), this.mob.getPitch());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos pos) {
        PathNodeType pathNodeType = LandPathNodeMaker.getLandNodeType(this.mob, pos.mutableCopy());
        if (pathNodeType != PathNodeType.WALKABLE) {
            return false;
        } else {
            BlockPos blockPos = pos.subtract(this.mob.getBlockPos());
            return this.world.isSpaceEmpty(this.mob, this.mob.getBoundingBox().offset(blockPos));
        }
    }

    private int getRandomInt(int min, int max) {
        return this.mob.getRandom().nextInt(max - min + 1) + min;
    }
}

