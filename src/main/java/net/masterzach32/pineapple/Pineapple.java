package net.masterzach32.pineapple;

import net.masterzach32.pineapple.core.IUpdatableMod;
import net.masterzach32.pineapple.core.handler.LootHandler;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.masterzach32.pineapple.core.helper.RegisterHelper;
import net.masterzach32.pineapple.core.util.UpdateChecker;
import net.masterzach32.pineapple.proxy.*;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Pineapple.MODNAME, modid = Pineapple.MODID, version = Pineapple.VERSION)
public class Pineapple implements IUpdatableMod {

	/**
	 * The ModID for the Pineapple Mod.
	 */
	public static final String MODID = "Pineapple";
	public final static String MODNAME = "Pineapple Mod";

	public static final String UPDATEURL = "http://masterzach32.net/mod-version.txt";
	public static final String MODURL = "http://wp.me/P3lEJb-9x";
	/**
	 * Mod Version + Minecraft Version.
	 */
	public static final String VERSION = "1.4.1.250";
	public static final String SVERSION = "1.4.1 Beta 1";

	public static EnumEnchantmentType enumStaff = EnumHelper.addEnchantmentType("staff");

	public static DamageSource pineapple = new DamageSource("pineapple").setMagicDamage().setProjectile();

	/**
	 * The instance of The Pineapple Mod that Forge uses.
	 */
	@Instance("Pineapple")
	public static Pineapple instance;

	/**
	 * Says where the client and server 'proxy' code is loaded.
	 */
	@SidedProxy(clientSide="net.masterzach32.pineapple.proxy.ClientProxy", serverSide="net.masterzach32.pineapple.proxy.CommonProxy")
	public static CommonProxy proxy;

	/**
	 * First part of loading the pineapple mod
	 * @param event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.logInfo("Pineapple Mod " + VERSION + " initializing.");
		//KeyBindings.init();
		FMLCommonHandler.instance().bus().register(new UpdateChecker(this));
		//FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		LogHelper.logWarning("This is a beta version of the mod, expect bugs.");
		LogHelper.logWarning("There may still be missing textures, so expect error messages.");
	}

	/**
	 * Loads all the blocks, items, entities and world generators.
	 * @param event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		RegisterHelper.registerBlocks();
		LogHelper.logInfo("Blocks loaded for Pineapple Mod " + VERSION + ".");
		RegisterHelper.registerItems();
		LogHelper.logInfo("Items loaded for Pineapple Mod " + VERSION + ".");
		RegisterHelper.registerWorldGenerators();
		LogHelper.logInfo("World Generators loaded for Pineapple Mod " + VERSION + ".");
		RegisterHelper.registerRecipies();
		LogHelper.logInfo("Crafting Recipies loaded for Pineapple Mod " + VERSION + ".");
		LootHandler.addLoot();
	}

	/**
	 * Last part of loading the pineapple mod, deals with communicating with other mods.
	 * @param event
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.logInfo("Pineapple Mod " + VERSION + " loaded sucessfully.");
	}

	public String getModID() {
		return Pineapple.MODID;
	}

	public String getModName() {
		return Pineapple.MODNAME;
	}

	public String getModVersion() {
		return Pineapple.SVERSION;
	}

	public String getUpdateURL() {
		return Pineapple.UPDATEURL;
	}

	public String getModURL() {
		return Pineapple.MODURL;
	}

}