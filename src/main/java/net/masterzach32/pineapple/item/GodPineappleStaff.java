package net.masterzach32.pineapple.item;

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
public class GodPineappleStaff extends PineappleStaff {
	
	public static final int MAX_ENERGY = 200;

	public GodPineappleStaff(int dmg) {
        super(dmg);
        setMaxStackSize(1);
        setMaxDamage(dmg);
    } 
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
	    if(stack.stackTagCompound == null) stack.setTagCompound(new NBTTagCompound());
	    stack.stackTagCompound.setDouble("energy", 0.0);
	    stack.stackTagCompound.setDouble("damage", 0.0);
	    stack.stackTagCompound.setDouble("critChance", 0.0);
	    stack.stackTagCompound.setInteger("cooldown", 0);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean b) {
		if(stack.stackTagCompound == null) stack.setTagCompound(new NBTTagCompound());
		updateStats(stack);
		dataList.add("�e+�c" + (int) stack.stackTagCompound.getDouble("damage") + " �e(1 + 15% Energy) Attack Damage");
		dataList.add("�e+�c" + (int) stack.stackTagCompound.getDouble("critChance") + "% �e(30% Energy) Crit Chance");
		dataList.add("");
		dataList.add("�9Passive: �3Gain 5 energy on hit.");
		dataList.add("�9Passive: �3Deal 10% of the targets missing health");
		dataList.add("�3as bonus damage on hit.");
		dataList.add("�9Active: �3Heal a maximum of �c" + (int) stack.stackTagCompound.getDouble("energy") / 5 / 2 + "�3 (20% Energy) Heart(s).");
		dataList.add("�3The amount of energy consumed to heal cannot");
		dataList.add("�3excede your missing health.");
		dataList.add("�b" + (stack.stackTagCompound.getInteger("cooldown") / 60) + "�3 seconds before next cast.");
		dataList.add("");
		dataList.add("Stored Energy: �c" + (int) stack.stackTagCompound.getDouble("energy") + " / " + (int) MAX_ENERGY);
		dataList.add("");
		dataList.add("�3�o\"A Godly staff only the Pineapple Gods can use. The");
		dataList.add("�3�oPineapple Gods showed Steve the way to make it");
		dataList.add("�3�oafter he found the shrine of Pineapples\"");
	}
	
	/**
	 * Heals the user if they are missing health.
	 * @param itemStack
	 * @param world
	 * @param player
	 */
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(stack.stackTagCompound.getInteger("cooldown") == 0) {
				if(player.shouldHeal()) {
					updateStats(stack);
					float missingHealth = GameMethods.getMissingHealth((EntityLivingBase) player);
					float current_health = ((EntityLivingBase) player).getHealth();
					if(stack.stackTagCompound.getDouble("energy") < 0.0D) {
						player.addChatMessage(new ChatComponentText("�4Your staff has run out of energy!"));
						updateStats(stack);
					} else if(missingHealth > stack.stackTagCompound.getDouble("energy") / 5) {
						player.heal((float) (stack.stackTagCompound.getDouble("energy") / 5));
						stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") / 5);
						GameMethods.spawnParticles("heal", stack, world, player);
						updateStats(stack);
					} else if(missingHealth < stack.stackTagCompound.getDouble("energy") / 5) {
						float f = missingHealth;
						player.heal(missingHealth);
						stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") - f);
						GameMethods.spawnParticles("heal", stack, world, player);
						updateStats(stack);
					} else {
						updateStats(stack);
					}
				}
				updateStats(stack);
			}
		}
		return stack;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		boolean b = false;
		double missing_health = GameMethods.getMissingHealth((EntityLivingBase) entity);
		double current_health = ((EntityLivingBase) entity).getHealth();
		updateStats(stack);
		if (GameMethods.isCritAttack(stack.stackTagCompound.getDouble("critChance"))) {
			b = entity.attackEntityFrom(Pineapple.pineapple, (float) (2*((stack.stackTagCompound.getDouble("energy") + (missing_health * 0.20)))));
			GameMethods.spawnParticles("crit", stack, entity.worldObj, (EntityLivingBase) entity);
		} else {
			b = entity.attackEntityFrom(Pineapple.pineapple, (float) (stack.stackTagCompound.getDouble("damage") + (missing_health * 0.10)));
		}
		if(b) stack.stackTagCompound.setDouble("energy", stack.stackTagCompound.getDouble("energy") + 5);
		updateStats(stack);
		return false;
	}
	
	/*
	@Override
	public boolean onEntitySwing(EntityLivingBase entity, ItemStack stack) {
		float missing_health = getMissingHealth((EntityLivingBase) entity);
		float current_health = ((EntityLivingBase) entity).getHealth();
		swingTick = 0;
		EntityPlayer player = (EntityPlayer) entity;
		while (swingTick == 0) {
			EntityPineapple entitypineapple = new EntityPineapple(mc.theWorld, player, player);
			mc.theWorld.spawnEntityInWorld(entitypineapple);
			if (player != null) {
				energy = (double) (energy + (attack_damage * 0.20) + (missing_health * 0.10));
				updateStats();
				swingTick = 1;
				return false;
			} else {
				swingTick = 1;
				return false;
			}
		}
		return false;
	}
	*/
	
	private void updateStats(ItemStack stack) {
		double energy = stack.stackTagCompound.getDouble("energy");
		double damage = stack.stackTagCompound.getDouble("damage");
		double critChance = stack.stackTagCompound.getDouble("critChance");
		
		damage = (1 + (energy * .15));
		critChance = energy * .3;
		if(energy > MAX_ENERGY) energy = MAX_ENERGY;
		
		stack.stackTagCompound.setDouble("energy", energy);
		stack.stackTagCompound.setDouble("damage", damage);
		stack.stackTagCompound.setDouble("critChance", critChance);
	}
	
	public boolean hasEffect(ItemStack stack) {
		if(stack.stackTagCompound == null) {
			return true;
		}
		if(stack.stackTagCompound.getDouble("energy") >= 100)
			return true;
		else 
			return false;
    }
}