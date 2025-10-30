package com.hbmspace.dim.eve;

import com.hbmspace.blocks.ModBlocksSpace;
import com.hbm.config.SpaceConfig;
import com.hbm.config.WorldConfig;
import com.hbmspace.dim.CelestialBody;
import com.hbmspace.dim.eve.GenLayerEve.WorldGenElectricVolcano;
import com.hbmspace.dim.eve.GenLayerEve.WorldGenEveSpike;
import com.hbmspace.dim.eve.biome.BiomeGenBaseEve;
import com.hbm.world.OilBubble;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorEve implements IWorldGenerator {

	WorldGenElectricVolcano volcano = new WorldGenElectricVolcano(30, 22, ModBlocksSpace.eve_silt, ModBlocksSpace.eve_rock);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.eveDimension) {
			generateEve(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEve(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);

		//DungeonToolbox.generateOre(world, rand, i, j, 12,  8, 1, 33, ModBlocksSpace.ore_niobium, meta, ModBlocksSpace.eve_rock);
		//DungeonToolbox.generateOre(world, rand, i, j, 8,  4, 5, 48, ModBlocksSpace.ore_iodine, meta, ModBlocksSpace.eve_rock);

		if(WorldConfig.eveGasSpawn > 0 && rand.nextInt(WorldConfig.eveGasSpawn) == 0) {
			int randPosX = i + rand.nextInt(16);
			int randPosY = rand.nextInt(25);
			int randPosZ = j + rand.nextInt(16);

			OilBubble.spawnOil(world, randPosX, randPosY, randPosZ, 10 + rand.nextInt(7), ModBlocksSpace.ore_gas, meta, ModBlocksSpace.eve_rock);
		}

		int x = i + rand.nextInt(16);
		int z = j + rand.nextInt(16);
		int y = world.getHeight(x, z);
		BlockPos pos = new BlockPos(x, y, z);
		Biome biome = world.getBiomeForCoordsBody(pos);
		if(biome == BiomeGenBaseEve.eveSeismicPlains) {
			new WorldGenEveSpike().generate(world, rand, pos);
		}

		if(rand.nextInt(100) == 0) {
			volcano.generate(world, rand, pos);

		}
	}

}
