package com.hbmspace.main;

import com.hbm.handler.GuiHandler;
import com.hbmspace.packet.PacketDispatcherSpace;
import com.hbmspace.config.SpaceConfig;
import com.hbmspace.world.ModBiomes;
import com.hbmspace.world.PlanetGen;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.dim.CommandSpaceTP;
import com.hbmspace.dim.SolarSystem;
import com.hbmspace.items.ModItemsSpace;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Okay, so if you read this
 * It's mostly separated NTM:Space content from NTM:ce, which I did put on a separate repo. Hopefully that won't take TOO much time.
 * I will try to mostly repeat the structure as it's just comfortable for me
 *
 * "It's fun, after all these times when you've killed me, and now.. all I have to do is to kill you **once**"
 *
 * @author Th3_Sl1ze
*/
@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION, name = RefStrings.NAME, acceptedMinecraftVersions = "[1.12.2]", dependencies = "required-after:hbm")
public class SpaceMain {

    @SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
    public static ServerProxy proxy;
    @Mod.Instance(RefStrings.MODID)
    public static SpaceMain instance;
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (logger == null)
            logger = event.getModLog();


        reloadConfig();
        PacketDispatcherSpace.registerPackets();

        ModItemsSpace.preInit();
        ModBlocksSpace.preInit();

        proxy.registerRenderInfo();
        proxy.preInit(event);

        AutoRegistrySpace.registerTileEntities();
        AutoRegistrySpace.loadAuxiliaryData();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }


    public static void reloadConfig() {
        Configuration config = new Configuration(new File(proxy.getDataDir().getPath() + "/config/hbm/hbm_space.cfg"));
        config.load();
        SpaceConfig.loadFromConfig(config);
        config.save();
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModBiomes.init();
        SolarSystem.init();
        PlanetGen.init();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new CommandSpaceTP());
    }
}
