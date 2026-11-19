package com.davigj.serenity_now.core.mixin;

import com.davigj.serenity_now.core.SNConfig;
import com.davigj.serenity_now.core.other.compat.NaturalistCompat;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.davigj.serenity_now.core.other.compat.ModConstants.NATURALIST;

@Mixin(AvoidEntityGoal.class)
public class AvoidEntityGoalMixin {
    @Shadow
    @Mutable
    @Final
    protected final PathfinderMob mob;

    @Shadow
    @Mutable
    @Final
    protected final Class<?> avoidClass;

    public AvoidEntityGoalMixin(PathfinderMob mob, Class<?> avoidClass) {
        this.mob = mob;
        this.avoidClass = avoidClass;
    }

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void sereneFoxes(CallbackInfoReturnable<Boolean> cir){
        if (!Player.class.isAssignableFrom(avoidClass)) return;
        if (SNConfig.COMMON.sereneFoxes.get() && this.mob.hasEffect(EnvironmentalMobEffects.SERENITY) &&
                this.mob instanceof Fox) {
            cir.setReturnValue(false);
        }
        if (NATURALIST){
            if (SNConfig.COMMON.sereneDeerN.get() && this.mob.hasEffect(EnvironmentalMobEffects.SERENITY) &&
                    NaturalistCompat.isDeer(this.mob)) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "canContinueToUse", at = @At("HEAD"), cancellable = true)
    private void sereneFox(CallbackInfoReturnable<Boolean> cir){
        if (!Player.class.isAssignableFrom(avoidClass)) return;
        if (SNConfig.COMMON.sereneFoxes.get() && this.mob.hasEffect(EnvironmentalMobEffects.SERENITY) &&
                this.mob instanceof Fox) {
            cir.setReturnValue(false);
        }
        if (NATURALIST){
            if (SNConfig.COMMON.sereneDeerN.get() && this.mob.hasEffect(EnvironmentalMobEffects.SERENITY) &&
                    NaturalistCompat.isDeer(this.mob)) {
                cir.setReturnValue(false);
            }
        }
    }
}
