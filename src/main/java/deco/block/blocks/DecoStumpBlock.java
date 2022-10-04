package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import btw.item.items.ChiselItem;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class DecoStumpBlock extends Block {
    public final int WOOD_TYPE;
    public final int CHEWED_LOG_ID;

    private String topTexture;
    private String sideTexture;

    public DecoStumpBlock(int blockID, int woodType, int chewedLogID, String topTexture, String sideTexture) {
        super(blockID, BTWBlocks.logMaterial);

        setHardness(1.25F);
        setResistance(3.33F);
        setAxesEffectiveOn(false);
        setChiselsEffectiveOn(false);

        setBuoyant();
        setFireProperties(Flammability.LOGS);

        setStepSound(soundWoodFootstep);

        this.WOOD_TYPE = woodType;
        this.CHEWED_LOG_ID = chewedLogID;

        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
    }

    @Override
    public boolean getIsProblemToRemove(ItemStack toolStack, IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public int idPicked(World world, int x, int y, int z) {
        return DecoBlocks.cherryLog.blockID;
    }

    @Override
    public ItemStack getStackRetrievedByBlockDispenser(World world, int x, int y, int z) {
        return new ItemStack(DecoBlocks.cherryLog);
    }

    @Override
    public boolean getDoesStumpRemoverWorkOnBlock(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canConvertBlock(ItemStack toolStack, World world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean convertBlock(ItemStack toolStack, World world, int x, int y, int z, int side) {
        if (this.isWorkStumpItemConversionTool(toolStack)) {
            world.playAuxSFX(2268, x, y, z, 0);
            world.setBlockAndMetadataWithNotify(x, y, z, BTWBlocks.workStump.blockID, (WOOD_TYPE - 1) | 8);
        }
        else {
            int newMetadata = BTWBlocks.oakChewedLog.setIsStump(0);
            world.setBlockMetadataWithNotify(x, y, z, CHEWED_LOG_ID, newMetadata);
        }

        return true;
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int fortuneModifier, float chanceOfDrop) {
        this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 6, 0, chanceOfDrop);
        this.dropItemsIndividually(world, x, y, z, BTWItems.bark.itemID, 1, WOOD_TYPE, chanceOfDrop);
        return true;
    }

    @Override
    public void onDestroyedByFire(World world, int x, int y, int z, int fireAge, boolean forcedFireSpread) {
        this.convertToSmouldering(world, x, y, z);
    }

    @Override
    public boolean isLog(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean canSupportLeaves(IBlockAccess blockAccess, int x, int y, int z) {
        return !this.isDeadStump(blockAccess, x, y, z);
    }

    //------------- Class Specific Methods ------------//

    public void convertToSmouldering(World world, int x, int y, int z) {
        int newMetadata = BTWBlocks.smolderingLog.setIsStump(0, true);
        world.setBlockAndMetadataWithNotify(x, y, z, BTWBlocks.smolderingLog.blockID, newMetadata);
    }

    public boolean isWorkStumpItemConversionTool(ItemStack toolStack) {
        if (toolStack != null && toolStack.getItem() instanceof ChiselItem) {
            int harvestLevel = ((ChiselItem) toolStack.getItem()).toolMaterial.getHarvestLevel();
            return harvestLevel >= 2;
        }
        else {
            return false;
        }
    }

    public boolean isDeadStump(IBlockAccess blockAccess, int x, int y, int z) {
        int blockAboveID = blockAccess.getBlockMetadata(x, y + 1, z);
        Block blockAbove = Block.blocksList[blockAboveID];

        if (blockAbove != null && blockAbove.isLog(blockAccess, x, y, z)) {
            return true;
        }

        return false;
    }

    //------------ Client Side Functionality ----------//

    private Icon topIcon;

    @Environment(EnvType.CLIENT)
    @Override
    public void registerIcons(IconRegister register) {
        this.blockIcon = register.registerIcon(this.sideTexture);
        this.topIcon = register.registerIcon(this.topTexture);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata) {
        if (side <= 1) {
            return this.topIcon;
        }
        else {
            return this.blockIcon;
        }
    }
}
