package net.masterzach32.pineapple.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import net.masterzach32.pineapple.item.PineappleItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class PineappleStem extends BlockBush implements IGrowable {
    private final Block block;
    @SideOnly(Side.CLIENT)
    private IIcon icon;

    protected PineappleStem(Block block) {
        this.block = block;
        this.setTickRandomly(true);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * is the block grass, dirt or farmland
     */
    protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.farmland;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);

        if (world.getBlockLightValue(x, y + 1, z) >= 9) {
            float f = this.func_149875_n(world, x, y, z);

            if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                int l = world.getBlockMetadata(x, y, z);

                if (l < 7) {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                } else {
                    if (world.getBlock(x - 1, y, z) == this.block) {
                        return;
                    } if (world.getBlock(x + 1, y, z) == this.block) {
                        return;
                    } if (world.getBlock(x, y, z - 1) == this.block) {
                        return;
                    } if (world.getBlock(x, y, z + 1) == this.block) {
                        return;
                    }

                    int i1 = random.nextInt(4);
                    int j1 = x;
                    int k1 = z;

                    if (i1 == 0) {
                        j1 = x - 1;
                    } if (i1 == 1) {
                        ++j1;
                    } if (i1 == 2) {
                        k1 = z - 1;
                    } if (i1 == 3) {
                        ++k1;
                    }

                    Block block = world.getBlock(j1, y - 1, k1);

                    if (world.isAirBlock(j1, y, k1) && (block.canSustainPlant(world, j1, y - 1, k1, UP, this) || block == Blocks.dirt || block == Blocks.grass)) {
                        world.setBlock(j1, y, k1, this.block);
                    }
                }
            }
        }
    }

    public void func_149874_m(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    private float func_149875_n(World world, int x, int y, int z) {
        float f = 1.0F;
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        Block block4 = world.getBlock(x - 1, y, z - 1);
        Block block5 = world.getBlock(x + 1, y, z - 1);
        Block block6 = world.getBlock(x + 1, y, z + 1);
        Block block7 = world.getBlock(x - 1, y, z + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for (int l = x - 1; l <= x + 1; ++l) {
            for (int i1 = z - 1; i1 <= z + 1; ++i1) {
                Block block8 = world.getBlock(l, y - 1, i1);
                float f1 = 0.0F;

                if (block8.canSustainPlant(world, l, y - 1, i1, UP, this)) {
                    f1 = 1.0F;

                    if (block8.isFertile(world, l, y - 1, i1)) {
                        f1 = 3.0F;
                    }
                }

                if (l != x || i1 != z) {
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }

        if (flag2 || flag && flag1) {
            f /= 2.0F;
        }

        return f;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        int j = p_149741_1_ * 32;
        int k = 255 - p_149741_1_ * 8;
        int l = p_149741_1_ * 4;
        return j << 16 | k << 8 | l;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return this.getRenderColor(blockAccess.getBlockMetadata(x, y, z));
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
        this.maxY = (double)((float)(blockAccess.getBlockMetadata(x, y, z) * 2 + 2) / 16.0F);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {
        return 19;
    }

    /**
     * Returns the current state of the stem. Returns -1 if the stem is not fully grown, or a value between 0 and 3
     * based on the direction the stem is facing.
     */
    @SideOnly(Side.CLIENT)
    public int getState(IBlockAccess iBlockAccess, int x, int y, int z) {
        int l = iBlockAccess.getBlockMetadata(x, y, z);
        return l < 7 ? -1 : (iBlockAccess.getBlock(x - 1, y, z) == this.block ? 0 : (iBlockAccess.getBlock(x + 1, y, z) == this.block ? 1 : (iBlockAccess.getBlock(x, y, z - 1) == this.block ? 2 : (iBlockAccess.getBlock(x, y, z + 1) == this.block ? 3 : -1))));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @SuppressWarnings("unused")
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par_1, float par_2, int par_3) {
        super.dropBlockAsItemWithChance(world, x, y, z, par_1, par_2, par_3);

        if (false && !world.isRemote) {
            Item item = null;

            if (this.block == PineappleBlocks.pineapple_block) {
                item = PineappleItems.pineapple;
            }

            for (int j1 = 0; j1 < 3; ++j1) {
                if (world.rand.nextInt(15) <= par_1) {
                    this.dropBlockAsItem(world, x, y, z, new ItemStack(item));
                }
            }
        }
    }

    public Item getItemDropped(int par_1, Random random, int par_2) {
        return null;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return 1;
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean b) {
        return world.getBlockMetadata(x, y, z) != 7;
    }

    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return true;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this.block == PineappleBlocks.pineapple_block ? PineappleItems.pineapple_seeds : Item.getItemById(0);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(/*this.getTextureName() + */ "minecraft:melon_disconnected");
        this.icon = icon.registerIcon(/*this.getTextureName() + */ "minecraft:melon_connected");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getStemIcon() {
        return this.icon;
    }

    public void func_149853_b(World world, Random random, int x, int y, int z) {
        this.func_149874_m(world, x, y, z);
    }



    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        Item item = null;
        item = block == PineappleBlocks.pineapple_block ? PineappleItems.pineapple_seeds : item;

        for (int i = 0; item != null && i < 3; i++)
        {
            if (world.rand.nextInt(15) <= meta)
                ret.add(new ItemStack(item));
        }

        return ret;
    }
}