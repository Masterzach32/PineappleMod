package net.masterzach32.pineapple.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.masterzach32.pineapple.block.*;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenPineapple implements IWorldGenerator {
	
	public static WorldGenPineapple pineappleDecorator = new WorldGenPineapple();
	
	/**
	 * Generator code for pineapples
	 * @param random
	 * @param chunkx
	 * @param chunkz
	 * @param world
	 * @param chunkGenerator
	 * @param chunckProvider
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x, y, z;

		// LogHelper.logDebug("WorldGenPineapple.generate() called with chunkX=" + chunkX + ", chunkZ=" + chunkZ + ".");
		
		// uncomment the line below and comment out line 30 to increase the spawning odds by 32x.
		// if (true)
		// one in 4 chance of running the generator in each chunk
		if (random.nextInt(3) == 0) {
			// LogHelper.logDebug("Spawning pineapple cluster in chunkX=" + chunkX + ", chunkZ=" + chunkZ + ".");
			// choose a "locus" for the cluster.
			// (must multiply chunk X & Z numbers by 16 to get actual X & Z coordinates within the chunk)
			x = chunkX*16 + random.nextInt(16) + 8;
			y = random.nextInt(128);	// this Y value is too random!
			z = chunkZ*16 + random.nextInt(16) + 8;

			// make 48 attempts to place a pineapple block.
			// increase the value 48 to increase the odds of succeeding in placing blocks.
			for (int l = 0; l < 48; ++l) {
				// each attempt chooses a random offset from the cluster locus.
				int x1 = x + random.nextInt(8) - random.nextInt(8);
				int y1 = y + random.nextInt(4) - random.nextInt(4);
				int z1 = z + random.nextInt(8) - random.nextInt(8);
				
				// and checks to see if the location is empty, and if the block below it is sand, and if pineapple blocks are placeable there.
				// if (world.isAirBlock(x1, y1, z1) && world.getBlockId(x1, y1 - 1, z1) == Block.sand.blockID && PineappleBlock.pineappleBlock.canPlaceBlockAt(world, x1, y1, z1))
				if (!world.isAirBlock(x1, y1, z1)) {
					 //LogHelper.logInfo("" + l + " Non-empty location at x=" + x1 + ", y=" + y1 + ", z=" + z1 + ".");
				}
				else if (world.getBlock(x1, y1 - 1, z1) != Blocks.sand) {
					 //LogHelper.logInfo("" + l + " Not on sand at x=" + x1 + ", y=" + y1 + ", z=" + z1 + ".");
				} 
				else if (!PineappleBlocks.pineapple_block.canPlaceBlockAt(world, x1, y1, z1)) {
					 //LogHelper.logInfo("" + l + " Unplaceable at x=" + x1 + ", y=" + y1 + ", z=" + z1 + ".");
				} else {
					// LogHelper.logInfo("Placing pineapple block at x=" + x1 + ", y=" + y1 + ", z=" + z1 + ".");
					world.setBlock(x1, y1, z1, PineappleBlocks.pineapple_block, 0, 2); // not sure whether the last param should be 2 or 5 ?
				}
			}
		}
		return;
	}
}
