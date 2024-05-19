package io.github.qwerty770.nosense.fabric;

import io.github.qwerty770.nosense.NoSenseMod;
import io.github.qwerty770.nosense.fabric.loot.LootTableModification;
import net.fabricmc.api.ModInitializer;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

public class NoSenseModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoSenseMod.init();
        LootTableModification.register();
        ModLogger.info("No Sense Mod loaded!");
    }
}
