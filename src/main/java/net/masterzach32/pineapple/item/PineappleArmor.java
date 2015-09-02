package net.masterzach32.pineapple.item;

import net.masterzach32.pineapple.Pineapple;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class PineappleArmor extends ItemArmor {

	public PineappleArmor(ArmorMaterial material, int i, int type) {
		super(material, i, type);
	}
	
	/**
	 * Registers the armor textures that the player sees in game.
	 * @param stack
	 * @param entity
	 * @param slot
	 * @param type
	 */
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {  	
    	if(stack.getItem() == PineappleItems.pineapple_helmet || stack.getItem() == PineappleItems.pineapple_chestplate || stack.getItem() == PineappleItems.pineapple_boots) {
    		return Pineapple.MODID + ":textures/models/armor/pineapple_layer_1.png";   		
    	}   	
    	if(stack.getItem() == PineappleItems.pineapple_leggings) {   		
    		return Pineapple.MODID + ":textures/models/armor/pineapple_layer_2.png";   	
    	}     	
    	if(stack.getItem() == PineappleItems.godly_pineapple_helmet || stack.getItem() == PineappleItems.godly_pineapple_chestplate || stack.getItem() == PineappleItems.godly_pineapple_boots) {
    		return Pineapple.MODID + ":textures/models/armor/godpineapple_layer_1.png";   		
    	}    	
    	if(stack.getItem() == PineappleItems.godly_pineapple_leggings) {    		
    		return Pineapple.MODID + ":textures/models/armor/godpineapple_layer_2.png";    	
    	} else { 
        	return null;
    	}
    }	
}