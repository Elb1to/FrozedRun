package me.elb1to.frozedrun.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    private Player player;

    public Sounds(Player player) {
        player = player;

    }

    public void playSound(Sound sound){
        player.playSound(player.getLocation(), sound, 8, 8);
    }
}
