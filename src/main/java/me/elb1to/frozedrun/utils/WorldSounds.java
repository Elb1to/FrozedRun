package me.elb1to.frozedrun.utils;

import org.bukkit.Location;
import org.bukkit.Sound;

public class WorldSounds {

    private Location location;

    public WorldSounds(Location l) {
        location = l;
    }

    public void playSound(Sound sound){
        location.getWorld().playSound(location, sound, 8, 8);
    }
}
