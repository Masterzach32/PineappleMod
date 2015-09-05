package net.masterzach32.pineapple.core.helper;

import net.masterzach32.pineapple.block.PineappleBlocks;
import net.masterzach32.pineapple.item.PineappleItems;
import net.masterzach32.pineapple.world.WorldGenPineapple;
import net.masterzach32.pineapple.world.WorldGenShrine;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelper {
	
	/**
	 * Registers all blocks.
	 * @param v
	 */
	public static void registerBlocks() {
        // Calls Pineapple Blocks!
		GameRegistry.registerBlock(PineappleBlocks.pineapple_block, "pineapple_block");
		GameRegistry.registerBlock(PineappleBlocks.godly_pineapple_block, "godly_pineapple_block");
	    GameRegistry.registerBlock(PineappleBlocks.pineapple_pie, "pineapple_pie");
	    //GameRegistry.registerBlock(pineapple_blocks.pineappleStem, "pineappleStem");
	}
	
	/**
	 * Registers all items.
	 * @param v
	 */
	public static void registerItems() {
        // Calls Pineapples!
        GameRegistry.registerItem(PineappleItems.pineapple, "pineapple");
        GameRegistry.registerItem(PineappleItems.pineapple_golden, "pineapple_golden");
        GameRegistry.registerItem(PineappleItems.godly_pineapple, "godly_pineapple");
        GameRegistry.registerItem(PineappleItems.pineapple_grilled, "pineapple_grilled");
        // GameRegistry.registerItem(PineappleItems.pineappleSeeds, "pineappleSeeds");
        GameRegistry.registerItem(PineappleItems.pineapple_pie_dough, "pineapple_pie_dough");
        GameRegistry.registerItem(PineappleItems.pineapple_pie_uncooked, "pineapple_pie_uncooked");
        GameRegistry.registerItem(PineappleItems.pineapple_pie_cooked, "pineapple_pie_cooked");
        // Calls Pineapple Tools!
        GameRegistry.registerItem(PineappleItems.pineapple_sword, "pineapple_sword");
        GameRegistry.registerItem(PineappleItems.pineapple_pickaxe, "pineapple_pickaxe_");
        GameRegistry.registerItem(PineappleItems.pineapple_axe, "pineapple_axe");
        GameRegistry.registerItem(PineappleItems.pineapple_shovel, "pineapple_shovel");
        GameRegistry.registerItem(PineappleItems.pineapple_hoe, "pineapple_hoe");
        
        GameRegistry.registerItem(PineappleItems.godly_pineapple_sword, "godly_pineapple_sword");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_pickaxe, "godly_pineapple_pickaxe");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_axe, "godly_pineapple_axe");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_shovel, "godly_pineapple_shovel");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_hoe, "godly_pineapple_hoe");
        // Calls Pineapple Armor!
        GameRegistry.registerItem(PineappleItems.pineapple_helmet, "pineapple_helmet");
        GameRegistry.registerItem(PineappleItems.pineapple_chestplate, "pineapple_chestplate");
        GameRegistry.registerItem(PineappleItems.pineapple_leggings, "pineapple_leggings");
        GameRegistry.registerItem(PineappleItems.pineapple_boots, "pineapple_boots");
        
        GameRegistry.registerItem(PineappleItems.godly_pineapple_helmet, "godly_pineapple_helmet");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_chestplate, "godly_pineapple_chestplate");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_leggings, "godly_pineapple_leggings");
        GameRegistry.registerItem(PineappleItems.godly_pineapple_boots, "godly_pineapple_boots");
        
        GameRegistry.registerItem(PineappleItems.pineapple_rod, "pineapple_rod");
        GameRegistry.registerItem(PineappleItems.pineapple_essence, "pineapple_essence");
        GameRegistry.registerItem(PineappleItems.pineapple_arc, "pineapple_arc");
        GameRegistry.registerItem(PineappleItems.pineapple_crystal_empty, "pineapple_crystal_empty");
        GameRegistry.registerItem(PineappleItems.pineapple_crystal_full, "pineapple_crystal_full");
        GameRegistry.registerItem(PineappleItems.basic_staff, "basic_staff");
        GameRegistry.registerItem(PineappleItems.damage_staff, "damage_staff");
        GameRegistry.registerItem(PineappleItems.golden_damage_staff, "golden_damage_staff");
        GameRegistry.registerItem(PineappleItems.godly_damage_staff, "godly_damage_staff");
        GameRegistry.registerItem(PineappleItems.healing_staff, "healing_staff");
        GameRegistry.registerItem(PineappleItems.golden_healing_staff, "golden_healing_staff");
        GameRegistry.registerItem(PineappleItems.godly_healing_staff, "godly_healing_staff");
	}
	
	/**
	 * Registers all world generators.
	 * @param v
	 */
	public static void registerWorldGenerators() {
		// Generates Pineapples!
        GameRegistry.registerWorldGenerator(WorldGenPineapple.pineappleDecorator, 0);
        GameRegistry.registerWorldGenerator(WorldGenShrine.pineappleTempleDecorator, 0);
	}
	
	/**
	 * Registers all crafting and smelting recipes.
	 * @param v
	 */
	public static void registerRecipies() {
		ItemStack stack_pineapple = new ItemStack(PineappleItems.pineapple);
    	ItemStack stack_pineapple_rod = new ItemStack(PineappleItems.pineapple_rod);
		ItemStack stack_basic_staff = new ItemStack(PineappleItems.basic_staff);
		ItemStack stack_diamond = new ItemStack(Items.diamond);
		ItemStack stack_emerald = new ItemStack(Items.emerald);
		ItemStack stack_pineapple_golden = new ItemStack(PineappleItems.pineapple_golden);
    	ItemStack stack_godly_pineapple = new ItemStack(PineappleItems.godly_pineapple);
		ItemStack stack_gold = new ItemStack(Items.gold_ingot);
		ItemStack stack_pineapple_grilled = new ItemStack(PineappleItems.pineapple_grilled);
		ItemStack stack_stick = new ItemStack(Items.stick);
		ItemStack stack_egg = new ItemStack(Items.egg);
		ItemStack stack_wheat = new ItemStack(Items.wheat);
		ItemStack stack_sugar = new ItemStack(Items.sugar);
		ItemStack stack_pineapple_pie = new ItemStack(PineappleItems.pineapple_pie_cooked);
		ItemStack stack_pie_dough = new ItemStack(PineappleItems.pineapple_pie_dough);
		ItemStack stack_pineapple_arc = new ItemStack(PineappleItems.pineapple_arc);
		ItemStack stack_pineapple_essence = new ItemStack(PineappleItems.pineapple_essence);
		ItemStack stack_pineapple_crystal_empty = new ItemStack(PineappleItems.pineapple_crystal_empty);
		
	    GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_arc),   " X ", 
				  															  "X#X", 
				  															  " X ", 'X', stack_pineapple_essence, '#', stack_pineapple_golden);
	    GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_essence, 2),   "X", 'X', stack_pineapple_golden);
	    GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_crystal_empty),   "XZX", 
				  															            "Z#Z", 
				  															            "XZX", 'X', stack_pineapple_essence, '#', stack_pineapple, 'Z', stack_pineapple_golden);
	    
	    GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_healing_staff),   "@X@", 
	    																			  "X#X", 
	    																			  "@X@", 'X', stack_diamond, '@', stack_emerald, '#', stack_basic_staff);
	    GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_helmet), "XXX", 
	    																			  "X X", 'X', stack_pineapple_golden);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_chestplate),  "X X", 
																					  "XXX", 
																					  "XXX", 'X', stack_pineapple_golden);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_leggings),   "XXX", 
																					  "X X", 
																					  "X X", 'X', stack_pineapple_golden);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_boots),  "X X", 
																					  "X X", 'X', stack_pineapple_golden);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_helmet),  "XXX", 
																					  "X X", 'X', stack_godly_pineapple);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_chestplate),   "X X", 
																					  "XXX", 
																					  "XXX", 'X', stack_godly_pineapple);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_leggings),    "XXX", 
																				      "X X", 
																				      "X X", 'X', stack_godly_pineapple);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_boots),   "X X", 
																					  "X X", 'X', stack_godly_pineapple);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_rod), 		  "  X", 
																					  " X ", 
																					  "X  ", 'X', stack_pineapple_golden);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_golden),"XXX", 
																						"X#X", 
																						"XXX", 'X', stack_gold, '#', stack_pineapple);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.basic_staff), 		" #Z", 
																					" X#", 
																					"X  ", 'X', stack_pineapple_rod, '#', stack_pineapple_arc, 'Z', stack_pineapple_crystal_empty);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_sword), 	"X", 
																				"X", 
																				"#", 'X', stack_pineapple_golden, '#', stack_stick);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_pickaxe),  "XXX", 
																					    " # ", 
																					    " # ", 'X', stack_pineapple_golden, '#', stack_stick);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_shovel),   "X", 
																						"#", 
																						"#", 'X', stack_pineapple_golden, '#', stack_stick);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_axe), 		"XX", 
																						"X#",	
																						" #", 'X', stack_pineapple_golden, '#', stack_stick);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_hoe), 		"XX", 
																						" #", 
																						" #", 'X', stack_pineapple_golden, '#', stack_stick);		
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_sword), 	"X", 
																						"X", 
																						"#", 'X', stack_godly_pineapple, '#', stack_pineapple_rod);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_pickaxe), 	"XXX", 
																						" # ", 
																						" # ", 'X', stack_godly_pineapple, '#', stack_pineapple_rod);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_shovel), 	"X", 
																						"#", 
																						"#", 'X', stack_godly_pineapple, '#', stack_pineapple_rod);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_axe), 		"XX", 
																						"X#", 
																						" #", 'X', stack_godly_pineapple, '#', stack_pineapple_rod);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.godly_pineapple_hoe), 		"XX", 
																						" #", 
																						" #", 'X', stack_godly_pineapple, '#', stack_pineapple_rod);
			
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_pie_dough), "@X@", "###", 'X', stack_egg,'#', stack_wheat, '@', stack_sugar);
		GameRegistry.addRecipe(new ItemStack(PineappleItems.pineapple_pie_uncooked), "###", "XXX", '#', stack_pineapple, 'X', stack_pie_dough);
			
		PineappleItems.PINEAPPLET.customCraftingMaterial = PineappleItems.pineapple_golden;
		PineappleItems.PINEAPPLETG.customCraftingMaterial = PineappleItems.godly_pineapple;
			
		GameRegistry.addSmelting(PineappleItems.pineapple, stack_pineapple_grilled, 1.0F);
		GameRegistry.addSmelting(PineappleItems.pineapple_pie_uncooked, stack_pineapple_pie, 1.5F);
	    
		PineappleItems.PINEAPPLEA.customCraftingMaterial = PineappleItems.pineapple_golden;
		PineappleItems.PINEAPPLEAG.customCraftingMaterial = PineappleItems.godly_pineapple;
	}
}
