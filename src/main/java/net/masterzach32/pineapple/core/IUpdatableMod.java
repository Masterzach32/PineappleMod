package net.masterzach32.pineapple.core;

/** 
 * Implements this interface in your main mod file if you want to check for updates for your mod.
 * 
 */
public interface IUpdatableMod{
     
	/**
	 * Gets the current version of the mod
	 * @return version
	 */
	String getModVersion();
	
	/**
	 * Gets the mod ID
	 * @return MODID
	 */
	String getModID();
	
	/**
	 * Gets the mod name
	 * @return name
	 */
	String getModName();
	
	/**
	 * Gets the URL with the updated version string
	 * @return updateurl
	 */
	String getUpdateURL();
	
	/**
	 * Gets the website where the player will download the new update
	 * @return downloadurl
	 */
	String getModURL();
	
}