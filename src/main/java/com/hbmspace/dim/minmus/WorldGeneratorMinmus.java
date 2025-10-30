package com.hbmspace.dim.minmus;

import com.hbm.blocks.ModBlocks;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbm.config.SpaceConfig;
import com.hbm.config.WorldConfig;
import com.hbmspace.dim.CelestialBody;
import com.hbm.world.generator.DungeonToolbox;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorMinmus implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.minmusDimension) {
			generateMinmus(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateMinmus(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);
        //DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.malachiteSpawn, 16, 6, 40, ModBlocksSpace.stone_resource, EnumStoneType.MALACHITE.ordinal(), ModBlocksSpace.minmus_stone);
        DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperSpawn * 3, 12, 8, 56, ModBlocks.ore_copper, ModBlocksSpace.minmus_stone);
	}



}