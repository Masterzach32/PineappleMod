package net.masterzach32.pineapple.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private double energy = 0;
	private int cooldown;
	private int timer = 1;
	private float attack_damage;
	private double max_energy = 200;
	private static int swingTick;
	
	private float crit_chance;

	public GodPineappleStaff(int dmg) {
        super(dmg);
        setMaxStackSize(1);
        setMaxDamage(dmg);
    } 
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
		updateStats();
		if(true) {
			dataList.add("�e+�c" + (int) attack_damage + " �e(1 + 10% Energy) Attack Damage");
			dataList.add("�e+�c" + (int) crit_chance + "% �e(40% Energy) Crit Chance");
			dataList.add("");
			dataList.add("�9Passive: �3Deal 10% of the targets missing health");
			dataList.add("�3as bonus damage on hit.");
			dataList.add("");
			dataList.add("�9Passive: �320% of damage delt is absorbed as energy.");
			dataList.add("");
			dataList.add("�9Active: �3Heal a maximum of �c" + (int) energy / 5 + "�3 (20% Energy) health.");
			dataList.add("�3The amount of energy consumed to heal cannot");
			dataList.add("�3excede your missing health.");
			dataList.add("�b" + cooldown + "�3 seconds before next cast.");
			dataList.add("");
			dataList.add("Stored Energy: �c" + (int) energy + " / " + (int) max_energy);
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
		// GameMethods.spawnParticles("heal", stack, world, player);
		if (timer == 0)timer++;
		else if (timer == 1) {
			if (player.shouldHeal()){
				float missingHealth = GameMethods.getMissingHealth((EntityLivingBase) player);
				float current_health = ((EntityLivingBase) player).getHealth();
				if (energy < 0.2D) {
					player.addChatMessage(new ChatComponentText("�4Your staff has run out of energy!"));
					//GameMethods.spawnParticles("heal", stack, world, player);
					updateStats();
				} else if (missingHealth > energy / 5) {
					player.heal((float) (energy / 2));
					energy = energy / 2;
					GameMethods.spawnParticles("heal", stack, world, player);
					updateStats();
				} else if (missingHealth < energy / 5) {
					float f = missingHealth;
					player.heal(missingHealth);
					energy = energy - f;
					GameMethods.spawnParticles("heal", stack, world, player);
					updateStats();
				} else {
					updateStats();
				}
			}
			updateStats();
			timer = 0;
		}
        return stack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
		float missing_health = GameMethods.getMissingHealth((EntityLivingBase) entity);
		float current_health = ((EntityLivingBase) entity).getHealth();
		if (timer == 0)timer++;
		else if (timer == 1) {
			//GameMethods.spawnParticles("projectile", stack, entity.worldObj, (EntityLivingBase) entity);
			if (GameMethods.isCritAttack(crit_chance)) {
				entity.attackEntityFrom(Pineapple.pineapple, (float) (2*((attack_damage + (missing_health * 0.10)))));
				GameMethods.spawnParticles("crit", stack, entity.worldObj, (EntityLivingBase) entity);
				energy = (double) (energy + (2 * (attack_damage * 0.10) + (missing_health * 0.01)));
			} else {
				entity.attackEntityFrom(Pineapple.pineapple, (float) (attack_damage + (missing_health * 0.10)));
				energy = (double) (energy + (attack_damage * 0.10) + (missing_health * 0.01));
			}
			updateStats();
		}
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
	
	private void updateStats() {
		attack_damage = (float) (1 + (energy * .10));
		crit_chance = (float) (energy * .40);
		if (energy > max_energy) {
			energy = max_energy;
		}
	}
	
}