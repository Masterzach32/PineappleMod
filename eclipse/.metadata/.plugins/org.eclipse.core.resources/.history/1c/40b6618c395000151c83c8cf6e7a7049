package net.masterzach32.pineapple.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PineappleStaff extends Item {
	
	private int damage;
	
	// Gives the staff an amount of uses.
	
	public PineappleStaff(int damage) {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
    }
	
	/**
	 * Heals the user if they have missing health.
	 * @param itemStack
	 * @param world
	 * @param entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.shouldHeal()){
			player.heal(1);
			stack.damageItem(1, player);
		}
        return stack;
    }
	
	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
			entity.attackEntityFrom(Pineapple.pineapple, 3);
		return false;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
		damage = stack.getMaxDamage() - stack.getItemDamage();
		dataList.add("Activate to heal 1 health.");
		dataList.add("§b" + damage + "§7 uses left.");
		dataList.add("");
		dataList.add("§9+3 Attack Damage");
	}
}