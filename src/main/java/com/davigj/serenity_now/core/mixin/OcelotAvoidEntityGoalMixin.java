package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.animal.Ocelot$OcelotAvoidEntityGoal")
public class OcelotAvoidEntityGoalMixin<T extends LivingEntity> extends AvoidEntityGoal<T> {
    public OcelotAvoidEntityGoalMixin(PathfinderMob mob, Class<T> entityClassToAvoid, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier) {
        super(mob, entityClassToAvoid, maxDistance, walkSpeedModifier, sprintSpeedModifier);
    }

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    private boolean sereneOcelots(boolean original){
        if (SNConfig.COMMON.sereneOcelots.get()) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }

    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean sereneOcelot(boolean original){
        if (SNConfig.COMMON.sereneOcelots.get()) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
