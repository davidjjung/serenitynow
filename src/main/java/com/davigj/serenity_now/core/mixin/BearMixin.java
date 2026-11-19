package com.davigj.serenity_now.core.mixin;

import com.crispytwig.naturalist.world.entity.animal.bear.Bear;
import com.davigj.serenity_now.core.SNConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(Bear.class)
public abstract class BearMixin extends TamableAnimal {
    protected BearMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(method = "isHostileWhenDark", at = @At("RETURN"))
    private boolean sereneBears(boolean original){
        if (SNConfig.COMMON.sereneBears.get()) {
            return original && !this.hasEffect(EnvironmentalMobEffects.SERENITY);
        }
        return original;
    }
}
