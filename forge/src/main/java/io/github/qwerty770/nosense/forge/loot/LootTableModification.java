package io.github.qwerty770.nosense.forge.loot;

import io.github.qwerty770.nosense.NoSenseMod;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static io.github.qwerty770.nosense.loot.CustomLootTables.*;

@Mod.EventBusSubscriber(modid = NoSenseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModification {
    @SubscribeEvent
    public static void register(LootTableLoadEvent event) {
        if (sheepLootTables.contains(event.getName())) {
            LootTable lootTable = event.getTable();
            // TODO: Modify the loot table
            event.setTable(event.getTable());
        }
    }
}
