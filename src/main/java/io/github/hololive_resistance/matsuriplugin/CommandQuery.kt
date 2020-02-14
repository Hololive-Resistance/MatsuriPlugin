/*
 * Command for testing querying the CO database.
 */
package io.github.hololive_resistance.matsuriplugin

import net.coreprotect.CoreProtectAPI
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandQuery : CommandExecutor {
    var api = CoreProtectAPI()
    // On command being run...
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val time = 24 * 60 * 60 * 5
        val lookup = api.performLookup(time, listOf("melon"), null, null, null, listOf(2), 0, null)
        // Print all
        for (i in lookup.indices) {
            val row = api.parseResult(lookup[i])
            val player = " Player: " + row.player
            val action = " Type: " + row.actionString
            val block = " Block: " + row.blockData.asString
            val xx = " X: " + row.x
            val yy = " Y: " + row.y
            val zz = " Z: " + row.z
            val s = "Log detected...$player$block$action$xx$yy$zz"
            Bukkit.getLogger().info(s)
        }
        return true
    }
}