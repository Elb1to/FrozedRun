package me.elb1to.frozedrun.managers;

import me.elb1to.frozedrun.FrozedUHCRun;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class ScatteringManager {

    public static void tpRandom(Player player) {
        Random random = new Random();

        int x = random.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        int y = 150;
        int z = random.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        World world = player.getWorld();

        Location randomLoc = new Location(world, x, y, z);

        player.teleport(randomLoc);
    }

}
