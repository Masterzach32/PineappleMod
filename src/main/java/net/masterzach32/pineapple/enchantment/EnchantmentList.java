package net.masterzach32.pineapple.enchantment;

import net.minecraft.enchantment.Enchantment;

public class EnchantmentList {

	public static Enchantment heal;

	public static void init() {
		
		heal = new EnchantmentHeal(1, 125);
		
	}

}
