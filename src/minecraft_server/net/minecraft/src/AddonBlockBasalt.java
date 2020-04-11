package net.minecraft.src;

public class AddonBlockBasalt extends Block {
	public AddonBlockBasalt(int id) {
		super(id, FCBetterThanWolves.fcMaterialNetherRock);
		this.SetPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override public int GetFacing(IBlockAccess access, int X, int Y, int Z)
	{
		return access.getBlockMetadata(X,Y,Z);
	}
	@Override public void SetFacing(World world, int X, int Y, int Z, int facing)
	{
		world.setBlockMetadataWithNotify(X,Y,Z,facing);
	}
	@Override public int GetFacing(int meta)
	{
		return meta;
	}
	@Override public int SetFacing(int var1, int var2)
	{
		return var2;
	}
	@Override 
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int var10 = par9 & 3;
        byte var11 = 0;

        switch (par5)
        {
            case 0:
            case 1:
                var11 = 0;
                break;

            case 2:
            case 3:
                var11 = 8;
                break;

            case 4:
            case 5:
                var11 = 4;
        }

        return var10 | var11;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }
}
