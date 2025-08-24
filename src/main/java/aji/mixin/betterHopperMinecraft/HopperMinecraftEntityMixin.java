package aji.mixin.betterHopperMinecraft;

import aji.CarpetAjiAdditionSettings;
import net.minecraft.block.entity.Hopper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperMinecartEntity.class)
public abstract class HopperMinecraftEntityMixin extends StorageMinecartEntity implements Hopper {
    @Shadow
    private boolean enabled;

    protected HopperMinecraftEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (CarpetAjiAdditionSettings.betterHopperMinecart) {
            this.setGlowing(!enabled);
            return;
        }
        this.setGlowing(false);
    }
}
