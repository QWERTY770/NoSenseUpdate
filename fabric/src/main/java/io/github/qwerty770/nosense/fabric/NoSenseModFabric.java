package io.github.qwerty770.nosense.fabric;

import io.github.qwerty770.nosense.NoSenseMod;
import net.fabricmc.api.ModInitializer;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

public class NoSenseModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoSenseMod.init();
        ModLogger.info("No Sense Mod loaded!");
    }
}
