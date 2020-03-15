package me.elb1to.frozedrun.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            players.add(online);
        }
        return players;
    }

}
