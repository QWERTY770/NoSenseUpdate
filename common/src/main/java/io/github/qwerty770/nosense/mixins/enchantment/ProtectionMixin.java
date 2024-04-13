package io.github.qwerty770.nosense.mixins.enchantment;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionMixin {
    @Final @Shadow public ProtectionEnchantment.Type type;

    @Inject(method = "checkCompatibility(Lnet/minecraft/world/item/enchantment/Enchantment;)Z",
            at = @At("HEAD"), cancellable = true)
    private void checkCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir){
        if (enchantment instanceof ProtectionEnchantment protectionEnchantment) {
            if (type == protectionEnchantment.type) {
                cir.setReturnValue(false);
            }
            cir.setReturnValue(true);  // Protection enchantments are compatible again in this mod!
        }
    }

    @Inject(method = "getDamageProtection(ILnet/minecraft/world/damagesource/DamageSource;)I",
            at = @At("HEAD"), cancellable = true)
    private void getDamageProtection(int i, DamageSource damageSource, CallbackInfoReturnable<Integer> cir){
        TagKey<DamageType> MELEE_ATTACK = TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("nosense", "melee_attack"));
        if (this.type == ProtectionEnchantment.Type.ALL && (!damageSource.is(MELEE_ATTACK))) {
            cir.setReturnValue(0);
        }
        if (this.type == ProtectionEnchantment.Type.ALL && damageSource.is(MELEE_ATTACK)) {
            cir.setReturnValue(2*i);
        }
    }
}
