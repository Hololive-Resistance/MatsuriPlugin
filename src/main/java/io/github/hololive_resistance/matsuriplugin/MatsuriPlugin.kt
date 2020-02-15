/*
 * Main plugin class.
 */
package io.github.hololive_resistance.matsuriplugin

import io.github.hololive_resistance.matsuriplugin.gacha.RNG
import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

object MatsuriPlugin : JavaPlugin() {
    val rDatabase: CoreDatabase? = null
    // Check that the API is enabled

    // Gets the CP plugin from server
    val coreProtectPlugin: CoreProtect?
        get() {
            val plugin = server.pluginManager.getPlugin("CoreProtect")
            // Check that CoreProtect is loaded
            if (plugin == null || plugin !is CoreProtect) {
                return null
            }
            // Check that the API is enabled
            if (!plugin.isEnabled) {
                return null
            }
            return plugin
        }

    // Gets the CP API
    val coreProtectAPI: CoreProtectAPI?
        get() {
            val cpAPI = coreProtectPlugin!!.api
            // Check that a compatible version of the API is loaded
            if (cpAPI.APIVersion() < 6) {
                return null
            }
            return cpAPI
        }

    //
    // OnEnable. Fired when plugin is first enabled
    //
    override fun onEnable() {
        // Make our plugin data folder
        if (!dataFolder.exists()) {
            dataFolder.mkdir()
        }
        // Get CoreProtectAPI and register command if it works
        val api = coreProtectAPI
        if (api != null) { // Ensure we have access to the API
            api.testAPI() // Will print out "[CoreProtect] API Test Successful." in the console.
            // Give this command class the CoreProtect API
            CommandQuery.api = api
            getCommand("querylookup")!!.setExecutor(CommandQuery)
        }
        Config.load()
    }

    //
    // OnDisable. Fired when plugin is disabled
    //
    override fun onDisable() {
        Config.unload()
    }
}