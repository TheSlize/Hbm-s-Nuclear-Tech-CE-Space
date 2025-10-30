package com.hbmspace.dim.minmus.biome;

import com.hbmspace.blocks.ModBlocksSpace;

public class BiomeGenMinmusBasin extends BiomeGenBaseMinmus {

	public BiomeGenMinmusBasin(BiomeProperties properties) {
		super(properties);
        this.topBlock = ModBlocksSpace.minmus_smooth.getDefaultState();
        this.fillerBlock = ModBlocksSpace.minmus_regolith.getDefaultState();
	}

}