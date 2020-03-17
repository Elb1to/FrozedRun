package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.managers.MatchManager;
import me.elb1to.frozedrun.utils.Sounds;
import me.elb1to.frozedrun.utils.Title;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class MatchJoinListener implements Listener {

    static int task;
    static int timer = 30;

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new Sounds(player).playSound(Sound.CLICK);

        if (!FrozedUHCRun.getInstance().playersInGame.contains(player.getUniqueId())) {
            FrozedUHCRun.getInstance().playersInGame.add(player.getUniqueId());

            if (FrozedUHCRun.getInstance().playersInGame.size() == 1) {
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FrozedUHCRun.getInstance(), new Runnable(){

                    @Override
                    public void run() {
                        timer--;

                        if (timer == 30 || timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
                            for (UUID uuid : FrozedUHCRun.getInstance().playersInGame) {
                                Player player = Bukkit.getPlayer(uuid);
                                Title.sendTitle(player, "Frozed UHCRun", "Iniciando partida en " + timer + " segundos!", 20);
                                new Sounds(player).playSound(Sound.NOTE_PLING);
                            }
                        }

                        if (timer == 0) {
                            for (UUID uuid : FrozedUHCRun.getInstance().playersInGame) {
                                Player player = Bukkit.getPlayer(uuid);
                                Title.sendTitle(player, "Frozed UHCRun", "Iniciando partida!", 20);
                                new Sounds(player).playSound(Sound.ENDERDRAGON_GROWL);
                            }
                            Bukkit.getScheduler().cancelTask(task);
                            MatchManager.start();
                            deleteLobby();
                        }
                    }
                },20,20);
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("La partida ha comenzado, asi que tendras acceso a espectearla!");
        }
    }

    private void deleteLobby() {
        World world = Bukkit.getWorld(("world"));

        Location location1 = new Location(world, 0, 130, 0);
        Location location2 = new Location(world, 30, 190, 44);

        int minX = Math.min(location1.getBlockX(), location2.getBlockX());
        int minY = Math.min(location1.getBlockY(), location2.getBlockY());
        int minZ = Math.min(location1.getBlockZ(), location2.getBlockZ());

        int maxX = Math.max(location1.getBlockX(), location2.getBlockX());
        int maxY = Math.min(location1.getBlockY(), location2.getBlockY());
        int maxZ = Math.min(location1.getBlockZ(), location2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                    for (Entity entity : world.getEntities()) {
                        entity.remove();
                    }
                }
            }
        }

    }

    public void setLevel(int timer) {
        for(UUID uuid : FrozedUHCRun.getInstance().playersInGame) {
            Player player = Bukkit.getPlayer(uuid);
            player.setLevel(timer);
        }
    }

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FrozedUHCRun.getInstance().playersInGame.remove(player.getUniqueId());
    }
}
