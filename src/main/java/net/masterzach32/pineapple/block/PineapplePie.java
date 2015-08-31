package net.masterzach32.pineapple.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.item.PineappleItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PineapplePie extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon pie_top;
    @SideOnly(Side.CLIENT)
    private IIcon pie_bottom;
    @SideOnly(Side.CLIENT)
    private IIcon pie_inside;

    public PineapplePie() {
        super(Material.cake);
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
        float f = 0.0625F;
        float f1 = (float)(1 + l * 2) / 16.0F;
        float f2 = 0.5F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, f2, 1.0F - f);
    }

    /**
     * Sets the block's bounds for rendering it as an item.
     */
    public void setBlockBoundsForItemRender() {
        float f = 0.0625F;
        float f1 = 0.5F;
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
        float f = 0.0625F;
        float f1 = (float)(1 + l * 2) / 16.0F;
        float f2 = 0.5F;
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
        float f = 0.0625F;
        float f1 = (float)(1 + l * 2) / 16.0F;
        float f2 = 0.5F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f1), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)y + f2), (double)((float)(z + 1) - f));
    }

    /**
     * Gets the block's texture.
     * @param side
     * @param meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.pie_top : (side == 0 ? this.pie_bottom : (metadata > 0 && side == 4 ? this.pie_inside : this.blockIcon));
    }

    /**
     * Registers the blocks game texture
     * @param blockIcon
     */
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister blockIcon) {
        this.blockIcon = blockIcon.registerIcon(Pineapple.MODID + ":pie_side");
        this.pie_inside = blockIcon.registerIcon(Pineapple.MODID + ":pie_inner");
        this.pie_top = blockIcon.registerIcon(Pineapple.MODID + ":pie_top");
        this.pie_bottom = blockIcon.registerIcon(Pineapple.MODID + ":pie_bottom");
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
     * Called upon block activation (right click on the block.)
     * @param world
     * @param x
     * @param y
     * @param z
     * @param entityPlayer
     * @param par6
     * @param par7
     * @param par8
     * @param par9
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        this.onEatPineapplePie(world, x, y, z, entityPlayer);
        return true;
    }

    /**
     * Called when a player hits the block.
     * @param world
     * @param x
     * @param y
     * @param z
     * @param entityPlayer
     */
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer) {
        this.onEatPineapplePie(world, x, y, z, entityPlayer);
    }

    private void onEatPineapplePie(World world, int x, int y, int z, EntityPlayer entityPlayer) {
        if(entityPlayer.canEat(false)) {
            entityPlayer.getFoodStats().addStats(2, 0.1F);
            int l = world.getBlockMetadata(x, y, z) + 1;
            
            if (l >= 6) {
                world.setBlockToAir(x, y, z);
            } else {
                world.setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
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
        return 0;
    }

    /**
     * Gets the item dropped on block destruction.
     * @param metadata
     * @param random
     * @param fortune
     */
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return null;
    }

    /**
     * Gets an item for the block being called on.
     * @param world
     * @param x
     * @param y
     * @param z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return PineappleItems.pineapple_pie_cooked;
    }
}