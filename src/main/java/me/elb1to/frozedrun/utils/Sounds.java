package me.elb1to.frozedrun.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    private Player player;

    public Sounds(Player p) {
        player = p;
    }

    public void playSound(Sound sound){
        player.playSound(player.getLocation(), sound, 8, 8);
    }
}
