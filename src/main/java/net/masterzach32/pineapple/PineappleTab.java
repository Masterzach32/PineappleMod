package net.masterzach32.pineapple;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.item.PineappleItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PineappleTab {

	public static CreativeTabs pineapple_tab = new CreativeTabs("pineapple_tab") {
		
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
	};
}