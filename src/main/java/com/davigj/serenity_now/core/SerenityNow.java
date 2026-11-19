package com.davigj.serenity_now.core;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(SerenityNow.MOD_ID)
public class SerenityNow {
    public static final String MOD_ID = "serenity_now";

    public SerenityNow(IEventBus bus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, SNConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, SNConfig.CLIENT_SPEC);
    }
}