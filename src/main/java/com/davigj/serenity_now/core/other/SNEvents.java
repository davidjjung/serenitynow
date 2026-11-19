package com.davigj.serenity_now.core.other;

import com.davigj.serenity_now.core.SNConfig;
import com.davigj.serenity_now.core.SerenityNow;
import com.davigj.serenity_now.core.other.compat.NaturalistCompat;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.environmental.common.entity.animal.koi.Koi;
import com.teamabnormals.environmental.core.EnvironmentalConfig;
import com.teamabnormals.environmental.core.other.tags.EnvironmentalEntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.davigj.serenity_now.core.other.compat.ModConstants.NATURALIST;
import static com.davigj.serenity_now.core.other.compat.NaturalistCompat.isMagicButterflySpawn;
import static com.teamabnormals.environmental.core.other.EnvironmentalEvents.VALID_SPAWNS;

@EventBusSubscriber(modid = SerenityNow.MOD_ID)
public class SNEvents {
    @SubscribeEvent
    public static void onTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) return;
        if (NATURALIST) {
            NaturalistCompat.magicButterfly(living);
        }
    }


    @SubscribeEvent
    public static void onLivingSpawn(FinalizeSpawnEvent event) {
        if (!NATURALIST) return;
        if (NaturalistCompat.isMagicButterflySpawn(event)) return;
        Mob entity = event.getEntity();
        boolean natural = VALID_SPAWNS.contains(event.getSpawnType());
        boolean spawner = !(Boolean) EnvironmentalConfig.COMMON.blockOnlyNaturalSpawns.get() && event.getSpawnType() == MobSpawnType.SPAWNER;
        if ((natural || spawner) && entity.getType().getCategory() == MobCategory.MONSTER && !entity.getType().is(EnvironmentalEntityTypeTags.UNAFFECTED_BY_SERENITY)) {
            NaturalistCompat.noSpawn(entity, event);
        }
    }
}
