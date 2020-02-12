/*
 * Main plugin class.
 */

package com.hololiveresistance.matsuriPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.hololiveresistance.matsuriPlugin.CoreDatabase;
import com.hololiveresistance.matsuriPlugin.CommandQuery;

import org.bukkit.plugin.Plugin;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public class MatsuriPlugin extends JavaPlugin {


    private CoreDatabase db;
	public CoreDatabase getRDatabase() {
        return this.db;
    }
    
    // Gets the CP plugin from server
 	public CoreProtect getCoreProtectPlugin() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
     
        // Check that CoreProtect is loaded
        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }
        // Check that the API is enabled
        CoreProtect coreProtect = (CoreProtect) plugin;
        if (coreProtect.isEnabled() == false) {
            return null;
        }
        return coreProtect;
 	}

    // Gets the CP API
 	public CoreProtectAPI getCoreProtectAPI() {
        CoreProtectAPI cpAPI = getCoreProtectPlugin().getAPI();
        
        // Check that a compatible version of the API is loaded
        if (cpAPI.APIVersion() < 6) {
            return null;
        }
        return cpAPI;
 	}
 	
 	//
    // OnEnable. Fired when plugin is first enabled
    //
 	@Override
    public void onEnable() {
    	// Make our plugin data folder
    	if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    	
    	
    	// Get CoreProtectAPI and register command if it works
		CoreProtectAPI api = getCoreProtectAPI();
		if (api != null){ //Ensure we have access to the API
			api.testAPI(); //Will print out "[CoreProtect] API Test Successful." in the console.
			
			// Give this command class the CoreProtect API
			CommandQuery querylookup = new CommandQuery();
			querylookup.api = api;
			this.getCommand("querylookup").setExecutor(querylookup);
		}
    	
		// Get 
    }
 	
 	//
    // OnDisable. Fired when plugin is disabled
    //
 	@Override
    public void onDisable() {
    }
}
