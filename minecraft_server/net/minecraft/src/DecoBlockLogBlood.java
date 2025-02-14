package net.minecraft.src;

import java.util.List;

public class DecoBlockLogBlood extends Block {
    public DecoBlockLogBlood(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetChiselsEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFurnaceBurnTime(4 * FCEnumFurnaceBurnTime.PLANKS_BLOOD.m_iBurnTime);
        this.SetFireProperties(FCEnumFlammability.EXTREME);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockBloodWood");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2225, var2, var3, var4, 0);
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

	public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
	{
		return true;
	}

	public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
	{
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		byte var8 = 0;
		int var10;

		int var9 = var7 >> 2 & 3;
		var10 = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(var8, var9);

		var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoDefs.logDamagedBlood.blockID, var10);

		if (!var2.isRemote && ((var7 & 3) == 0 || (var7 & 3) == 2))
		{
			FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4), var6);
		}

		return true;
	}

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 4, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, 4, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 1, 0, var6);
        return true;
    }
	@Override public boolean CanRotateOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return access.getBlockMetadata(X,Y,Z)!=0;
	}
	@Override public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return true;
	}
	@Override public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess access, int X, int Y, int Z)
	{
		return true;
	}

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1 & 12;

        if (var3 != 0)
        {
            if (var3 == 4)
            {
                var3 = 8;
            }
            else if (var3 == 8)
            {
                var3 = 4;
            }

            var1 = var1 & -13 | var3;
        }

        return var1;
    }
    
	@Override public boolean ToggleFacing(World world, int X, int Y, int Z, boolean var5)
	{
		this.RotateAroundJAxis(world, X, Y, Z, var5);
		return true;
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

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
}
