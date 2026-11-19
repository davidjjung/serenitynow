package com.davigj.serenity_now.core.other;

import com.davigj.serenity_now.core.SerenityNow;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SNItemTags {
    public static final TagKey<Item> SERENITY_INDICATORS = itemTag("serenity_indicators");

    private static TagKey<Item> itemTag(String name) {
        return TagUtil.itemTag(SerenityNow.MOD_ID, name);
    }
}
