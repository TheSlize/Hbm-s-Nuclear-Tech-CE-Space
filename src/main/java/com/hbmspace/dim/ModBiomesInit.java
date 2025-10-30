package com.hbmspace.dim;

import com.hbm.config.WorldConfig;
import com.hbmspace.dim.Ike.BiomeGenIke;
import com.hbmspace.dim.dres.biome.BiomeGenBaseDres;
import com.hbmspace.dim.duna.biome.BiomeGenBaseDuna;
import com.hbmspace.dim.eve.biome.BiomeGenBaseEve;
import com.hbmspace.dim.laythe.biome.BiomeGenBaseLaythe;
import com.hbmspace.dim.minmus.biome.BiomeGenBaseMinmus;
import com.hbmspace.dim.moho.biome.BiomeGenBaseMoho;
import com.hbmspace.dim.moon.BiomeGenMoon;
import com.hbmspace.dim.orbit.BiomeGenOrbit;
import com.hbm.world.biome.BiomeGenCraterBase;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBiomesInit {
    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> evt){
        evt.getRegistry().registerAll(
                BiomeGenBaseDuna.dunaPlains.setRegistryName("hbm", "duna_plains"),
                BiomeGenBaseDuna.dunaLowlands.setRegistryName("hbm", "duna_lowlands"),
                BiomeGenBaseDuna.dunaPolar.setRegistryName("hbm", "duna_polar"),
                BiomeGenBaseDuna.dunaHills.setRegistryName("hbm", "duna_hills"),
                BiomeGenBaseDuna.dunaPolarHills.setRegistryName("hbm", "duna_polar_hills"),
                BiomeGenBaseDres.dresPlains.setRegistryName("hbm", "dres_plains"),
                BiomeGenBaseDres.dresCanyon.setRegistryName("hbm", "dres_canyon"),
                BiomeGenBaseEve.evePlains.setRegistryName("hbm", "eve_plains"),
                BiomeGenBaseEve.eveOcean.setRegistryName("hbm", "eve_ocean"),
                BiomeGenBaseEve.eveMountains.setRegistryName("hbm", "eve_mountains"),
                BiomeGenBaseEve.eveSeismicPlains.setRegistryName("hbm", "eve_seismic_plains"),
                BiomeGenBaseEve.eveRiver.setRegistryName("hbm", "eve_river"),
                new BiomeGenIke(new Biome.BiomeProperties("Ike")).setRegistryName("hbm", "ike"),
                BiomeGenBaseLaythe.laytheIsland.setRegistryName("hbm", "laythe_island"),
                BiomeGenBaseLaythe.laytheOcean.setRegistryName("hbm", "laythe_ocean"),
                BiomeGenBaseLaythe.laythePolar.setRegistryName("hbm", "laythe_polar"),
                BiomeGenBaseMinmus.minmusPlains.setRegistryName("hbm", "minmus_plains"),
                BiomeGenBaseMinmus.minmusCanyon.setRegistryName("hbm", "minmus_canyon"),
                BiomeGenBaseMoho.mohoCrag.setRegistryName("hbm", "moho_crag"),
                BiomeGenBaseMoho.mohoBasalt.setRegistryName("hbm", "moho_basalt"),
                new BiomeGenMoon(new Biome.BiomeProperties("Mun")).setRegistryName("hbm", "moon"),
                new BiomeGenOrbit(new Biome.BiomeProperties("Orbit")).setRegistryName("hbm", "orbit")
        );
        if(WorldConfig.enableCraterBiomes){
            evt.getRegistry().registerAll(
                    BiomeGenCraterBase.craterBiome.setRegistryName("hbm", "crater"),
                    BiomeGenCraterBase.craterInnerBiome.setRegistryName("hbm", "crater_inner"),
                    BiomeGenCraterBase.craterOuterBiome.setRegistryName("hbm", "crater_outer")
            );
            BiomeGenCraterBase.initDictionary();
        }
    }
}
