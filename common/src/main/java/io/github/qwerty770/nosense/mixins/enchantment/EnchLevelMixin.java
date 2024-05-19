package io.github.qwerty770.nosense.mixins.enchantment;

import net.minecraft.world.item.enchantment.ArrowFireEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnreachableCode")
@Mixin(Enchantment.class)
public class EnchLevelMixin {
    @Inject(method = "getMaxLevel()I",
            at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir){
        if (((Enchantment) (Object) this) instanceof FireAspectEnchantment){
            cir.setReturnValue(3);
        }
        if (((Enchantment) (Object) this) instanceof ArrowFireEnchantment){
            cir.setReturnValue(3);
        }
    }
}
