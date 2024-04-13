package io.github.qwerty770.nosense.mixins.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

@Mixin(BlockBehaviour.class)
public class NeighborChangedMixin {
    @Inject(method = "neighborChanged(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/core/BlockPos;Z)V",
            at = @At("HEAD"))
    private void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl, CallbackInfo ci){
        if((BlockBehaviour)((Object) this) instanceof GrassBlock) {
            if (!level.isClientSide) {
                BlockState blockState2 = level.getBlockState(blockPos2);
                if (!(blockState2.is(Blocks.FIRE) || blockState2.is(Blocks.LAVA)) || blockPos.above().compareTo(blockPos2) != 0) return;
                BlockState state = Blocks.DIRT.defaultBlockState();
                level.setBlockAndUpdate(blockPos, state);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(state));
                ModLogger.debug("No Sense Update: A grass block decays into a dirt block! Block state: "
                        + blockState + ", Dimension: " + level.dimension() + ", Block pos: " + blockPos.toString()
                        + ", Block: " + block + ", Block pos 2: " + blockPos2.toString() + ", bl=" + bl);
            }
        }
    }
}
