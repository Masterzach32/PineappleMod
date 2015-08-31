package net.masterzach32.pineapple.world;

import java.util.Random;

import net.masterzach32.pineapple.block.*;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenShrine implements IWorldGenerator {
	
	public static WorldGenShrine pineappleTempleDecorator = new WorldGenShrine();
	
	private boolean spawn = false;

	/**
	 * Generator code for the Pineapple Temple
	 * @param random
	 * @param chunkx
	 * @param chunkz
	 * @param world
	 * @param chunkGenerator
	 * @param chunckProvider
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Finds a coordinate within the given chunk
		int x, y, z;
		spawn = true;
		if (random.nextInt(500) == 1) {
			while(spawn) {
				x = chunkX*16 + random.nextInt(16) + 8;
				y = random.nextInt(128);
				z = chunkZ*16 + random.nextInt(16) + 8;
				
				BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
				
				// Checks to see if their is a block at the location
				if (!world.isAirBlock(x, y, z)) {
					//LogHelper.logInfo("Non-empty location at x=" + x + ", y=" + y + ", z=" + z + ". Try:" + i);
				}
				else if (world.getBlock(x, y - 1, z) == Blocks.stone) {
					//LogHelper.logInfo("Not on sand at x=" + x + ", y=" + y + ", z=" + z + ". Try:" + i);
				}
				else if (world.getBlock(x, y - 1, z) instanceof BlockLeavesBase) {
					//LogHelper.logInfo("Not on sand at x=" + x + ", y=" + y + ", z=" + z + ". Try:" + i);
				}
				//Checks to see if a block cannot be placed at the location ** Is this really needed? **
				else if (!PineappleBlocks.pineapple_block.canPlaceBlockAt(world, x, y, z)) {
					//LogHelper.logInfo("Unplaceable at x=" + x + ", y=" + y + ", z=" + z + ".");
					// i1 = i1 + 1;
				} 
				// Builds the temple at legal location
				else {
					//LogHelper.logInfo("Placing Temple at x=" + x + ", y=" + y + ", z=" + z + ".");
					world.setBlock(x, y - 1, z, Blocks.redstone_block, 0, 2);
					world.setBlock(x, y, z, Blocks.redstone_lamp, 0, 2);
					// stairs
					world.setBlock(x + 1, y, z, Blocks.stone_brick_stairs, 1, 2);
					world.setBlock(x - 1, y, z, Blocks.stone_brick_stairs, 0, 2);
					world.setBlock(x, y, z + 1, Blocks.stone_brick_stairs, 3, 2);
					world.setBlock(x, y, z - 1, Blocks.stone_brick_stairs, 2, 2);
					// under stairs
					world.setBlock(x + 1, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 3, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 1, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 3, y - 1, z, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z + 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z + 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z + 3, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z - 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z - 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x, y - 1, z - 3, Blocks.stonebrick, 0, 2);
					// beside under stairs
					world.setBlock(x + 1, y - 1, z + 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y - 1, z + 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y - 1, z + 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 1, y - 1, z + 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y, z + 2, Blocks.cobblestone_wall, 0, 2);
					world.setBlock(x + 2, y + 1, z + 2, Blocks.gold_block, 0, 2);
					
					world.setBlock(x + 1, y - 1, z - 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y - 1, z - 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y - 1, z - 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 1, y - 1, z - 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x + 2, y, z - 2, Blocks.cobblestone_wall, 0, 2);
					world.setBlock(x + 2, y + 1, z - 2, PineappleBlocks.godly_pineapple_block, 0, 2);
					
					world.setBlock(x - 1, y - 1, z + 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y - 1, z + 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y - 1, z + 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 1, y - 1, z + 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y, z + 2, Blocks.cobblestone_wall, 0, 2);
					world.setBlock(x - 2, y + 1, z + 2, PineappleBlocks.godly_pineapple_block, 0, 2);
					
					world.setBlock(x - 1, y - 1, z - 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y - 1, z - 1, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y - 1, z - 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 1, y - 1, z - 2, Blocks.stonebrick, 0, 2);
					world.setBlock(x - 2, y, z - 2, Blocks.cobblestone_wall, 0, 2);
					world.setBlock(x - 2, y + 1, z - 2, Blocks.gold_block, 0, 2);
					
					world.setBlock(x, y + 1, z, PineappleBlocks.godly_pineapple_block, 0, 2);
					spawn = false;
				}
			}
		}
	}
}
