package com.davigj.serenity_now.core.mixin;

import com.teamabnormals.environmental.common.entity.animal.koi.Koi;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Koi.class)
public class KoiMixin {
    @Mutable
    @Final
    @Shadow
    public static final TargetingConditions PLAYERS_OR_ENDERMEN;

    static {
        PLAYERS_OR_ENDERMEN = TargetingConditions.forNonCombat().selector((entity) -> entity instanceof LivingEntity).ignoreLineOfSight().ignoreInvisibilityTesting();
    }
}
