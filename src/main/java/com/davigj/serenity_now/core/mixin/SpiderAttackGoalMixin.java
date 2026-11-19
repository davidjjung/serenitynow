package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.monster.Spider$SpiderAttackGoal")
public class SpiderAttackGoalMixin extends MeleeAttackGoal {
    public SpiderAttackGoalMixin(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
    }

    @ModifyReturnValue(method = "canContinueToUse", at = @At("RETURN"))
    private boolean sereneSpider(boolean original){
        if (SNConfig.COMMON.sereneSpiders.get()) {
            return original && !this.mob.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
