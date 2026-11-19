package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.animal.Rabbit;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.animal.Rabbit$RabbitAvoidEntityGoal")
public class RabbitAvoidEntityGoalMixin {
    @Shadow
    @Final
    private Rabbit rabbit;

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    private boolean sereneRabbits(boolean original){
        if (SNConfig.COMMON.sereneRabbits.get()) {
            return original && !this.rabbit.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
