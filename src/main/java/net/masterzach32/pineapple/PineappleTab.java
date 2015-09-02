package net.masterzach32.pineapple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.block.PineappleBlocks;
import net.masterzach32.pineapple.item.PineappleItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PineappleTab extends CreativeTabs {

	public static List<Item> items;

	public static Comparator<ItemStack> compItemStacks;

	public PineappleTab(int id, String name) {
		super(id, name);
	}

	public void displayAllReleventItems(List items) {
		super.displayAllReleventItems(items);
		Collections.sort(items, compItemStacks);
	}

	/**
	 * Sets the icon for the pineapple tab.
	 */
	public ItemStack getIconItemStack() {
		return new ItemStack(PineappleItems.pineapple_staff, 1, 0);
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PineappleItems.pineapple_staff;
	}
}