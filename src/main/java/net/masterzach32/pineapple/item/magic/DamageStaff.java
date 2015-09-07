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

public class DamageStaff extends PineappleStaff {
	
	public DamageStaff(int damage, int energy) {
		super(damage, energy);
	}

	public ItemStack setNBTTag(ItemStack stack) {
		super.setNBTTag(stack);
		stack.stackTagCompound.setDouble("damage", 7.0);
		return stack;
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    super.onCreated(stack, world, player);
	    setNBTTag(stack);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(stack.stackTagCompound.getInteger("cooldown") == 0) {
				super.onItemRightClick(stack, world, player);
			}
		}
        return stack;
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		boolean b = entity.attackEntityFrom(Pineapple.pineapple, (float) stack.stackTagCompound.getDouble("damage"));
		return b;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean b) {
		if(stack.stackTagCompound == null) {
			setNBTTag(stack);
		}
		dataList.add("§e+" + (int) stack.stackTagCompound.getDouble("damage") +" Attack Damage");
		dataList.add("");
		dataList.add("§9Active: §3Deal §c4 §3Damage in");
		dataList.add("§3an area infront of you.");
		super.addInformation(stack, player, dataList, b);
	}
}