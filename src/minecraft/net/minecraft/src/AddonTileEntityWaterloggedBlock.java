package net.minecraft.src;

import java.util.ArrayList;

public class AddonTileEntityWaterloggedBlock extends TileEntity {
	private boolean isSource;

	@Override
	public void updateEntity() {
		ArrayList<Integer> waterDirs = calcDirectionsForWaterFlow();
		
		for (int i : waterDirs) {
			FCUtilsBlockPos blockPos = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord);
			blockPos.AddFacingAsOffset(i);
			
			if (FCUtilsMisc.CanWaterDisplaceBlock(this.worldObj, blockPos.i, blockPos.j, blockPos.k)) {
				FCUtilsMisc.FlowWaterIntoBlockIfPossible(this.worldObj, blockPos.i, blockPos.j, blockPos.k, 2);
			}
		}
	}

    /**
     * Writes a tile entity to NBT.
     */
	@Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean("isSource", this.isSource);
    }

    /**
     * Reads a tile entity from NBT.
     */
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.isSource = nbtTagCompound.getBoolean("isSource");
    }
	
	private ArrayList<Integer> calcDirectionsForWaterFlow() {
		ArrayList<Integer> waterDirs = new ArrayList<Integer>();
		
		for (int i = 0; i <= 5; i++) {
			if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, i) && i != 1) {
				waterDirs.add(i);
			}
		}
		
		return waterDirs;
	}
	
	public int getWaterFlowToFacing(int facing) {
		if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord, facing) && facing != 1) {
			return 7;
		}
		
		return -1;
	}
}