package com.hbmspace.main;

import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.items.ModItemsSpace;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
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

        proxy.registerRenderInfo();
        proxy.preInit(event);

        ModItemsSpace.preInit();
        ModBlocksSpace.preInit();

        AutoRegistrySpace.registerTileEntities();
        AutoRegistrySpace.loadAuxiliaryData();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
