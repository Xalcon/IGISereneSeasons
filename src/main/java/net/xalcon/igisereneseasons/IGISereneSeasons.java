package net.xalcon.igisereneseasons;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = IGISereneSeasons.MODID, name = IGISereneSeasons.NAME, version = IGISereneSeasons.VERSION, clientSideOnly = true, acceptableRemoteVersions = "*")
public class IGISereneSeasons
{
    public static final String MODID = "igisereneseasons";
    public static final String NAME = "InGame Info XML Addon - Serene Seasons";
    public static final String VERSION = "@VERSION@";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        if (Loader.isModLoaded("sereneseasons"))
        {
            SereneSeasonTags.register();
        }
    }
}
