package com.davigj.serenity_now.core.other;

import com.davigj.serenity_now.core.SerenityNow;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class SNBiomeTags {
    public static final TagKey<Biome> HAS_MAGIC_BUTTERFLY = biomeTag("has_magic_butterfly");

    private static TagKey<Biome> biomeTag(String name) {
        return TagUtil.biomeTag(SerenityNow.MOD_ID, name);
    }
}
