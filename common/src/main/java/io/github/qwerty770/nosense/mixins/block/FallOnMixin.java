package io.github.qwerty770.nosense.mixins.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

@Mixin(Block.class)
public class FallOnMixin {
    @Inject(method = "fallOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;F)V",
            at = @At("HEAD"))
    private void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f, CallbackInfo ci) {
        if((Block)((Object) this) instanceof GrassBlock) {
            if (!level.isClientSide && level.random.nextFloat() < f - 0.5F
                    && entity instanceof LivingEntity
                    && (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
                    && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
                BlockState state = Blocks.DIRT.defaultBlockState();
                level.setBlockAndUpdate(blockPos, state);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(entity, state));
                ModLogger.debug("No Sense Update: A grass block decays into a dirt block! Dimension: "
                        + level.dimension() + ", Block state: " + blockState + ", Block pos: " + blockPos.toString()
                        + ", Entity: " + entity.getType() + " Fall distance: " + f);
            }
        }
    }
}