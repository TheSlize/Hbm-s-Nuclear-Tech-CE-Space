/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package com.hbmspace.dim.laythe.biome;

import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.dim.BiomeDecoratorCelestial;
import com.hbmspace.dim.BiomeGenBaseCelestial;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public abstract class BiomeGenBaseLaythe extends BiomeGenBaseCelestial {

	public static final Biome laytheIsland = new BiomeGenLaytheIslands(new BiomeProperties("Laythe Islands").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.2F).setWaterColor(0x5b209a));
	public static final Biome laytheOcean = new BiomeGenLaytheOcean(new BiomeProperties("Sagan Sea").setBaseHeight(-0.6F).setHeightVariation(0.01F).setTemperature(0.2F).setRainfall(0.2F));
	public static final Biome laythePolar = new BiomeGenLaythePolar(new BiomeProperties("Laythe Poles").setBaseHeight(-0.1F).setHeightVariation(0.05F).setTemperature(0.2F).setRainfall(0.2F).setWaterColor(0xC1F4FF));

	public BiomeGenBaseLaythe(BiomeProperties properties) {
		super(properties);
		properties.setWaterColor(0x5b009a);
		// no-no-no, mister fish! you won't go to your family, you will go in this ebaniy tazik blyat
		//this.waterCreatures.add(new BiomeGenBase.SpawnListEntry(EntityScutterfish.class, 10, 4, 4));

		BiomeDecoratorCelestial decorator = new BiomeDecoratorCelestial(Blocks.STONE);
		decorator.waterPlantsPerChunk = 32;
		this.decorator = decorator;
		this.decorator.generateFalls = false;
        
        this.topBlock = ModBlocksSpace.laythe_silt.getDefaultState();
        this.fillerBlock = ModBlocksSpace.laythe_silt.getDefaultState();
	}
}