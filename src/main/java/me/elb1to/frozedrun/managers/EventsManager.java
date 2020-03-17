package me.elb1to.frozedrun.managers;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.listeners.MatchJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public static void registerEvents(FrozedUHCRun plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MatchJoinListener(), plugin);
    }
}
