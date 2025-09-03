package aji.carpetajiaddition.mixin.rules.betterHopperMinecart;

import aji.carpetajiaddition.CarpetAjiAdditionSettings;
import net.minecraft.block.entity.Hopper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.entity.vehicle.StorageMinecartEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperMinecartEntity.class)
public abstract class HopperMinecartEntityMixin extends StorageMinecartEntity implements Hopper {
    @Shadow
    public abstract boolean isEnabled();

    protected HopperMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo ci) {
        if (CarpetAjiAdditionSettings.glowingHopperMinecart) {
            this.setGlowing(true);
            MinecraftServer server = this.getWorld().getServer();
            if (server != null) {
                if (isEnabled()) {
                    Team team = server.getScoreboard().getTeam("enabled_hopper_minecraft");
                    if (team == null) {
                        team = server.getScoreboard().addTeam("enabled_hopper_minecraft");
                        team.setColor(Formatting.WHITE);
                    }
                    server.getScoreboard().addScoreHolderToTeam(this.getUuid().toString(), team);
                }else {
                    Team team = server.getScoreboard().getTeam("Locked_hopper_minecraft");
                    if (team == null) {
                        team = server.getScoreboard().addTeam("Locked_hopper_minecraft");
                        team.setColor(Formatting.RED);
                    }
                    server.getScoreboard().addScoreHolderToTeam(this.getUuid().toString(), team);
                }
            }
            return;
        }
        this.setGlowing(false);
    }
}
