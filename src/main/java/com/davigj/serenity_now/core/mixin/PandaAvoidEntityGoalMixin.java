package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.animal.Panda$PandaAvoidGoal")
public class PandaAvoidEntityGoalMixin<T extends LivingEntity> extends AvoidEntityGoal<T> {
    public PandaAvoidEntityGoalMixin(PathfinderMob mob, Class<T> entityClassToAvoid, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier) {
        super(mob, entityClassToAvoid, maxDistance, walkSpeedModifier, sprintSpeedModifier);
    }

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    private boolean serenePandas(boolean original){
        if (SNConfig.COMMON.serenePandas.get() && Player.class.isAssignableFrom(avoidClass)) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
