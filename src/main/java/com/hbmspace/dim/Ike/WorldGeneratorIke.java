package com.hbmspace.dim.Ike;

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

public class WorldGeneratorIke implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == SpaceConfig.ikeDimension) {
			generateIke(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateIke(World world, Random rand, int i, int j) {
		int meta = CelestialBody.getMeta(world);

		DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.asbestosSpawn, 8, 3, 22, ModBlocks.ore_asbestos, ModBlocksSpace.ike_stone);
		DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperSpawn, 9, 4, 27, ModBlocks.ore_copper, ModBlocksSpace.ike_stone);
		//DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.ironClusterSpawn,  8, 1, 33, ModBlocksSpace.ore_iron, ModBlocksSpace.ike_stone);
		//DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.lithiumSpawn,  6, 4, 8, ModBlocksSpace.ore_lithium, ModBlocksSpace.ike_stone);
		DungeonToolbox.generateOre(world, rand, i, j, 2, 4, 15, 40, ModBlocks.ore_coltan, ModBlocksSpace.ike_stone);
		
		//okay okay okay, lets say on duna you DO make solvent, this is now awesome because you can now make gallium arsenide to then head to
		//dres and the likes :)
	
		
        //DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.mineralSpawn, 10, 12, 32, ModBlocksSpace.ore_mineral, meta, ModBlocksSpace.moho_stone);

	}
}