package com.hbmspace.dim.dres;

import com.hbm.blocks.ModBlocks;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbm.config.GeneralConfig;
import com.hbm.config.SpaceConfig;
import com.hbm.config.WorldConfig;
import com.hbmspace.dim.CelestialBody;
import com.hbm.world.generator.DungeonToolbox;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorDres implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.dresDimension) {
			generateDres(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateDres(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);

		DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.cobaltSpawn, 4, 3, 22, ModBlocks.ore_cobalt, ModBlocksSpace.dres_rock);
		//DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperSpawn, 9, 4, 27, ModBlocksSpace.ore_iron, meta, ModBlocksSpace.dres_rock);
		//DungeonToolbox.generateOre(world, rand, i, j, 12,  8, 1, 33, ModBlocksSpace.ore_niobium, meta, ModBlocksSpace.dres_rock);
		DungeonToolbox.generateOre(world, rand, i, j, GeneralConfig.coltanRate, 4, 15, 40, ModBlocks.ore_coltan, ModBlocksSpace.dres_rock);
		//DungeonToolbox.generateOre(world, rand, i, j, 1, 6, 4, 64, ModBlocksSpace.ore_lanthanium, meta, ModBlocksSpace.dres_rock);

        //DungeonToolbox.generateOre(world, rand, i, j, 1, 12, 8, 32, ModBlocksSpace.ore_shale, meta, ModBlocksSpace.dres_rock);
	}
}