package net.masterzach32.pineapple.item.magic;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.core.util.GameMethods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PineappleStaff extends Item {
	
	public int maxEnergy;
	public int maxUses;
	
	public PineappleStaff(int damage, int energy) {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
        maxUses = damage;
        maxEnergy = energy;
    }
	
	public ItemStack setNBTTag(ItemStack stack) {
		if(stack.stackTagCompound == null) stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setInteger("maxEnergy", maxEnergy);
	    stack.stackTagCompound.setDouble("energy", 0.0);
	    stack.stackTagCompound.setDouble("damage", 0.0);
	    stack.stackTagCompound.setInteger("cooldown", 0);
	    stack.stackTagCompound.setInteger("cost", 0);
	    stack.stackTagCompound.setInteger("uses", maxUses);
	    stack.stackTagCompound.setBoolean("showParticles", false);
		return stack;
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
		stack.setItemDamage(maxUses - stack.stackTagCompound.getInteger("uses"));
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    setNBTTag(stack);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) stack.damageItem(1, player);
        return stack;
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		entity.attackEntityFrom(Pineapple.pineapple, (float) stack.stackTagCompound.getDouble("damage"));
		return false;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
		if(stack.stackTagCompound == null) {
			setNBTTag(stack);
		}
		dataList.add("");
		dataList.add("Cooldown: §b" + (stack.stackTagCompound.getInteger("cooldown") / 60) + " §3seconds.");
		dataList.add("Uses Left: §b" + stack.stackTagCompound.getInteger("uses") + " / " + maxUses);
		dataList.add("Stored Energy: §c" + (int) stack.stackTagCompound.getDouble("energy") + " / " + (int) stack.stackTagCompound.getInteger("maxEnergy"));
	}
}