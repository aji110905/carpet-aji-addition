package aji.settings.sitOnTheGround;

import aji.CarpetAjiAdditionSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Unique
    private boolean lastSneakState = false;
    @Unique
    private long firstSneakTimestamp = -1;
    @Unique
    private int sneakCount = 0;
    @Unique
    private Entity ridenEntity = null;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void onTick(CallbackInfo ci) {
        if (CarpetAjiAdditionSettings.sitOnTheGround) {
            boolean isSneaking = this.isSneaking();
            long currentTime = this.getWorld().getTime();

            if (ridenEntity != null && !this.hasVehicle()) {
                ridenEntity.discard();
                ridenEntity = null;
            }

            if (isSneaking && !lastSneakState) {
                if (sneakCount == 0) {
                    firstSneakTimestamp = currentTime;
                    sneakCount = 1;
                }
            } else if (!isSneaking && lastSneakState) {
                if (sneakCount >= 1 && sneakCount < 4) {
                    if (currentTime - firstSneakTimestamp <= 10) {
                        sneakCount++;
                    } else {
                        sneakCount = 0;
                        firstSneakTimestamp = -1;
                    }
                } else if (sneakCount == 4) {
                    if (!getWorld().isClient()) {
                        if (ridenEntity != null) {
                            ridenEntity.discard();
                            ridenEntity = null;
                        }
                        ArmorStandEntity armorStand = new ArmorStandEntity(this.getWorld(), this.getX(), this.getY() - 1.9, this.getZ());
                        ridenEntity = armorStand;
                        armorStand.setInvisible(true);
                        armorStand.setNoGravity(true);
                        armorStand.setInvulnerable(true);
                        armorStand.setCustomNameVisible(false);
                        this.getWorld().spawnEntity(armorStand);
                        this.startRiding(armorStand, true);
                    }
                    sneakCount = 0;
                    firstSneakTimestamp = -1;
                }
            }

            lastSneakState = isSneaking;
        }
    }
}
