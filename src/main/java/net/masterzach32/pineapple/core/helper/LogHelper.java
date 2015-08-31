package net.masterzach32.pineapple.core.helper;

import cpw.mods.fml.common.FMLLog;

/**
 * Logging system, version 1.1
 * @author Masterzach32
 *
 */
public class LogHelper {

	public static String CCONSOLE = "[Pineapple Mod] "; // [ClientSide]";
	public static String SCONSOLE = "[Pineapple Mod] "; // [ServerSide]";
	
	/**
	 * Logs info to the console.
	 * @param log The message to the console
	 */
	public static void logInfo(String log) {
		FMLLog.info(CCONSOLE + log);
	}
	
	/**
	 * Logs a warning to the console.
	 * @param log The warning message to the console
	 */
	public static void logWarning(String log) {
		FMLLog.warning(CCONSOLE + log);
	}
	
	/**
	 * Logs an error to the console.
	 * @param log The error message to the console
	 */
	public static void logError(String log) {
		FMLLog.severe(CCONSOLE + log);
	}
	
	/**
	 * Logs a debug message to the console
	 * @param log
	 */
	public static void logDebug(String log) {
		FMLLog.fine(CCONSOLE + log);
	}
}
