package ru.zoom4ikdan4ik.blockblocker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import ru.zoom4ikdan4ik.blockblocker.events.PlayerListeners;
import ru.zoom4ikdan4ik.blockblocker.interfaces.IBlockBlocker;
import ru.zoom4ikdan4ik.blockblocker.managers.ConfigManager;

@Mod(modid = ConfigManager.MODID, name = ConfigManager.MODNAME, acceptableRemoteVersions = "*")
public class BlockBlocker implements IBlockBlocker {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerListeners());
    }
}
