package me.elb1to.frozedrun.managers;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class MatchManager {

    static int timer = 0;
    static int task;

    public static void start() {
        MatchState.setState(MatchState.INGAME);

        for(UUID uuid : FrozedUHCRun.getInstance().playersInGame){
            Player player = Bukkit.getPlayer(uuid);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1));

            ScatteringManager.tpRandom(player);
        }

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FrozedUHCRun.getInstance(), new Runnable() {

            @Override
            public void run() {
                timer--;

                if (timer == 30) {
                    MatchState.setState(MatchState.INGAME);
                }

                if (timer == 600) {
                    Bukkit.getWorld("world").setPVP(true);
                    MatchState.setState(MatchState.INPVP);
                }

                if (timer <= 630) {
                    if (timer > 0) {

                    }
                }

                if (timer >= 900) {
                    for (UUID uuid : FrozedUHCRun.getInstance().playersInGame) {
                        Player player = Bukkit.getPlayer(uuid);
                        player.teleport(new Location(Bukkit.getWorld("world"), 0, 0, 0));
                        Title.sendTitle(player, "Frozed UHCRun", "The borders are closing!", 20);
                    }
                }
            }
        },20,20);
    }
}
