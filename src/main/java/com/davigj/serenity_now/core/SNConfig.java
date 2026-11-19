package com.davigj.serenity_now.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SNConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Boolean> sereneFoxes;
        public final ModConfigSpec.ConfigValue<Boolean> sereneCats;
        public final ModConfigSpec.ConfigValue<Boolean> sereneOcelots;
        public final ModConfigSpec.ConfigValue<Boolean> sereneRabbits;
        public final ModConfigSpec.ConfigValue<Boolean> sereneSpiders;
        public final ModConfigSpec.ConfigValue<Boolean> serenePandas;
        public final ModConfigSpec.ConfigValue<Boolean> sereneDeer;
        public final ModConfigSpec.ConfigValue<Boolean> sereneDucks;
        public final ModConfigSpec.ConfigValue<Boolean> sereneBoars;
        public final ModConfigSpec.ConfigValue<Boolean> sereneDeerN;
        public final ModConfigSpec.ConfigValue<Boolean> sereneBears;
        public final ModConfigSpec.ConfigValue<Boolean> peaceUnity;
        public final ModConfigSpec.ConfigValue<Boolean> magicButterfly;
        public final ModConfigSpec.ConfigValue<Integer> horizontalSerenityRange;
        public final ModConfigSpec.ConfigValue<Integer> verticalSerenityRange;
        public final ModConfigSpec.ConfigValue<Double> magicReplaceChance;

        Common (ModConfigSpec.Builder builder) {
            builder.push("common");
            builder.push("behaviors");
            sereneCats = builder.comment("Cats no longer run from players when afflicted with Serenity").define("Serene cats", true);
            sereneFoxes = builder.comment("Foxes no longer run from players when afflicted with Serenity").define("Serene foxes", true);
            sereneOcelots = builder.comment("Ocelots no longer run from players when afflicted with Serenity").define("Serene ocelots", true);
            serenePandas = builder.comment("Worried pandas no longer run from players when afflicted with Serenity").define("Serene pandas", true);
            sereneRabbits = builder.comment("Rabbits no longer run from players when afflicted with Serenity").define("Serene rabbits", true);
            sereneSpiders = builder.comment("Spiders are always neutral towards players when afflicted with Serenity").define("Serene spiders", true);
            builder.push("environmental");
            sereneDeer = builder.comment("Deer no longer run from players when afflicted with Serenity").define("Serene deer", true);
            sereneDucks = builder.comment("Ducks no longer run from players when afflicted with Serenity").define("Serene ducks", true);
            builder.pop();
            builder.push("naturalist");
            sereneBoars = builder.comment("Boars no longer run from players when afflicted with Serenity").define("Serene boars", true);
            sereneDeerN = builder.comment("Naturalist deer no longer run from players when afflicted with Serenity").define("Serene deer (Naturalist)", true);
            sereneBears = builder.comment("Naturalist bears are always neutral towards players when afflicted with Serenity").define("Serene bears (Naturalist)", true);
            builder.push("butterfly");
            magicButterfly = builder.comment("Magic butterfly variant gives Serenity in a radius, like koi").define("Magic butterflies", true);
            horizontalSerenityRange = builder.comment("Horizontal radius of Serenity effect in blocks from the magic butterfly").defineInRange("Horizontal serenity range (radius)", 32, 0, Integer.MAX_VALUE);
            verticalSerenityRange = builder.comment("Vertical radius of Serenity effect in blocks from the magic butterfly").defineInRange("Vertical serenity range (radius)", 8, 0, Integer.MAX_VALUE);
            magicReplaceChance = builder.comment("Chance a butterfly spawn in has_magic_butterfly is replaced by a magic butterfly").defineInRange("Magic spawn chance", 0.1, 0, 1.0);
            builder.pop();
            builder.pop();
            builder.push("nirvana");
            peaceUnity = builder.comment("Entities with Serenity gain all the benefits of entities with Peace").define("Effect unification", false);
            builder.pop();
            builder.pop();
            builder.pop();
        }
    }
    public static class Client {
        public final ModConfigSpec.ConfigValue<Boolean> sereneParticles;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("client");
            sereneParticles = builder.comment("Entities with Serenity emit particles when a nearby player holds serenity_indicators").define("Serene particles", false);
            builder.pop();
        }
    }

    static final ModConfigSpec COMMON_SPEC;
    public static final SNConfig.Common COMMON;

    public static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(SNConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}

