package com.davigj.serenity_now.core.other;

import com.davigj.serenity_now.core.SNConfig;
import com.davigj.serenity_now.core.SerenityNow;
import com.davigj.serenity_now.core.other.compat.NaturalistCompat;
import com.teamabnormals.environmental.core.registry.EnvironmentalMobEffects;
import com.teamabnormals.environmental.core.registry.EnvironmentalParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import static com.davigj.serenity_now.core.other.compat.ModConstants.NATURALIST;

@EventBusSubscriber(modid = SerenityNow.MOD_ID, value = Dist.CLIENT)
public class SNClientEvents {
    @SubscribeEvent
    public static void onTick(EntityTickEvent.Post event) {
        if (!SNConfig.CLIENT.sereneParticles.get()) return;
        if (!(event.getEntity() instanceof LivingEntity living)) return;
        if (!living.hasEffect(EnvironmentalMobEffects.SERENITY)) return;
        if (living.tickCount % 25 == 0) {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            if (player != null && !living.is(player) && player.isHolding(SNClientEvents::validIndicator)) {
                RandomSource random = living.level().getRandom();
                player.level().addParticle(EnvironmentalParticleTypes.RED_LOTUS_BLOSSOM.get(), living.getX() + (random.nextGaussian() * 0.2),
                        living.getY() + random.nextDouble(), living.getZ() + (random.nextGaussian() * 0.2), 0, 0, 0);
            }
        }
    }

    private static boolean validIndicator(ItemStack stack) {
        if (stack.is(SNItemTags.SERENITY_INDICATORS)) return true;
        if (NATURALIST) return NaturalistCompat.isMagicButterflyItem(stack);
        return false;
    }
}