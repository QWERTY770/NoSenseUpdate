package io.github.qwerty770.nosense.mixins.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

@Mixin(FlintAndSteelItem.class)
public class FlintMixin {
    @Inject(method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"), cancellable = true)
    private void useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir){
        Level level = useOnContext.getLevel();
        double d = level.random.nextDouble();
        if (d < 0.2){
            Player player = useOnContext.getPlayer();
            ItemStack itemStack = useOnContext.getItemInHand();
            if (player instanceof ServerPlayer) {
                itemStack.hurtAndBreak(1, player, (playerx) -> playerx.broadcastBreakEvent(useOnContext.getHand()));
            }
            cir.cancel();
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide()));
            ModLogger.info("No Sense Update: Sometimes flint and steel does not work! Random number: " + d);
        }
    }
}
