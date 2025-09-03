package aji.carpetajiaddition.mixin.rules.totemOfUndyingWrench;

import carpet.CarpetSettings;
import carpet.helpers.BlockRotator;
import aji.carpetajiaddition.CarpetAjiAdditionSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Mixin(BlockRotator.class)
public abstract class BlockRotatorMixin {
    @Unique
    private static final Map<BlockPos, Long> lastFlipTimes = new HashMap<>();
    
    // 冷却时间（tick）
    @Unique
    private static final int FLIP_COOLDOWN = 5;

    @Unique
    private static boolean isPlayerHoldingTotemInMainHand(PlayerEntity player) {
        return player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING;
    }

    @Unique
    private static boolean isPlayerHoldingTotemInOffHand(PlayerEntity player) {
        return player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING;
    }

    @Unique
    private static boolean isPlayerHoldingBlockInMainHand(PlayerEntity player) {
        return player.getMainHandStack().getItem() instanceof BlockItem;
    }

    @Inject(
            method = "flipBlockWithCactus",
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true,
            remap = false
    )
    private static void postFlipBlockWithCactus(BlockState state, World world, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetAjiAdditionSettings.totemOfUndyingWrench && cir.getReturnValue()) return;

        if (isPlayerHoldingTotemInMainHand(player) && player.getOffHandStack().isEmpty()) {
            BlockPos pos = hit.getBlockPos();
            long currentTime = world.getTime();

            if (lastFlipTimes.containsKey(pos) && (currentTime - lastFlipTimes.get(pos)) < FLIP_COOLDOWN) {
                return;
            }
            
            CarpetSettings.impendingFillSkipUpdates.set(true);
            boolean ret = flipBlockDirectly(state, world, pos);
            CarpetSettings.impendingFillSkipUpdates.set(false);
            
            if (ret) {
                lastFlipTimes.put(pos, currentTime);
                playFlipSound(world, pos);
            }
            
            cir.setReturnValue(ret);
        }
    }

    @Unique
    private static void playFlipSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Unique
    private static boolean flipBlockDirectly(BlockState state, World world, BlockPos pos) {
        Collection<Property<?>> properties = state.getProperties();
        for (Property<?> property : properties) {
            if (property instanceof DirectionProperty) {
                DirectionProperty directionProperty = (DirectionProperty) property;
                Direction currentDirection = (Direction) state.get(directionProperty);
                Direction newDirection = getRestrictedNextDirection(currentDirection, directionProperty.getValues());
                if (newDirection != null && newDirection != currentDirection) {
                    BlockState newState = state.with(directionProperty, newDirection);
                    return world.setBlockState(pos, newState, 3);
                }
            }
        }
        return false;
    }

    @Unique
    private static Direction getRestrictedNextDirection(Direction current, Collection<Direction> allowedDirections) {
        if (allowedDirections.size() == 2) {
            Direction[] directions = allowedDirections.toArray(new Direction[0]);
            return directions[0] == current ? directions[1] : directions[0];
        }

        Direction[] directions = allowedDirections.toArray(new Direction[0]);
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == current) {
                return directions[(i + 1) % directions.length];
            }
        }
        return current;
    }

    @Inject(
            method = "flippinEligibility",
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true,
            remap = false
    )
    private static void postFlippinEligibility(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetAjiAdditionSettings.totemOfUndyingWrench && cir.getReturnValue()) return;

        if (!(entity instanceof PlayerEntity player)) {
            return;
        }

        if (isPlayerHoldingTotemInOffHand(player) && isPlayerHoldingBlockInMainHand(player)
                && !player.isSpectator()) {
            cir.setReturnValue(true);
        }
    }
}