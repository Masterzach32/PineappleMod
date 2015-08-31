package net.masterzach32.pineapple.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.masterzach32.pineapple.core.IUpdatableMod;
import net.masterzach32.pineapple.core.helper.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * Update Checker version 1.0
 * 
 * @author Masterzach32
 */
public class UpdateChecker {
 
    protected static IUpdatableMod updateMod;
    
    public UpdateChecker(IUpdatableMod mod) {
		updateMod = mod;
	}
    
    private static boolean isUpdateAvailable() throws IOException, MalformedURLException {
    	BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL(updateMod.getUpdateURL()).openStream()));
    	String curVersion = versionFile.readLine();

    	versionFile.close(); // YOU DONT NEED THE READER ANYMORE

    	if (!curVersion.contains(updateMod.getModVersion())) {
    		LogHelper.logInfo("Update is Available");
    		return true;
    	}
    	LogHelper.logInfo("No update is available");
    	return false;
    }
    
    @SubscribeEvent
    public void playerLoggedIn(PlayerLoggedInEvent event){
    	EntityPlayer player = event.player;
    	if(!player.worldObj.isRemote){
    		try {
    			if(isUpdateAvailable()){
    				IChatComponent chatComponentUpdate = new ChatComponentText("[Pineapple Mod] An update is available for this mod");
    				chatComponentUpdate.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, updateMod.getModURL()));
    				chatComponentUpdate.getChatStyle().setUnderlined(true);
    				player.addChatMessage(chatComponentUpdate);
    			}
    		} catch (MalformedURLException e) {
    			LogHelper.logError("MalformedURL Exception while checking for updates. Stacktrace:");
    			e.printStackTrace();
    		} catch (IOException e) {
    			LogHelper.logError("IO Exception while checking for updates. Stacktrace:");
    			e.printStackTrace();
    		}
    	}
    }
}