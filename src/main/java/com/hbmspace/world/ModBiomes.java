package com.hbmspace.world;

import com.hbmspace.dim.duna.biome.BiomeGenBaseDuna;
import com.hbmspace.dim.eve.biome.BiomeGenBaseEve;
import com.hbmspace.dim.laythe.biome.BiomeGenBaseLaythe;
import com.hbmspace.dim.minmus.biome.BiomeGenBaseMinmus;
import net.minecraftforge.common.BiomeDictionary;

public class ModBiomes {
    public static void init()
    {
        BiomeDictionary.addTypes(BiomeGenBaseDuna.dunaPlains, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
        BiomeDictionary.addTypes(BiomeGenBaseDuna.dunaLowlands, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
        BiomeDictionary.addTypes(BiomeGenBaseDuna.dunaPolar, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY);
        BiomeDictionary.addTypes(BiomeGenBaseDuna.dunaHills, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.HILLS);
        BiomeDictionary.addTypes(BiomeGenBaseDuna.dunaPolarHills, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN);
        BiomeDictionary.addTypes(BiomeGenBaseEve.evePlains, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseEve.eveSeismicPlains, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseEve.eveOcean, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseEve.eveRiver, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseEve.eveMountains, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseLaythe.laytheIsland, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseLaythe.laytheOcean, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseLaythe.laythePolar, BiomeDictionary.Type.COLD, BiomeDictionary.Type.WET, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SPOOKY);
        BiomeDictionary.addTypes(BiomeGenBaseMinmus.minmusCanyon, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN);
        BiomeDictionary.addTypes(BiomeGenBaseMinmus.minmusPlains, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.MOUNTAIN);

    }
}
