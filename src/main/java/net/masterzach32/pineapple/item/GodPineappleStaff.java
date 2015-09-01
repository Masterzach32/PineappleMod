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
	
	private double[] energy = new double[256];
	private int[] cooldown = new int[256];
	private int timer = 1;
	private double[] attack_damage = new double[256];
	private double max_energy = 200;
	private static int swingTick;
	
	private double[] crit_chance = new double[256];
	
	private ArrayList<ItemStack> staffs = new ArrayList<ItemStack>();

	public GodPineappleStaff(int dmg) {
        super(dmg);
        setMaxStackSize(1);
        setMaxDamage(dmg);
    } 
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
		int i = updateStats(stack);
		if(true) {
			dataList.add("�e+�c" + (int) attack_damage[i]+ " �e(1 + 10% Energy) Attack Damage");
			dataList.add("�e+�c" + (int) crit_chance[i] + "% �e(40% Energy) Crit Chance");
			dataList.add("");
			dataList.add("�9Passive: �3Deal 10% of the targets missing health");
			dataList.add("�3as bonus damage on hit.");
			dataList.add("");
			dataList.add("�9Passive: �320% of damage delt is absorbed as energy.");
			dataList.add("");
			dataList.add("�9Active: �3Heal a maximum of �c" + (int) energy[i] / 5 / 2 + "�3 (20% Energy) Heart(s).");
			dataList.add("�3The amount of energy consumed to heal cannot");
			dataList.add("�3excede your missing health.");
			dataList.add("�b" + cooldown[i] + "�3 seconds before next cast.");
			dataList.add("");
			dataList.add("Stored Energy: �c" + (int) energy[i] + " / " + (int) max_energy);
		} /*else {
			dataList.add("Right Click to heal �c" + (int) energy / 5 + "�7 health.");
			//dataList.add("�b" + cooldown + "�7 seconds before next cast.");
			dataList.add("20% of damage delt is absorbed as energy.");
			//dataList.add("Press �fSHIFT �7to read more.");
			dataList.add("");
			dataList.add("�9+�c" + (int) attack_damage + "�9 Attack Damage");
			dataList.add("�9+�c" + (int) crit_chance + "%�9 Crit Chance");
			dataList.add("�310% Missing Health bonus damage");
			dataList.add("");
			dataList.add("Stored Energy: �c" +(int) energy + " / " + (int) max_energy);
		//}*/
	}
	
	/**
	 * Heals the user if they are missing health.
	 * @param itemStack
	 * @param world
	 * @param player
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.shouldHeal()) {
			int i = updateStats(stack);
			float missingHealth = GameMethods.getMissingHealth((EntityLivingBase) player);
			float current_health = ((EntityLivingBase) player).getHealth();
			if(energy[i] < 0.2D) {
				player.addChatMessage(new ChatComponentText("�4Your staff has run out of energy!"));
				updateStats(stack);
			} else if(missingHealth > energy[i] / 5) {
				player.heal((float) (energy[i] / 2));
				energy[i] = energy[i] / 2;
				GameMethods.spawnParticles("heal", stack, world, player);
				updateStats(stack);
			} else if(missingHealth < energy[i] / 5) {
				float f = missingHealth;
				player.heal(missingHealth);
				energy[i] = energy[i] - f;
				GameMethods.spawnParticles("heal", stack, world, player);
				updateStats(stack);
			} else {
				updateStats(stack);
			}
		}
		updateStats(stack);
		timer = 0;
		return stack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		float missing_health = GameMethods.getMissingHealth((EntityLivingBase) entity);
		float current_health = ((EntityLivingBase) entity).getHealth();
		int i = updateStats(stack);
		if (GameMethods.isCritAttack(crit_chance[i])) {
			entity.attackEntityFrom(Pineapple.pineapple, (float) (2*((attack_damage[i] + (missing_health * 0.20)))));
			GameMethods.spawnParticles("crit", stack, entity.worldObj, (EntityLivingBase) entity);
			energy[i] = (double) (energy[i] + (2 * (attack_damage[i] * 0.10) + (missing_health * 0.04)));
		} else {
			entity.attackEntityFrom(Pineapple.pineapple, (float) (attack_damage[i] + (missing_health * 0.10)));
			energy[i] = (double) (energy[i] + (attack_damage[i] * 0.10) + (missing_health * 0.02));
		}
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
	
	private int updateStats(ItemStack stack) {
		boolean b = false;
		int i;
		for(i = 0; i < staffs.size(); i++) {
			ItemStack staff = staffs.get(i);
			if(staff.equals(stack)) {
				b = true;
				break;
			} else {
				b = false;
			}
		}
		if(!b) {
			staffs.add(stack);
			i++;
			energy[i] = 0;
		}
		attack_damage[i] = (float) (1 + (energy[i] * .15));
		crit_chance[i] = (float) (energy[i] * .40);
		if (energy[i] > max_energy) {
			energy[i] = max_energy;
		}
		return i;
	}
}