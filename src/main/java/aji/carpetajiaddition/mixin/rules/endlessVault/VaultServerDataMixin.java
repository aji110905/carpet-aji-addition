package aji.carpetajiaddition.mixin.rules.endlessVault;

import aji.carpetajiaddition.CarpetAjiAdditionSettings;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.block.vault.VaultServerData;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.UUID;

@Mixin(VaultServerData.class)
public class VaultServerDataMixin {
    @Inject(method = "hasRewardedPlayer", at = @At("HEAD"), cancellable = true)
    public void hasRewardedPlayer(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetAjiAdditionSettings.keepOpeningVault) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "getRewardedPlayers", at = @At("HEAD"), cancellable = true)
    public void getRewardedPlayers(CallbackInfoReturnable<Set<UUID>> cir) {
        if (CarpetAjiAdditionSettings.keepOpeningVault) {
            cir.setReturnValue(new ObjectLinkedOpenHashSet<>());
        }
    }
}
