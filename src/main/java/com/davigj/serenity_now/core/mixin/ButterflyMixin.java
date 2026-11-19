package com.davigj.serenity_now.core.mixin;

import com.crispytwig.naturalist.world.entity.animal.butterfly.Butterfly;
import com.davigj.serenity_now.core.other.compat.NaturalistCompat;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Pseudo
@Mixin(Butterfly.class)
public class ButterflyMixin {
    @Mutable
    @Final
    @Shadow
    public static final String[] VARIANT_NAMES;

    @WrapOperation(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lcom/crispytwig/naturalist/world/entity/animal/butterfly/Butterfly;setVariantString(Ljava/lang/String;)V"))
    private static void magic(Butterfly instance, String location, Operation<Void> original){
        if (instance.getVariantString() != null && instance.getVariantString().equals(NaturalistCompat.magicName)) {
            original.call(instance, NaturalistCompat.magicName);
        } else {
            original.call(instance, location);
        }
    }

    static {
        VARIANT_NAMES = new String[]{"monarch", "clouded_yellow", "swallowtail", "blue_morpho", "jade_green_swallowtail", "purple_emperor", "red_admiral", "magic"};
    }
}
