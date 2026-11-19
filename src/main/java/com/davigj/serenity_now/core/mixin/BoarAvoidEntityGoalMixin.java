package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "com.crispytwig.naturalist.world.entity.animal.boar.Boar$BoarAvoidPlayerGoal")
public class BoarAvoidEntityGoalMixin<T extends LivingEntity> extends AvoidEntityGoal<T> {
    public BoarAvoidEntityGoalMixin(PathfinderMob mob, Class<T> entityClassToAvoid, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier) {
        super(mob, entityClassToAvoid, maxDistance, walkSpeedModifier, sprintSpeedModifier);
    }

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    private boolean sereneBoars(boolean original){
        if (SNConfig.COMMON.sereneBoars.get()) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }

    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean sereneBoar(boolean original){
        if (SNConfig.COMMON.sereneBoars.get()) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
