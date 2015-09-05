package net.masterzach32.pineapple.enchantment;

import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.item.magic.GodHealingStaff;
import net.masterzach32.pineapple.item.magic.PineappleStaff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class EnchantmentHeal extends Enchantment {

	public EnchantmentHeal(int par1, int par2) {
		super(par1, par2, Pineapple.enumStaff);
		this.setName("heal");
	}

	public EnchantmentHeal(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
		super(par1, par2, par3EnumEnchantmentType);
	}

	public int getMinEnchantability(int par1) {
		return 10 + 20 * (par1 - 1);
	}

	public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 50;
	}

	public int getMaxLevel() {
		return 4;
	}

	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if (stack.getItem() instanceof PineappleStaff) {
			return true;
		}
		else{
			return false;
		}

	}

    public boolean canApply(ItemStack stack)
    {
    	if (stack.getItem() instanceof PineappleStaff){
    		return true;
    	} else if (stack.getItem() instanceof GodHealingStaff){
    		return true;
    	} else {
    		return false;
    	}
    }
	
}
