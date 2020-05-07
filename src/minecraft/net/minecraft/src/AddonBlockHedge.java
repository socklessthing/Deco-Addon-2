package net.minecraft.src;

public class AddonBlockHedge extends FCBlockLeaves {
	protected AddonBlockHedge(int id) {
		super(id);
		this.blockMaterial = AddonDefs.materialHedge;
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
}