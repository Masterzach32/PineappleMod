package net.masterzach32.pineapple.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.masterzach32.pineapple.Pineapple;
import net.masterzach32.pineapple.PineappleTab;
import net.masterzach32.pineapple.block.PineappleBlocks;
import net.masterzach32.pineapple.item.magic.*;
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
			dataList.add("§e+1 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("§3§o\"Most people dont find these, but");
			dataList.add("§3§othey seem to have strange healng powers.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 5, 0, 1.0F).setUnlocalizedName("pineapple").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple");
	public static final Item pineapple_golden = new ItemFood(6, 1.5F, false){
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
			dataList.add("§e+5 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("§3§o\"Steve was lucky to make one of these.");
			dataList.add("§3§oOnly the gods have been known to have");
			dataList.add("§3§opineapples of this power.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 10, 1, 1.0F).setUnlocalizedName("pineapple_golden").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_golden");
	public static final Item godly_pineapple = new ItemFood(10, 2.0F, false){
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List dataList, boolean boal) {
			dataList.add("§e+50 Health Regen per 5 seconds");
			dataList.add("");
			dataList.add("§3§o\"These pineapples are gifts from the gods.");
			dataList.add("§3§oOnly after deep meditation was Steve able");
			dataList.add("§3§oto get one from the mighty pineapple gods.\"");
		}
	}.setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 15, 10, 2.0F).setUnlocalizedName("godly_pineapple").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple");
	public static final Item pineapple_grilled = new ItemFood(7, 1.0F, false).setUnlocalizedName("pineapple_grilled").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_grilled");
	public static final Item pineapple_seeds = new PineappleSeed(PineappleBlocks.pineapple_stem, Blocks.farmland).setUnlocalizedName("pineapple_seeds").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_seeds");
	public static final Item pineapple_pie_uncooked = new Item().setMaxStackSize(1).setUnlocalizedName("pineapple_pie_uncooked").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pie_uncooked");
	public static final Item pineapple_pie_cooked = new ItemReed(PineappleBlocks.pineapple_pie).setMaxStackSize(1).setUnlocalizedName("pineapple_pie_cooked").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pie_cooked");
	// Materials.
	public static final Item pineapple_pie_dough = new Item().setUnlocalizedName("pineapple_pie_dough").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pie_dough");
	// Tools.
	public static final Item pineapple_sword = new ItemSword(PINEAPPLET).setUnlocalizedName("pineapple_sword").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_sword");
	public static final Item pineapple_pickaxe = new PineapplePickaxe(PINEAPPLET).setUnlocalizedName("pineapple_pickaxe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_pickaxe");
	public static final Item pineapple_axe = new PineappleAxe(PINEAPPLET).setUnlocalizedName("pineapple_axe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_axe");
	public static final Item pineapple_shovel = new ItemSpade(PINEAPPLET).setUnlocalizedName("pineapple_shovel").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_shovel");
	public static final Item pineapple_hoe = new ItemHoe(PINEAPPLET).setUnlocalizedName("pineapple_hoe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_hoe");
	// God Pineapple Tools.
	public static final Item godly_pineapple_sword = new ItemSword(PINEAPPLETG).setUnlocalizedName("godly_pineapple_sword").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_sword");
	public static final Item godly_pineapple_pickaxe = new PineapplePickaxe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_pickaxe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_pickaxe");
	public static final Item godly_pineapple_axe = new PineappleAxe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_axe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_axe");
	public static final Item godly_pineapple_shovel = new ItemSpade(PINEAPPLETG).setUnlocalizedName("godly_pineapple_shovel").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_shovel");
	public static final Item godly_pineapple_hoe = new ItemHoe(PINEAPPLETG).setUnlocalizedName("godly_pineapple_hoe").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_hoe");
	// Pineapple Armor.
	public static final Item pineapple_helmet = new PineappleArmor(PINEAPPLEA, 0, 0).setUnlocalizedName("pineapple_helmet").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_helmet");
	public static final Item pineapple_chestplate = new PineappleArmor(PINEAPPLEA, 0, 1).setUnlocalizedName("pineapple_chestplate").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_chestplate");
	public static final Item pineapple_leggings = new PineappleArmor(PINEAPPLEA, 0, 2).setUnlocalizedName("pineapple_leggings").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_leggings");
	public static final Item pineapple_boots = new PineappleArmor(PINEAPPLEA, 0, 3).setUnlocalizedName("pineapple_boots").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":pineapple_boots");
	// Godly Pineapple Armor.
	public static final Item godly_pineapple_helmet = new PineappleArmor(PINEAPPLEAG, 0, 0).setUnlocalizedName("godly_pineapple_helmet").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_helmet");
	public static final Item godly_pineapple_chestplate = new PineappleArmor(PINEAPPLEAG, 0, 1).setUnlocalizedName("godly_pineapple_chestplate").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_chestplate");
	public static final Item godly_pineapple_leggings = new PineappleArmor(PINEAPPLEAG, 0, 2).setUnlocalizedName("godly_pineapple_leggings").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_leggings");
	public static final Item godly_pineapple_boots = new PineappleArmor(PINEAPPLEAG, 0, 3).setUnlocalizedName("godly_pineapple_boots").setCreativeTab(Pineapple.pineapple_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_boots");
	// Staffs
	public static final Item pineapple_rod = new Item().setFull3D().setUnlocalizedName("pineapple_rod").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_rod");
	public static final Item pineapple_essence = new Item().setUnlocalizedName("pineapple_essence").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_essence");
	public static final Item golden_pineapple_essence = new Item().setUnlocalizedName("golden_pineapple_essence").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":golden_pineapple_essence");
	public static final Item godly_pineapple_essence = new Item().setUnlocalizedName("godly_pineapple_essence").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":godly_pineapple_essence");
	public static final Item pineapple_arc = new Item().setUnlocalizedName("pineapple_arc").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_arc");
	public static final Item pineapple_crystal_empty = new PineappleCrystal(0).setUnlocalizedName("pineapple_crystal_empty").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_crystal_empty");
	public static final Item pineapple_crystal_red = new PineappleCrystal(1).setUnlocalizedName("pineapple_crystal_red").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_crystal_red");
	public static final Item pineapple_crystal_green = new PineappleCrystal(2).setUnlocalizedName("pineapple_crystal_green").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":pineapple_crystal_green");
	public static final Item basic_staff = new BasicStaff(100, 0).setFull3D().setUnlocalizedName("basic_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":basic_staff");
	public static final Item damage_staff = new DamageStaff(100, 0).setFull3D().setUnlocalizedName("damage_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":damage_staff");
	public static final Item golden_damage_staff = new GoldDamageStaff(75, 100).setFull3D().setUnlocalizedName("golden_damage_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":golden_healing_staff");
	public static final Item godly_damage_staff = new GodDamageStaff(50, 200).setFull3D().setUnlocalizedName("godly_damage_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":godly_damage_staff");
	public static final Item healing_staff = new HealingStaff(100, 0).setFull3D().setUnlocalizedName("healing_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":healing_staff");
	public static final Item golden_healing_staff = new GoldHealingStaff(75, 100).setFull3D().setUnlocalizedName("golden_healing_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":golden_damage_staff");
	public static final Item godly_healing_staff = new GodHealingStaff(50, 200).setFull3D().setUnlocalizedName("godly_healing_staff").setCreativeTab(Pineapple.staff_tab).setTextureName(Pineapple.MODID + ":godly_healing_staff");
	
}