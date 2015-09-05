package net.masterzach32.pineapple.item.magic;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BasicStaff extends PineappleStaff {

	public BasicStaff(int damage, int energy) {
		super(damage, energy);
	}
	
	public ItemStack setNBTTag(ItemStack stack) {
		super.setNBTTag(stack);
		stack.stackTagCompound.setDouble("damage", 4.0);
		return stack;
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    super.onCreated(stack, world, player);
	    setNBTTag(stack);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			
		}
        return stack;
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		entity.attackEntityFrom(Pineapple.pineapple, (float) stack.stackTagCompound.getDouble("damage"));
		return false;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean b) {
		if(stack.stackTagCompound == null) {
			setNBTTag(stack);
			
		}
		dataList.add("§e+" + (int) stack.stackTagCompound.getDouble("damage") + " Attack Damage");
		dataList.add("");
		dataList.add("§9Active: §3Does nothing.");
		dataList.add("");
		dataList.add("§3Craft a better staff to");
		dataList.add("§3gain an active effect.");
		super.addInformation(stack, player, dataList, b);
	}
}