package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.teamabnormals.environmental.common.entity.ai.goal.deer.DeerAvoidEntityGoal;
import com.teamabnormals.environmental.common.entity.animal.deer.AbstractDeer;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(DeerAvoidEntityGoal.class)
public class DeerAvoidEntityGoalMixin {
    @Shadow
    @Final
    private AbstractDeer deer;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void sereneDeer(CallbackInfoReturnable<Boolean> cir){
        if (SNConfig.COMMON.sereneDeer.get() && this.deer.hasEffect(EnvironmentalMobEffects.SERENITY)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    private void naraDeer(CallbackInfoReturnable<Boolean> cir){
        if (SNConfig.COMMON.sereneDeer.get() && this.deer.hasEffect(EnvironmentalMobEffects.SERENITY)) {
            cir.setReturnValue(false);
        }
    }
}
