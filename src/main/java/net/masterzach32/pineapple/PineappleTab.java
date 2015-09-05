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
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.masterzach32.pineapple.item.PineappleItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PineappleTab extends CreativeTabs {

	public List<Item> items;

	public Comparator<ItemStack> compItemStacks;

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
		if(this.getTabLabel() == "pineapple_tab")
			return new ItemStack(PineappleItems.pineapple, 1, 0);
		else if (this.getTabLabel() == "staff_tab") {
			return new ItemStack(PineappleItems.basic_staff, 1, 0);
		} else {
			return new ItemStack(PineappleItems.pineapple, 1, 0);
		}
	}

	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		if(this.getTabLabel() == "pineapple_tab")
			return PineappleItems.pineapple;
		else if (this.getTabLabel() == "staff_tab") {
			return PineappleItems.basic_staff;
		} else {
			return PineappleItems.pineapple;
		}
	}
}