package io.github.qwerty770.nosense.forge;

import io.github.qwerty770.nosense.NoSenseMod;
import net.minecraftforge.fml.common.Mod;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

@Mod(NoSenseMod.MOD_ID)
public class NoSenseModForge {
    public NoSenseModForge() {
        NoSenseMod.init();
        ModLogger.info("No Sense Update loaded!");
    }
}
