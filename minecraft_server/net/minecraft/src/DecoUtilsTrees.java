package net.minecraft.src;

import java.util.Random;

public class DecoUtilsTrees {
	public static boolean generateCherry(World var0, Random var1, int var2, int var3, int var4) {
		int var5 = 5;
		int var6 = 0;
		int var7 = 0;
		boolean var8 = false;
		
		int var9 = var1.nextInt(3) + var5;
		boolean var10 = true;

		if (var3 >= 1 && var3 + var9 + 1 <= 256)
		{
			int var11;
			byte var12;
			int var14;
			int var15;

			for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
			{
				var12 = 1;

				if (var11 == var3)
				{
					var12 = 0;
				}

				if (var11 >= var3 + 1 + var9 - 2)
				{
					var12 = 2;
				}

				for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
				{
					for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = var0.getBlockId(var13, var11, var14);

							if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != DecoDefs.cherryLog.blockID && var15 != DecoDefs.cherryLeaves.blockID)
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
			{
				return false;
			}
			else
			{
				var11 = var0.getBlockId(var2, var3 - 1, var4);

				if (FCUtilsTrees.CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
				{
					if (var11 == Block.grass.blockID)
					{
						var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
					}

					var12 = 3;
					byte var21 = 0;
					int var16;
					int var17;
					int var18;

					for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
					{
						var15 = var14 - (var3 + var9);
						var16 = var21 + 1 - var15 / 2;

						for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
						{
							var18 = var17 - var2;

							for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
							{
								int var20 = var19 - var4;

								if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
								{
									var0.setBlockAndMetadataWithNotify(var17, var14, var19, DecoDefs.cherryLeaves.blockID, var7);
								}
							}
						}
					}

					for (var14 = 0; var14 < var9; ++var14)
					{
						var15 = var0.getBlockId(var2, var3 + var14, var4);

						if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == DecoDefs.cherryLeaves.blockID)
						{
							var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, DecoDefs.cherryLog.blockID, var6);
						}
					}

					if (var9 > 2)
					{
						var14 = var0.getBlockId(var2, var3, var4);

						if (var14 == DecoDefs.cherryLog.blockID)
						{
							var15 = var0.getBlockMetadata(var2, var3, var4);

							if (var15 == var6)
							{
								var0.setBlock(var2, var3, var4, DecoDefs.cherryStump.blockID);
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	public static boolean generateAutumn(World var0, Random var1, int var2, int var3, int var4, int leafMeta) {
		int var5 = 5;
		int var6 = 0;
		int var7 = leafMeta;
		boolean var8 = false;
		
		int var9 = var1.nextInt(3) + var5;
		boolean var10 = true;

		if (var3 >= 1 && var3 + var9 + 1 <= 256)
		{
			int var11;
			byte var12;
			int var14;
			int var15;

			for (var11 = var3; var11 <= var3 + 1 + var9; ++var11)
			{
				var12 = 1;

				if (var11 == var3)
				{
					var12 = 0;
				}

				if (var11 >= var3 + 1 + var9 - 2)
				{
					var12 = 2;
				}

				for (int var13 = var2 - var12; var13 <= var2 + var12 && var10; ++var13)
				{
					for (var14 = var4 - var12; var14 <= var4 + var12 && var10; ++var14)
					{
						if (var11 >= 0 && var11 < 256)
						{
							var15 = var0.getBlockId(var13, var11, var14);

							if (!var0.isAirBlock(var13, var11, var14) && var15 != Block.leaves.blockID && var15 != Block.grass.blockID && var15 != Block.dirt.blockID && var15 != Block.wood.blockID && var15 != DecoDefs.cherryLog.blockID && var15 != DecoDefs.cherryLeaves.blockID || var15 == DecoDefs.autumnLeaves.blockID)
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
			{
				return false;
			}
			else
			{
				var11 = var0.getBlockId(var2, var3 - 1, var4);

				if (FCUtilsTrees.CanSaplingGrowOnBlock(var0, var2, var3 - 1, var4) && var3 < 256 - var9 - 1)
				{
					if (var11 == Block.grass.blockID)
					{
						var0.setBlockWithNotify(var2, var3 - 1, var4, Block.dirt.blockID);
					}

					var12 = 3;
					byte var21 = 0;
					int var16;
					int var17;
					int var18;

					for (var14 = var3 - var12 + var9; var14 <= var3 + var9; ++var14)
					{
						var15 = var14 - (var3 + var9);
						var16 = var21 + 1 - var15 / 2;

						for (var17 = var2 - var16; var17 <= var2 + var16; ++var17)
						{
							var18 = var17 - var2;

							for (int var19 = var4 - var16; var19 <= var4 + var16; ++var19)
							{
								int var20 = var19 - var4;

								if ((Math.abs(var18) != var16 || Math.abs(var20) != var16 || var1.nextInt(2) != 0 && var15 != 0) && !Block.opaqueCubeLookup[var0.getBlockId(var17, var14, var19)])
								{
									System.out.println(leafMeta);
									var0.setBlockAndMetadataWithNotify(var17, var14, var19, DecoDefs.autumnLeaves.blockID, leafMeta);
								}
							}
						}
					}

					for (var14 = 0; var14 < var9; ++var14)
					{
						var15 = var0.getBlockId(var2, var3 + var14, var4);

						if (var0.isAirBlock(var2, var3 + var14, var4) || var15 == Block.leaves.blockID || var15 == DecoDefs.autumnLeaves.blockID || var15 == DecoDefs.cherryLeaves.blockID)
						{
							var0.setBlockAndMetadataWithNotify(var2, var3 + var14, var4, Block.wood.blockID, var6);
						}
					}

					if (var9 > 2)
					{
						var14 = var0.getBlockId(var2, var3, var4);

						if (var14 == Block.wood.blockID)
						{
							var15 = var0.getBlockMetadata(var2, var3, var4);

							if (var15 == var6)
							{
								var0.setBlockMetadata(var2, var3, var4, var15 | 12);
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
	
	public static boolean generateAcacia(World world, Random rand, int x, int y, int z)
	{
		int baseHeight = 4 + rand.nextInt(3);
		
		//Base tree
		for(int i = 1; i < baseHeight + 4; i++) {
			int blockID = world.getBlockId(x, y + i, z);

			//Checks trunk space
			if (!(world.isAirBlock(x, y + i, z) || blockID == DecoDefs.acaciaLeaves.blockID)) {
				return false;
			}
		}
		
		for(int i = 0; i < baseHeight + 3; i++) {
			world.setBlock(x, y + i, z, DecoDefs.acaciaLog.blockID);
		}
		
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 3, z - 0, 3);
		createAcaciaLeaves(world, rand, x + 0, y + baseHeight + 4, z - 0, 2);

        world.setBlock(x, y, z, DecoDefs.acaciaStump.blockID);

        //Branches
		if(rand.nextInt(4) == 0 && 
				(world.isAirBlock(x + 0, y + baseHeight + 1, z + 1) || world.getBlockId(x + 0, y + baseHeight + 1, z + 1) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 1, y + baseHeight + 2, z + 2) || world.getBlockId(x + 1, y + baseHeight + 2, z + 2) == DecoDefs.acaciaLeaves.blockID)) {
			world.setBlock(x + 0, y + baseHeight + 1, z + 1, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 2, z + 2, DecoDefs.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z + 2, 3);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 3, z + 2, 2);
		}

		if(rand.nextInt(4) == 0 && 
				(world.isAirBlock(x + 1, y + baseHeight + 0, z + 0) || world.getBlockId(x + 1, y + baseHeight + 0, z + 0) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 2, y + baseHeight + 1, z + 0) || world.getBlockId(x + 2, y + baseHeight + 1, z + 0) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 3, y + baseHeight + 2, z + 1) || world.getBlockId(x + 3, y + baseHeight + 2, z + 1) == DecoDefs.acaciaLeaves.blockID)) {
			world.setBlock(x + 1, y + baseHeight + 0, z + 0, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 2, y + baseHeight + 1, z + 0, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 3, y + baseHeight + 2, z - 1, DecoDefs.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 1, 3);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 1, 2);
		}

		if(rand.nextInt(4) == 0 && 
				(world.isAirBlock(x - 1, y + baseHeight + 0, z + 0) || world.getBlockId(x - 1, y + baseHeight + 0, z + 0) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x - 2, y + baseHeight + 1, z + 0) || world.getBlockId(x - 2, y + baseHeight + 1, z + 0) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x - 3, y + baseHeight + 2, z - 1) || world.getBlockId(x - 3, y + baseHeight + 2, z - 1) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x - 4, y + baseHeight + 3, z - 2) || world.getBlockId(x - 4, y + baseHeight + 3, z - 2) == DecoDefs.acaciaLeaves.blockID)) {
			world.setBlock(x - 1, y + baseHeight + 0, z + 0, DecoDefs.acaciaLog.blockID);
			world.setBlock(x - 2, y + baseHeight + 1, z + 0, DecoDefs.acaciaLog.blockID);
			world.setBlock(x - 3, y + baseHeight + 2, z - 1, DecoDefs.acaciaLog.blockID);
			world.setBlock(x - 4, y + baseHeight + 3, z - 2, DecoDefs.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 4, z - 2, 3);
			createAcaciaLeaves(world, rand, x - 4, y + baseHeight + 5, z - 2, 2);
		}

		if(rand.nextInt(4) == 0 && 
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 1, y + baseHeight + 1, z - 1) || world.getBlockId(x + 1, y + baseHeight + 1, z - 1) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 2, y + baseHeight + 2, z - 2) || world.getBlockId(x + 2, y + baseHeight + 2, z - 2) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 3, y + baseHeight + 3, z - 2) || world.getBlockId(x + 3, y + baseHeight + 3, z - 2) == DecoDefs.acaciaLeaves.blockID)) {
			world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 1, z - 2, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 2, y + baseHeight + 2, z - 2, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 3, y + baseHeight + 3, z - 2, DecoDefs.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 3, z - 2, 3);
			createAcaciaLeaves(world, rand, x + 3, y + baseHeight + 4, z - 2, 2);
		}

		if(rand.nextInt(4) == 0 && 
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 1) || world.getBlockId(x + 0, y + baseHeight + 0, z - 1) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 0, y + baseHeight + 0, z - 2) || world.getBlockId(x + 0, y + baseHeight + 0, z - 2) == DecoDefs.acaciaLeaves.blockID) && 
				(world.isAirBlock(x + 1, y + baseHeight + 1, z - 3) || world.getBlockId(x + 1, y + baseHeight + 0, z - 3) == DecoDefs.acaciaLeaves.blockID)) {
			world.setBlock(x + 0, y + baseHeight + 0, z - 1, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 0, y + baseHeight + 0, z - 2, DecoDefs.acaciaLog.blockID);
			world.setBlock(x + 1, y + baseHeight + 1, z - 3, DecoDefs.acaciaLog.blockID);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 1, z - 3, 3);
			createAcaciaLeaves(world, rand, x + 1, y + baseHeight + 2, z - 3, 2);
		}

		return true;
	}

	private static void createAcaciaLeaves(World par1World, Random par2Random, int x, int y, int z, int size)
	{
		for(int i = -size + x; i < size + 1 + x; i++) {
			for(int k = -size + z; k < size + 1 + z; k++) {
				int currentID = par1World.getBlockId(i, y, k);
				
				if (currentID == 0)
				{
					if(i == -size + x && k == -size + z ){} else if(i == -size + x && k == size + z ){} else if(i == size + x && k == -size + z ){} else if(i == size + x && k == size + z ){}
					else { par1World.setBlock(i, y, k, DecoDefs.acaciaLeaves.blockID); }
				}
			}
		}
		
		if(size==3){par1World.setBlock(x, y, z, DecoDefs.acaciaLog.blockID);}
	}
}
