package com.hbmspace.dim.duna;

import com.hbm.blocks.ModBlocks;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbm.config.SpaceConfig;
import com.hbm.config.WorldConfig;
import com.hbmspace.dim.CelestialBody;
import com.hbm.world.OilBubble;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorDuna implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.dunaDimension) {
			generateDuna(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateDuna(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);

		if(WorldConfig.dunaOilSpawn > 0 && rand.nextInt(WorldConfig.dunaOilSpawn) == 0) {
			int randPosX = i + rand.nextInt(16);
			int randPosY = rand.nextInt(25);
			int randPosZ = j + rand.nextInt(16);

			OilBubble.spawnOil(world, randPosX, randPosY, randPosZ, 10 + rand.nextInt(7), ModBlocks.ore_oil, meta, ModBlocksSpace.duna_rock);
		}

		//DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.nickelSpawn, 8, 1, 43, ModBlocksSpace.ore_iron, meta, ModBlocksSpace.duna_rock);
		//DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.titaniumSpawn, 9, 4, 27, ModBlocksSpace.ore_zinc, meta, ModBlocksSpace.duna_rock);

		// Basalt rich in minerals, but only in basaltic caves!
		//DungeonToolbox.generateOre(world, rand, i, j, 12, 6, 0, 16, ModBlocksSpace.ore_basalt, 0, ModBlocksSpace.basalt);
		//DungeonToolbox.generateOre(world, rand, i, j, 8, 8, 0, 16, ModBlocksSpace.ore_basalt, 1, ModBlocksSpace.basalt);
		//DungeonToolbox.generateOre(world, rand, i, j, 8, 9, 0, 16, ModBlocksSpace.ore_basalt, 2, ModBlocksSpace.basalt);
		//DungeonToolbox.generateOre(world, rand, i, j, 2, 4, 0, 16, ModBlocksSpace.ore_basalt, 3, ModBlocksSpace.basalt);
		//DungeonToolbox.generateOre(world, rand, i, j, 6, 10, 0, 16, ModBlocksSpace.ore_basalt, 4, ModBlocksSpace.basalt);


		//if(i == 0 && j == 0 && world.getWorldInfo().getTerrainType() == WorldTypeTeleport.martian) {
			//int x = 0;
			//int z = 0;
			//int y = world.getHeight(x, z) - 1;

			//ResourceManager.martian.build(world, x, y, z);
		//}
	}
	
}