package net.masterzach32.pineapple.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.PineappleTab;
import net.masterzach32.pineapple.item.PineappleItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PineappleBlocks extends Block {

	public static final Block pineapple_pie = new PineapplePie().setHardness(0.5F).setStepSound(soundTypeCloth).setBlockName("pineapple_pie").setBlockTextureName(Pineapple.MODID + ":pineapple_pie");
	public static final Block pineapple_block = new PineappleBlocks().setHardness(0.5F).setStepSound(soundTypeCloth).setBlockName("pineapple_block").setBlockTextureName(Pineapple.MODID + "pineapple_block").setCreativeTab(PineappleTab.pineapple_tab);
	public static final Block godly_pineapple_block = new GodlyPineappleBlock().setHardness(0.5F).setStepSound(soundTypeCloth).setBlockName("godly_pineapple_block").setBlockTextureName(Pineapple.MODID + "godly_pineapple_block").setCreativeTab(PineappleTab.pineapple_tab);
	public static final Block pineapple_stem = new PineappleStem(pineapple_block).setBlockName("pineapple_stem");
	
	@SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon bottom;

    public PineappleBlocks() {
        super(Material.cactus);
        this.setTickRandomly(true);
    }

    /**
     * Updates the blocks bounds based on its current state.
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.2625F;
        float f1 = (float)(1 + l * 2) / 4.0F;
        float f2 = 0.6F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
    }

    /**
     * Sets the block's bounds for rendering it as an item.
     */
    public void setBlockBoundsForItemRender() {
        float f = 0.2625F;
        float f1 = 0.6F;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.2625F;
        float f1 = (float)(1 + l * 2) / 4.0F;
        float f2 = 0.6F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f1), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)y + f2 - f), (double)((float)(z + 1) - f));
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     * @param world
     * @param x
     * @param y
     * @param z
     */
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        float f = 0.2625F;
        float f1 = (float)(1 + l * 2) / 4.0F;
        float f2 = 0.6F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f1), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)y + f2), (double)((float)(z + 1) - f));
    }

    /**
     * Gets the block's texture.
     * @param side
     * @param meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.top : (side == 0 ? this.bottom : this.blockIcon);
    }

    /**
     * Registers the blocks game texture
     * @param blockIcon
     */
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister blockIcon) {
        this.blockIcon = blockIcon.registerIcon(Pineapple.MODID + ":side");
        this.top = blockIcon.registerIcon(Pineapple.MODID + ":top");
        this.bottom = blockIcon.registerIcon(Pineapple.MODID + ":bottom");
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
    	
        return !super.canPlaceBlockAt(world, x, y, z) ? false : this.canBlockStay(world, x, y, z);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own)
     * @param world
     * @param x
     * @param y
     * @param z
     * @param neighborBlock
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (!this.canBlockStay(world, x, y, z)) {
            world.setBlockToAir(x, y, z);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     * @param world
     * @param x
     * @param y
     * @param z
     */
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z).getMaterial().isSolid();
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     * @param random
     */
    public int quantityDropped(Random random) {
        return 4;
    }

    /**
     * Gets the item dropped on block destruction.
     * @param metadata
     * @param random
     * @param fortune
     */
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return PineappleItems.pineapple;
    }      
} 