package net.masterzach32.pineapple.item.magic;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.core.util.GameMethods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GodDamageStaff extends PineappleStaff {
	
	public GodDamageStaff(int damage, int energy) {
		super(damage, energy);
	}

	public ItemStack setNBTTag(ItemStack stack) {
		super.setNBTTag(stack);
	    stack.stackTagCompound.setInteger("cost", 50);
	    stack.stackTagCompound.setDouble("critChance", 0.0);
		return stack;
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    super.onCreated(stack, world, player);
	    setNBTTag(stack);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			updateStats(stack);
			if(stack.stackTagCompound.getInteger("cooldown") == 0) {
				super.onItemRightClick(stack, world, player);
			}
		}
        return stack;
    }
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		updateStats(stack);
		boolean b;
		if(GameMethods.isCritAttack(stack.stackTagCompound.getDouble("critchance"))) {
			b = entity.attackEntityFrom(Pineapple.pineapple, (float) (2 * stack.stackTagCompound.getDouble("damage")));
			GameMethods.spawnParticles("crit", stack, entity.worldObj, (EntityLivingBase) entity);
		} else {
			b = entity.attackEntityFrom(Pineapple.pineapple, (float) (stack.stackTagCompound.getDouble("damage")));
		}
		if(b) stack.stackTagCompound.setDouble("energy" , stack.stackTagCompound.getDouble("energy") + 5);
		updateStats(stack);
		return b;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean b) {
		if(stack.stackTagCompound == null) {
			setNBTTag(stack);
		}
		updateStats(stack);
		dataList.add("§e+§c" + (int) stack.stackTagCompound.getDouble("damage") +" §e(1 + 15% Energy) Attack Damage");
		dataList.add("§e+§c" + (int) stack.stackTagCompound.getDouble("critChance") +"% §e(25% Energy) Crit Chance");
		dataList.add("");
		dataList.add("§9Passive: §3Gain 5 energy on hit.");
		dataList.add("§9Passive: §3Deal 10% of the targets missing health");
		dataList.add("§3as bonus damage on hit.");
		dataList.add("§9Active: §3Consume §c" + stack.stackTagCompound.getInteger("cost") + "§3 Energy to deal §c" + (int) (stack.stackTagCompound.getDouble("damage") * 2) + " §3(200% Attack Damage)");
		dataList.add("§3Damage in an area infront of you.");
		super.addInformation(stack, player, dataList, b);
	}
	
	private void updateStats(ItemStack stack) {
		double energy = stack.stackTagCompound.getDouble("energy");
		double damage = stack.stackTagCompound.getDouble("damage");
		double critChance = stack.stackTagCompound.getDouble("critChance");
		
		damage = (1 + (energy * .15));
		critChance = energy * .25;
		if(energy > stack.stackTagCompound.getInteger("maxEnergy")) energy = stack.stackTagCompound.getInteger("maxEnergy");
		
		stack.stackTagCompound.setDouble("energy", energy);
		stack.stackTagCompound.setDouble("damage", damage);
		stack.stackTagCompound.setDouble("critChance", critChance);
	}
	
	public boolean hasEffect(ItemStack stack) {
		if(stack.stackTagCompound == null) {
			setNBTTag(stack);
		}
		
		if(stack.stackTagCompound.getDouble("energy") >= 50) {
			return true;
		} else {
			return false;
		}
    }
}