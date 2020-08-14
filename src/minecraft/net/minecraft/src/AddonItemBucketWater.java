package net.minecraft.src;

public class AddonItemBucketWater extends FCItemBucketWater {
	public AddonItemBucketWater(int var1) {
		super(var1);
	}
	
    protected boolean AttemptPlaceContentsAtLocation(World world, int x, int y, int z) {
    	if (AddonUtilsBlock.canBlockBeWaterlogged(world, x, y, z)) {
    		AddonTileEntityWaterloggedBlock waterloggedBlock = new AddonTileEntityWaterloggedBlock();
    		world.setBlockTileEntity(x, y, z, waterloggedBlock);
    		System.out.println("Added waterlogged block");
    		return true;
    	}
    	else {
    		return super.AttemptPlaceContentsAtLocation(world, x, y, z);
    	}
    }
}