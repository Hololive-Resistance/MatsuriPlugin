/*
 * Command for testing querying the CO database.
 */

package com.hololiveresistance.matsuriPlugin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.coreprotect.CoreProtectAPI;
import net.coreprotect.CoreProtectAPI.ParseResult;

public class CommandQuery implements CommandExecutor {
	public CoreProtectAPI api;
	
	// On command being run...
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int time = 24*60*60*5;
		
		List<String[]> lookup = api.performLookup(time, Arrays.asList("melon"), null, null, null, Arrays.asList(2), 0, null);
		// Print all 
		for (int i=0; i<lookup.size(); i++) {
			ParseResult row = api.parseResult(lookup.get(i));
			
			String player = " Player: "+row.getPlayer();
			String action = " Type: "+row.getActionString();
			String block = " Block: "+row.getBlockData().getAsString();
			String xx = " X: "+String.valueOf(row.getX());
			String yy = " Y: "+String.valueOf(row.getY());
			String zz = " Z: "+String.valueOf(row.getZ());
			
			
			String s = "Log detected..." + player +block+ action + xx + yy + zz;
			Bukkit.getLogger().info(s);
			
		}
		return true;
	}
}
