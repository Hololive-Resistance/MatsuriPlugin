package com.hololiveresistance.matsuriPlugin;

import java.util.logging.Level;
import com.hololiveresistance.matsuriPlugin.MatsuriPlugin;

public class Error {
    public static void execute(MatsuriPlugin plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(MatsuriPlugin plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
