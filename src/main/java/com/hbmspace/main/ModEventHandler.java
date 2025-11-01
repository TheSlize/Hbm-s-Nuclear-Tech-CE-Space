package com.hbmspace.main;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.ParticleUtil;
import com.hbmspace.dim.*;
import com.hbmspace.dim.trait.CBT_Atmosphere;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = RefStrings.MODID)
public class ModEventHandler {

    public static Random rand = new Random();
    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            DebugTeleporter.runQueuedTeleport();
            if (event.world.getTotalWorldTime() % 20 == 0) {
                CelestialBody.updateChemistry(event.world);
            }
        }
        if (event.phase == TickEvent.Phase.START && event.world.provider instanceof WorldProviderCelestial && event.world.provider.getDimension() != 0) {
            if (event.world.getGameRules().getBoolean("doDaylightCycle")) {
                event.world.provider.setWorldTime(event.world.provider.getWorldTime() + 1L);
            }
        }
        if (event.phase == TickEvent.Phase.START) {
            updateWaterOpacity(event.world);
        }
    }

    private static void updateWaterOpacity(World world) {
        // Per world water opacity!
        int waterOpacity = 3;
        if (world.provider instanceof WorldProviderCelestial) {
            waterOpacity = ((WorldProviderCelestial) world.provider).getWaterOpacity();
        }

        Blocks.WATER.setLightOpacity(waterOpacity);
        Blocks.FLOWING_WATER.setLightOpacity(waterOpacity);
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.posY > 300 && player.posY < 1000) {
            Vec3 vec = Vec3.createVectorHelper(3 * rand.nextDouble(), 0, 0);
            CBT_Atmosphere thatmosphere = CelestialBody.getTrait(player.world, CBT_Atmosphere.class);

            if (thatmosphere != null && thatmosphere.getPressure() > 0.05 && !player.isRiding()) {
                if (Math.abs(player.motionX) > 1 || Math.abs(player.motionY) > 1 || Math.abs(player.motionZ) > 1) {
                    ParticleUtil.spawnGasFlame(player.world, player.posX - 1 + vec.xCoord, player.posY + vec.yCoord, player.posZ + vec.zCoord, 0, 0, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onGenerateOre(OreGenEvent.GenerateMinable event) {
        if (event.getWorld().provider instanceof WorldProviderCelestial && event.getWorld().provider.getDimension() != 0) {
            WorldGeneratorCelestial.onGenerateOre(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLoad(WorldEvent.Load event) {
        if (event.getWorld().provider.getDimension() == 0) {
            WorldProviderEarth customProvider = new WorldProviderEarth();
            customProvider.setWorld(event.getWorld());
            customProvider.setDimension(0);
            event.getWorld().provider = customProvider;
        }
    }
}
