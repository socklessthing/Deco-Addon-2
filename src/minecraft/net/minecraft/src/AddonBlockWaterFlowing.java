package net.minecraft.src;

import java.util.Random;

public class AddonBlockWaterFlowing extends FCBlockWaterFlowing {
	public AddonBlockWaterFlowing(int id, Material material) {
		super(id, material);
	}

    /**
     * Updates the flow for the BlockFlowing object.
     */
    private void updateFlow(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, this.blockID + 1, var5, 2);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int var6 = this.getFlowDecay(par1World, par2, par3, par4);
        byte var7 = 1;

        if (this.blockMaterial == Material.lava && !par1World.provider.isHellWorld)
        {
            var7 = 2;
        }

        boolean var8 = true;
        int var9;

        if (var6 > 0)
        {
            byte var10 = -100;
            this.numAdjacentSources = 0;
            int var11 = this.getSmallestFlowDecay(par1World, par2 - 1, par3, par4, var10);
            var11 = this.getSmallestFlowDecay(par1World, par2 + 1, par3, par4, var11);
            var11 = this.getSmallestFlowDecay(par1World, par2, par3, par4 - 1, var11);
            var11 = this.getSmallestFlowDecay(par1World, par2, par3, par4 + 1, var11);
            var11 = this.GetSmallestFlowDecayFromCustomSources(par1World, par2, par3, par4, var11);
            int var12 = this.tickRate(par1World);
            var9 = var11 + var7;

            if (var9 >= 8 || var11 < 0)
            {
                var9 = -1;
            }

            if (this.getFlowDecay(par1World, par2, par3 + 1, par4) >= 0)
            {
                int var13 = this.getFlowDecay(par1World, par2, par3 + 1, par4);

                if (var13 >= 8)
                {
                    var9 = var13;
                }
                else
                {
                    var9 = var13 + 8;
                }
            }

            if (this.numAdjacentSources >= 2 && this.blockMaterial == Material.water)
            {
                if (par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid())
                {
                    var9 = 0;
                }
                else if (par1World.getBlockMaterial(par2, par3 - 1, par4) == this.blockMaterial && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0)
                {
                    var9 = 0;
                }
            }

            if (this.blockMaterial == Material.lava && var6 < 8 && var9 < 8 && var9 > var6)
            {
                var12 += var12 * (1 + par5Random.nextInt(4));
            }

            if (var9 == var6)
            {
                if (var8)
                {
                    this.updateFlow(par1World, par2, par3, par4);
                }
            }
            else
            {
                var6 = var9;

                if (var9 < 0)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 2);
                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, var12);
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                }
            }
        }
        else
        {
            this.updateFlow(par1World, par2, par3, par4);
        }

        if (this.liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4))
        {
            if (this.blockMaterial == Material.lava && par1World.getBlockMaterial(par2, par3 - 1, par4) == Material.water)
            {
                par1World.setBlock(par2, par3 - 1, par4, FCBetterThanWolves.fcBlockLavaPillow.blockID);
                this.triggerLavaMixEffects(par1World, par2, par3 - 1, par4);
                return;
            }

            if (var6 >= 8)
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, var6);
            }
            else
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, var6 + 8);
            }
        }
        else if (var6 >= 0 && (var6 == 0 || this.blockBlocksFlow(par1World, par2, par3 - 1, par4)))
        {
            boolean[] var14 = this.getOptimalFlowDirections(par1World, par2, par3, par4);
            var9 = var6 + var7;

            if (var6 >= 8)
            {
                var9 = 1;
            }

            if (var9 >= 8)
            {
                return;
            }

            if (var14[0])
            {
                this.flowIntoBlock(par1World, par2 - 1, par3, par4, var9);
            }

            if (var14[1])
            {
                this.flowIntoBlock(par1World, par2 + 1, par3, par4, var9);
            }

            if (var14[2])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 - 1, var9);
            }

            if (var14[3])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 + 1, var9);
            }
        }
    }

    /**
     * flowIntoBlock(World world, int x, int y, int z, int newFlowDecay) - Flows into the block at the coordinates and
     * changes the block type to the liquid.
     */
    private void flowIntoBlock(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.liquidCanDisplaceBlock(par1World, par2, par3, par4))
        {
            int var6 = par1World.getBlockId(par2, par3, par4);

            if (var6 > 0)
            {
                if (this.blockMaterial == Material.lava)
                {
                    this.triggerLavaMixEffects(par1World, par2, par3, par4);
                }
                else
                {
                    Block.blocksList[var6].OnFluidFlowIntoBlock(par1World, par2, par3, par4, this);
                }
            }

            par1World.setBlock(par2, par3, par4, this.blockID, par5, 3);
        }
    }

    /**
     * calculateFlowCost(World world, int x, int y, int z, int accumulatedCost, int previousDirectionOfFlow) - Used to
     * determine the path of least resistance, this method returns the lowest possible flow cost for the direction of
     * flow indicated. Each necessary horizontal flow adds to the flow cost.
     */
    private int calculateFlowCost(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        int var7 = 1000;

        for (int var8 = 0; var8 < 4; ++var8)
        {
            if ((var8 != 0 || par6 != 1) && (var8 != 1 || par6 != 0) && (var8 != 2 || par6 != 3) && (var8 != 3 || par6 != 2))
            {
                int var9 = par2;
                int var10 = par4;

                if (var8 == 0)
                {
                    var9 = par2 - 1;
                }

                if (var8 == 1)
                {
                    ++var9;
                }

                if (var8 == 2)
                {
                    var10 = par4 - 1;
                }

                if (var8 == 3)
                {
                    ++var10;
                }

                if (!this.blockBlocksFlow(par1World, var9, par3, var10) && (par1World.getBlockMaterial(var9, par3, var10) != this.blockMaterial || par1World.getBlockMetadata(var9, par3, var10) != 0))
                {
                    if (!this.blockBlocksFlow(par1World, var9, par3 - 1, var10))
                    {
                        return par5;
                    }

                    if (par5 < 4)
                    {
                        int var11 = this.calculateFlowCost(par1World, var9, par3, var10, par5 + 1, var8);

                        if (var11 < var7)
                        {
                            var7 = var11;
                        }
                    }
                }
            }
        }

        return var7;
    }

    /**
     * Returns a boolean array indicating which flow directions are optimal based on each direction's calculated flow
     * cost. Each array index corresponds to one of the four cardinal directions. A value of true indicates the
     * direction is optimal.
     */
    private boolean[] getOptimalFlowDirections(World par1World, int par2, int par3, int par4)
    {
        int var5;
        int var6;

        for (var5 = 0; var5 < 4; ++var5)
        {
            this.flowCost[var5] = 1000;
            var6 = par2;
            int var7 = par4;

            if (var5 == 0)
            {
                var6 = par2 - 1;
            }

            if (var5 == 1)
            {
                ++var6;
            }

            if (var5 == 2)
            {
                var7 = par4 - 1;
            }

            if (var5 == 3)
            {
                ++var7;
            }

            if (!this.blockBlocksFlow(par1World, var6, par3, var7) && (par1World.getBlockMaterial(var6, par3, var7) != this.blockMaterial || par1World.getBlockMetadata(var6, par3, var7) != 0))
            {
                if (this.blockBlocksFlow(par1World, var6, par3 - 1, var7))
                {
                    this.flowCost[var5] = this.calculateFlowCost(par1World, var6, par3, var7, 1, var5);
                }
                else
                {
                    this.flowCost[var5] = 0;
                }
            }
        }

        var5 = this.flowCost[0];

        for (var6 = 1; var6 < 4; ++var6)
        {
            if (this.flowCost[var6] < var5)
            {
                var5 = this.flowCost[var6];
            }
        }

        for (var6 = 0; var6 < 4; ++var6)
        {
            this.isOptimalFlowDirection[var6] = this.flowCost[var6] == var5;
        }

        return this.isOptimalFlowDirection;
    }

    /**
     * Returns true if the block at the coordinates can be displaced by the liquid.
     */
    private boolean liquidCanDisplaceBlock(World par1World, int par2, int par3, int par4)
    {
        Material var5 = par1World.getBlockMaterial(par2, par3, par4);
        return var5 == this.blockMaterial ? false : (var5 == Material.lava ? false : !this.blockBlocksFlow(par1World, par2, par3, par4));
    }

    private int GetSmallestFlowDecayFromCustomSources(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 != 0)
        {
            for (int var6 = 0; var6 < 6; ++var6)
            {
                int var7 = AddonUtilsBlock.IsValidSourceForFluidBlockToFacing(var1, var2, var3, var4, var6);

                if (var7 == 0)
                {
                    var5 = 0;
                    break;
                }

                if (var7 > 0 && (var5 < 0 || var7 < var5))
                {
                    var5 = var7;
                }
            }
        }

        return var5;
    }
    
    //CLIENT ONLY
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int var6;
        double var11;

        if (this.blockMaterial == Material.water)
        {
            if (rand.nextInt(10) == 0)
            {
                var6 = world.getBlockMetadata(x, y, z);

                if (var6 <= 0 || var6 >= 8)
                {
                    world.spawnParticle("suspended", (double)((float)x + rand.nextFloat()), (double)((float)y + rand.nextFloat()), (double)((float)z + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
            }

            for (var6 = 0; var6 < 0; ++var6)
            {
                int var7 = rand.nextInt(4);
                int var8 = x;
                int var9 = z;

                if (var7 == 0)
                {
                    var8 = x - 1;
                }

                if (var7 == 1)
                {
                    ++var8;
                }

                if (var7 == 2)
                {
                    var9 = z - 1;
                }

                if (var7 == 3)
                {
                    ++var9;
                }

                if (world.getBlockMaterial(var8, y, var9) == Material.air && (world.getBlockMaterial(var8, y - 1, var9).blocksMovement() || world.getBlockMaterial(var8, y - 1, var9).isLiquid()))
                {
                    float var10 = 0.0625F;
                    var11 = (double)((float)x + rand.nextFloat());
                    double var13 = (double)((float)y + rand.nextFloat());
                    double var15 = (double)((float)z + rand.nextFloat());

                    if (var7 == 0)
                    {
                        var11 = (double)((float)x - var10);
                    }

                    if (var7 == 1)
                    {
                        var11 = (double)((float)(x + 1) + var10);
                    }

                    if (var7 == 2)
                    {
                        var15 = (double)((float)z - var10);
                    }

                    if (var7 == 3)
                    {
                        var15 = (double)((float)(z + 1) + var10);
                    }

                    double var17 = 0.0D;
                    double var19 = 0.0D;

                    if (var7 == 0)
                    {
                        var17 = (double)(-var10);
                    }

                    if (var7 == 1)
                    {
                        var17 = (double)var10;
                    }

                    if (var7 == 2)
                    {
                        var19 = (double)(-var10);
                    }

                    if (var7 == 3)
                    {
                        var19 = (double)var10;
                    }

                    world.spawnParticle("splash", var11, var13, var15, var17, 0.0D, var19);
                }
            }
        }

        if (this.blockMaterial == Material.water && rand.nextInt(64) == 0)
        {
            var6 = world.getBlockMetadata(x, y, z);

            if (var6 > 0 && var6 < 8)
            {
                world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "liquid.water", rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() * 1.0F + 0.5F, false);
            }
        }

        double var21;
        double var22;

        if (this.blockMaterial == Material.lava && world.getBlockMaterial(x, y + 1, z) == Material.air && !world.isBlockOpaqueCube(x, y + 1, z))
        {
            if (rand.nextInt(100) == 0)
            {
                var21 = (double)((float)x + rand.nextFloat());
                var11 = (double)y + this.maxY;
                var22 = (double)((float)z + rand.nextFloat());
                world.spawnParticle("lava", var21, var11, var22, 0.0D, 0.0D, 0.0D);
                world.playSound(var21, var11, var22, "liquid.lavapop", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }

            if (rand.nextInt(200) == 0)
            {
                world.playSound((double)x, (double)y, (double)z, "liquid.lava", 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }

        if (rand.nextInt(10) == 0 && world.doesBlockHaveSolidTopSurface(x, y - 1, z) && !world.getBlockMaterial(x, y - 2, z).blocksMovement())
        {
            var21 = (double)((float)x + rand.nextFloat());
            var11 = (double)y - AddonUtilsBlock.getFluidDripOffsetForBlockType(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y - 1, z));
            var22 = (double)((float)z + rand.nextFloat());

            if (this.blockMaterial == Material.water)
            {
                world.spawnParticle("dripWater", var21, var11, var22, 0.0D, 0.0D, 0.0D);
            }
            else
            {
                world.spawnParticle("dripLava", var21, var11, var22, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}