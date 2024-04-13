package io.github.qwerty770.nosense.mixins.enchantment;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class TridentMixin {
    @Shadow public abstract boolean isNoPhysics();

    @Inject(method = "playerTouch(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;take(Lnet/minecraft/world/entity/Entity;I)V"))
    private void tick(CallbackInfo ci){
        if (((AbstractArrow) (Object) this) instanceof ThrownTrident trident && this.isNoPhysics()){
            Entity owner = trident.getOwner();
            if (owner != null && owner.isAlive()){
                if (owner instanceof ServerPlayer player && (!owner.isSpectator())){
                    player.hurt(trident.damageSources().trident(trident, player), 4.0f);
                }
            }
        }
    }
}
