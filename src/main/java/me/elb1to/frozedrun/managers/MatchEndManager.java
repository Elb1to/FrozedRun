package me.elb1to.frozedrun.managers;

import me.elb1to.frozedrun.FrozedUHCRun;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MatchEndManager {

    public static void checkWin() {

        int online = FrozedUHCRun.getInstance().playersInGame.size();

        if (online == 0) {
            stopGame(10);
        }

        if (online == 1) {
            for(UUID pl : FrozedUHCRun.getInstance().playersInGame){
                Player winner = Bukkit.getPlayer(pl);
                Bukkit.broadcastMessage("Le joueur : "+winner.getName()+" a gagné le jeu ! Bravo à lui !");
                stopGame(6);
            }
        }
    }

    public static void stopGame(int timeUntilStop) {
        Bukkit.getScheduler().runTaskLater(FrozedUHCRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer("Game End !");
                }
                Bukkit.shutdown();
            }
        },timeUntilStop * 20);
    }

}
