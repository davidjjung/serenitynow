package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import galena.nirvana.index.NirvanaEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(NirvanaEffects.class)
public class NirvanaEffectsMixin {
    @Inject(method = "arePeaceful", at = @At("RETURN"), cancellable = true)
    private static void effectUnification(Entity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (SNConfig.COMMON.peaceUnity.get() && target instanceof LivingEntity living) {
            if (attacker.hasEffect(EnvironmentalMobEffects.SERENITY) || living.hasEffect(EnvironmentalMobEffects.SERENITY)) {
                cir.setReturnValue(true);
            }
        }
    }
}

