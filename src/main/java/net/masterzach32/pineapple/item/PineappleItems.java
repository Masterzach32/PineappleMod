package net.masterzach32.pineapple.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.PineappleTab;
import net.masterzach32.pineapple.block.PineappleBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PineappleItems extends Item {

	public static ToolMaterial PINEAPPLETG = EnumHelper.addToolMaterial("PINEAPPLE", 40, 1000, 40.0F, 20, 20);
	public static ToolMaterial PINEAPPLET = EnumHelper.addToolMaterial("PINEAPPLEG", 3, 300, 6.0F, 7, 10);
	public static ArmorMaterial PINEAPPLEA = EnumHelper.addArmorMaterial("PINEAPPLE", 35, new int[]{4, 8, 6, 4}, 15);
	public static ArmorMaterial PINEAPPLEAG = EnumHelper.addArmorMaterial("PINEAPPLEG", 100, new int[]{12, 24, 18, 12}, 25);
	
	static class PineapplePickaxe extends ItemPickaxe {
		protected PineapplePickaxe(ToolMaterial material) {
			super(material);
		}
	}
	static class PineappleAxe extends ItemAxe {
		protected PineappleAxe(ToolMaterial material) {
			super(material);
		}
	}
	
	protected PineappleItems() {
		super();
	}
	
	// Food.
	public static final Item pineapple = new ItemFood(4, 1.0F, false){
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack , EntityPlayer player, List dataList, boolean boal) {
			dataList.add("�e+1 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("�3�o\"Most people dont find these, but");
			dataList.add("�3�othey seem to have strange healng powers.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 5, 0, 1.0F).setUnlocalizedName("pineapple").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple");
	public static final Item pineapple_golden = new ItemFood(6, 1.5F, false){
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
			dataList.add("�e+5 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("�3�o\"Steve was lucky to make one of these.");
			dataList.add("�3�oOnly the gods have been known to have");
			dataList.add("�3�opineapples of this power.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 10, 1, 1.0F).setUnlocalizedName("pineapple_golden").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_golden");
	public static final Item godly_pineapple = new ItemFood(10, 2.0F, false){
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
			dataList.add("�e+50 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("�3�o\"These pineapples are gifts from the gods.");
			dataList.add("�3�oOnly after deep meditation was Steve");
			dataList.add("�3�oable to get one from the mighty pineapple gods.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 15, 10, 2.0F).setUnlocalizedName("godly_pineapple").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple");
	public static final Item pineapple_grilled = new ItemFood(7, 1.0F, false).setUnlocalizedName("pineapple_grilled").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_grilled");
	public static final Item pineapple_seeds = new PineappleSeed(PineappleBlocks.pineapple_stem, Blocks.farmland).setUnlocalizedName("pineapple_seeds").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_seeds");
	public static final Item pineapple_pie_uncooked = new Item().setMaxStackSize(1).setUnlocalizedName("pineapple_pie_uncooked").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pie_uncooked");
	public static final Item pineapple_pie_cooked = new ItemReed(PineappleBlocks.pineapple_pie).setMaxStackSize(1).setUnlocalizedName("pineapple_pie_cooked").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pie_cooked");
	// Materials.
	public static final Item pineapple_rod = new Item().setFull3D().setUnlocalizedName("pineapple_rod").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_rod");
	public static final Item pineapple_pie_dough = new Item().setUnlocalizedName("pineapple_pie_dough").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pie_dough");
	// Tools.
	public static final Item pineapple_staff = new PineappleStaff(100).setFull3D().setUnlocalizedName("pineapple_staff").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_staff");
	public static final Item pineapple_sword = new ItemSword(PINEAPPLET).setUnlocalizedName("pineapple_sword").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_sword");
	public static final Item pineapple_pickaxe = new PineapplePickaxe(PINEAPPLET).setUnlocalizedName("pineapple_pickaxe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pickaxe");
	public static final Item pineapple_axe = new PineappleAxe(PINEAPPLET).setUnlocalizedName("pineapple_axe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_axe");
	public static final Item pineapple_shovel = new ItemSpade(PINEAPPLET).setUnlocalizedName("pineapple_shovel").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_shovel");
	public static final Item pineapple_hoe = new ItemHoe(PINEAPPLET).setUnlocalizedName("pineapple_hoe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_hoe");
	// God Pineapple Tools.
	public static final Item godly_pineapple_staff = new GodPineappleStaff(10).setFull3D().setUnlocalizedName("godly_pineapple_staff").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_staff");
	public static final Item godly_pineapple_sword = new ItemSword(PINEAPPLETG).setUnlocalizedName("godly_pineapple_sword").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_sword");
	public static final Item godly_pineapple_pickaxe = new PineapplePickaxe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_pickaxe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_pickaxe");
	public static final Item godly_pineapple_axe = new PineappleAxe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_axe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_axe");
	public static final Item godly_pineapple_shovel = new ItemSpade(PINEAPPLETG).setUnlocalizedName("godly_pineapple_shovel").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_shovel");
	public static final Item godly_pineapple_hoe = new ItemHoe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_hoe").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_hoe");
	// Pineapple Armor.
	public static final Item pineapple_helmet = new ItemArmor(PINEAPPLEA, 0, 0).setUnlocalizedName("pineapple_helmet").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_helmet");
	public static final Item pineapple_chestplate = new ItemArmor(PINEAPPLEA, 0, 1).setUnlocalizedName("pineapple_chestplate").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_chestplate");
	public static final Item pineapple_leggings = new ItemArmor(PINEAPPLEA, 0, 2).setUnlocalizedName("pineapple_leggings").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_leggings");
	public static final Item pineapple_boots = new ItemArmor(PINEAPPLEA, 0, 3).setUnlocalizedName("pineapple_boots").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_boots");
	// Godly Pineapple Armor.
	public static final Item godly_pineapple_helmet = new ItemArmor(PINEAPPLEAG, 0, 0).setUnlocalizedName("godly_pineapple_helmet").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_helmet");
	public static final Item godly_pineapple_chestplate = new ItemArmor(PINEAPPLEAG, 0, 1).setUnlocalizedName("godly_pineapple_chestplate").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_chestplate");
	public static final Item godly_pineapple_leggings = new ItemArmor(PINEAPPLEAG, 0, 2).setUnlocalizedName("godly_pineapple_leggings").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_leggings");
	public static final Item godly_pineapple_boots = new ItemArmor(PINEAPPLEAG, 0, 3).setUnlocalizedName("godly_pineapple_boots").setCreativeTab(PineappleTab.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_boots");
	
	/**
	 * Registers the armor textures that the player sees in game.
	 * @param stack
	 * @param entity
	 * @param slot
	 * @param type
	 */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {  	
    	if(stack.getItem() == pineapple_helmet || stack.getItem() == pineapple_chestplate || stack.getItem() == pineapple_boots) {
    		return Pineapple.MODID + ":textures/models/armor/pineapple_layer_1.png";   		
    	}   	
    	if(stack.getItem() == pineapple_leggings) {   		
    		return Pineapple.MODID + ":textures/models/armor/pineapple_layer_2.png";   	
    	}     	
    	if(stack.getItem() == godly_pineapple_helmet || stack.getItem() == godly_pineapple_chestplate || stack.getItem() == godly_pineapple_boots) {
    		return Pineapple.MODID + ":textures/models/armor/godpineapple_layer_1.png";   		
    	}    	
    	if(stack.getItem() == godly_pineapple_leggings) {    		
    		return Pineapple.MODID + ":textures/models/armor/godpineapple_layer_2.png";    	
    	} else { 
        	return null;
    	}
    }
	
}
