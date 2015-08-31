package net.masterzach32.pineapple.core.handler;

import net.masterzach32.pineapple.item.PineappleItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class LootHandler {
	
	public static void addLoot() {
		addDungeon();
		addBlacksmith();
		addStronghold();
	}

	public static void addDungeon() {
		ChestGenHooks.getInfo("dungeonChest").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple), 1, 1, 15));
		ChestGenHooks.getInfo("dungeonChest").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_rod), 1, 1, 15));
	}

	public static void addBlacksmith() {
		ChestGenHooks.getInfo("villageBlacksmith").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_golden), 1, 1, 15));
		ChestGenHooks.getInfo("villageBlacksmith").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_sword), 1, 1, 15));
		ChestGenHooks.getInfo("villageBlacksmith").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_staff), 1, 1, 15));
		ChestGenHooks.getInfo("villageBlacksmith").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_rod), 1, 1, 15));
	}

	public static void addStronghold() {
		ChestGenHooks.getInfo("strongholdCorridor").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple), 1, 1, 15));
		ChestGenHooks.getInfo("strongholdCorridor").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_golden), 1, 1, 15));
		ChestGenHooks.getInfo("strongholdCorridor").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.godly_pineapple), 1, 1, 15));
		ChestGenHooks.getInfo("strongholdCorridor").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.pineapple_staff), 1, 1, 15));
		ChestGenHooks.getInfo("strongholdCorridor").addItem(new WeightedRandomChestContent(new ItemStack(PineappleItems.godly_pineapple_staff), 1, 1, 15));
	}
}