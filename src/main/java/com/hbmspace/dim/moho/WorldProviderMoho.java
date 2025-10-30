package com.hbmspace.dim.moho;

import com.hbmspace.blocks.ModBlocksSpace;
import com.hbm.config.SpaceConfig;
import com.hbmspace.dim.WorldChunkManagerCelestial;
import com.hbmspace.dim.WorldChunkManagerCelestial.BiomeGenLayers;
import com.hbmspace.dim.WorldProviderCelestial;
import com.hbmspace.dim.moho.genlayer.GenLayerMohoBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.*;

public class WorldProviderMoho extends WorldProviderCelestial {

	@Override
	public void init() {
		this.biomeProvider = new WorldChunkManagerCelestial(createBiomeGenerators(world.getSeed()));
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderMoho(this.world, this.getSeed(), false);
	}

	@Override
	public Block getStone() {
		return ModBlocksSpace.moho_stone;
	}

	private static BiomeGenLayers createBiomeGenerators(long seed) {
		GenLayer biomes = new GenLayerMohoBiomes(seed);

		biomes = new GenLayerFuzzyZoom(2000L, biomes);
		biomes = new GenLayerZoom(2001L, biomes);
		// biomes = new GenLayerZoom(1000L, biomes);
		// biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerSmooth(700L, biomes);
		biomes = new GenLayerZoom(1006L, biomes);
		
		GenLayer genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, biomes);

		return new BiomeGenLayers(biomes, genLayerVoronoiZoom, seed);
	}

	@Override
	public DimensionType getDimensionType(){return DimensionType.getById(SpaceConfig.mohoDimension);}

}