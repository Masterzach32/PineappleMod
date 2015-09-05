package net.masterzach32.pineapple.item.magic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import com.google.common.collect.Multimap;

import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.masterzach32.pineapple.core.util.GameMethods;
import net.masterzach32.pineapple.entity.EntityPineapple;
import net.minecraft.nbt.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GodHealingStaff extends PineappleStaff {

	public GodHealingStaff(int damage, int energy) {
		super(damage, energy);
    } 
	
	public ItemStack setNBTTag(ItemStack stack) {
		super.setNBTTag(stack);
		stack.stackTagCompound.setDouble("lifeSteal", 0.0);
		stack.stackTagCompound.setInteger("cost", 50);
		return stack;
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    super.onCreated(stack, world, player);
	    setNBTTag(stack);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean b) {
		if(stack.stackTagCompound == null) stack.setTagCompound(new NBTTagCompound());
		updateStats(stack);
		dataList.add("§e+§c" + (int) stack.stackTagCompound.getDouble("damage") + " §e(1 + 10% Energy) Attack Damage");
		dataList.add("§e+§c" + (int) stack.stackTagCompound.getDouble("lifeSteal") + "% §e(5% Energy) LifeSteal");
		dataList.add("");
		dataList.add("§9Passive: §3Gain 5 energy on hit.");
		dataList.add("§9Passive: §3Deal 6% of your current health");
		dataList.add("§3as bonus damage on hit.");
		dataList.add("§9Active: §3Consume §c" + stack.stackTagCompound.getInteger("cost") + " §3Energy to heal a maximum");
		dataList.add("§3of §c" + (int) stack.stackTagCompound.getDouble("energy") / 20 + "§3 (5% Energy) Heart(s).");
		super.addInformation(stack, player, dataList, b);
		dataList.add("");
		dataList.add("§3§o\"A Godly staff only the Pineapple Gods can use. The");
		dataList.add("§3§oPineapple Gods showed Steve the way to make it");
		dataList.add("§3§oafter he found the shrine of Pineapples\"");
	}
	
	/**
	 * Heals the user if they are missing health.
	 * @param itemStack
	 * @param world
	 * @param player
	 */
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(stack.stackTagCompound.getInteger("cooldown") == 0) {
				if(player.shouldHeal()) {
					updateStats(stack);
					float missingHealth = GameMethods.getMissingHealth((EntityLivingBase) player);
					float current_health = ((EntityLivingBase) player).getHealth();
					if(stack.stackTagCompound.getDouble("energy") < 0.0D) {
						player.addChatMessage(new ChatComponentText("§4Your staff has run out of energy!"));
						updateStats(stack);
					} else if(missingHealth > stack.stackTagCompound.getDouble("energy") / 20) {
						player.heal((float) (stack.stackTagCompound.getDouble("energy") / 20));
						stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") / 5);
						stack.stackTagCompound.setBoolean("showParticles", true);
						updateStats(stack);
						super.onItemRightClick(stack, world, player);
					} else if(missingHealth < stack.stackTagCompound.getDouble("energy") / 20) {
						float f = missingHealth;
						player.heal(missingHealth);
						stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") - f);
						stack.stackTagCompound.setBoolean("showParticles", true);
						updateStats(stack);
						super.onItemRightClick(stack, world, player);
					} else {
						updateStats(stack);
					}
				}
				updateStats(stack);
			}
		}
		if(world.isRemote) {
			if(stack.stackTagCompound.getBoolean("showParticles")) {
				GameMethods.spawnParticles("heal", stack, world, player);
				stack.stackTagCompound.setBoolean("showParticles", false);
			}
		}
		return stack;
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		boolean b = false;
		double missing_health = GameMethods.getMissingHealth((EntityLivingBase) entity);
		double current_health = player.getHealth();
		updateStats(stack);
		float damage = (float) (stack.stackTagCompound.getDouble("damage") + (current_health * 0.06));
		b = entity.attackEntityFrom(Pineapple.pineapple, damage);
		if(b) {
			stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") + 5);
			if(player.shouldHeal()) player.heal((float) (damage * (stack.stackTagCompound.getDouble("lifeSteal") / 100)));
		}
		updateStats(stack);
		return b;
	}
	
	private void updateStats(ItemStack stack) {
		double energy = stack.stackTagCompound.getDouble("energy");
		double damage = stack.stackTagCompound.getDouble("damage");
		double lifeSteal = stack.stackTagCompound.getDouble("lifeSteal");
		
		damage = (1 + (energy * .10));
		lifeSteal = energy * .05;
		if(energy > stack.stackTagCompound.getInteger("maxEnergy")) energy = stack.stackTagCompound.getInteger("maxEnergy");
		
		stack.stackTagCompound.setDouble("energy", energy);
		stack.stackTagCompound.setDouble("damage", damage);
		stack.stackTagCompound.setDouble("lifeSteal", lifeSteal);
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