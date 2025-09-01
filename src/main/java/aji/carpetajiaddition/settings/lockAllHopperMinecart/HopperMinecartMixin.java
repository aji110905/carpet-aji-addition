package aji.carpetajiaddition.settings.lockAllHopperMinecart;

import aji.carpetajiaddition.CarpetAjiAdditionSettings;
import net.minecraft.block.entity.Hopper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperMinecartEntity.class)
public abstract class HopperMinecartMixin extends StorageMinecartEntity implements Hopper {
    protected HopperMinecartMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isEnabled", at = @At("HEAD"), cancellable = true)
    private void isEnabled(CallbackInfoReturnable<Boolean> cir) {
        if (CarpetAjiAdditionSettings.lockAllHopperMinecart) {
            cir.setReturnValue(false);
        }
    }
}
