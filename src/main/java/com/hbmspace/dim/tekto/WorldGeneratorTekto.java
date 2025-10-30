package com.hbmspace.dim.tekto;

import com.hbm.config.SpaceConfig;
import com.hbmspace.dim.CelestialBody;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorTekto implements IWorldGenerator {


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.tektoDimension) {
			generateTekto(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateTekto(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);
	}
}
