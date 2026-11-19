package com.davigj.serenity_now.core.other.compat;

import com.crispytwig.naturalist.registry.NaturalistRegistry;
import com.crispytwig.naturalist.world.entity.animal.butterfly.Butterfly;
import com.crispytwig.naturalist.world.entity.animal.deer.Deer;
import com.davigj.serenity_now.core.SNConfig;
import com.davigj.serenity_now.core.other.SNBiomeTags;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.environmental.common.entity.animal.koi.Koi;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

import java.util.Objects;

import static com.davigj.serenity_now.core.other.compat.ModConstants.NATURALIST;

public class NaturalistCompat {
    public static final String magicName = "naturalist:magic";

    public static boolean isDeer(LivingEntity living){
        if (NATURALIST) {
            return living instanceof Deer;
        }
        return false;
    }

    public static boolean isMagicButterflyItem(ItemStack stack){
        if (NATURALIST) {
            if (stack.is(NaturalistRegistry.BUTTERFLY.get())) {
                CompoundTag tag = ((CustomData)stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY)).copyTag();
                return tag.contains("Variant") && Objects.equals(tag.getString("Variant"), magicName);
            }
        }
        return false;
    }

    public static void magicButterfly(LivingEntity entity) {
        if (NATURALIST && entity instanceof Butterfly butterfly) {
            if (!(butterfly.getVariantString().equals(magicName))) return;
            if (butterfly.level().getGameTime() % 20L == 0L && (Boolean) SNConfig.COMMON.magicButterfly.get()) {
                int horizontalRange = (Integer) SNConfig.COMMON.horizontalSerenityRange.get();
                int verticalRange = (Integer)SNConfig.COMMON.verticalSerenityRange.get();
                for(LivingEntity living : butterfly.level().getNearbyEntities(LivingEntity.class, Koi.PLAYERS_OR_ENDERMEN, butterfly, butterfly.getBoundingBox().inflate((double)horizontalRange, (double)verticalRange, (double)horizontalRange))) {
                    if (!butterfly.level().isClientSide()) {
                        living.addEffect(new MobEffectInstance(EnvironmentalMobEffects.SERENITY, 100, 0, true, false, true));
                    }
                }
            }
        }
    }

    public static void noSpawn(LivingEntity entity, FinalizeSpawnEvent event) {
        ServerLevelAccessor level = event.getLevel();
        int horizontalRange = (Integer) SNConfig.COMMON.horizontalSerenityRange.get();
        int verticalRange = (Integer)SNConfig.COMMON.verticalSerenityRange.get();
        for(Butterfly butterfly : level.getEntitiesOfClass(Butterfly.class, entity.getBoundingBox().inflate((double)horizontalRange, (double)verticalRange, (double)horizontalRange))) {
            if (butterfly.getVariantString().equals(magicName) && MathUtil.distanceBetweenPoints2d(entity.getX(), entity.getZ(), butterfly.getX(), butterfly.getZ()) <= (double)horizontalRange) {
                event.setSpawnCancelled(true);
                event.setCanceled(true);
                break;
            }
        }
    }

    public static boolean isMagicButterflySpawn(FinalizeSpawnEvent event) {
        if (event.getEntity() instanceof Butterfly butterfly) {
            if (event.getSpawnType().equals(MobSpawnType.NATURAL)) {
                Holder<Biome> holder = event.getLevel().getBiome(butterfly.getOnPos());
                if (holder.is(SNBiomeTags.HAS_MAGIC_BUTTERFLY)) {
                    if (butterfly.getRandom().nextDouble() < SNConfig.COMMON.magicReplaceChance.get()) {
                        butterfly.setVariantString(magicName);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
