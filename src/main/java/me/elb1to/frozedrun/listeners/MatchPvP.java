package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.WorldSounds;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class MatchPvP implements Listener {

    @EventHandler
    public void fakeDeath(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            fakeDeath(event.getEntity(), event.getDamage());
        }
    }

    private void fakeDeath(Entity entity, double damage) {
        Player player = (Player) entity;
        double health = player.getHealth();

        if (damage >= health) {
            player.setGameMode(GameMode.SPECTATOR);
            Bukkit.broadcastMessage("§7[§aUHCRun§7] " + player.getName() + " ha sido eliminado!");
            FrozedUHCRun.getInstance().playersInGame.remove(player.getUniqueId());
            new WorldSounds(player.getLocation()).playSound(Sound.AMBIENCE_THUNDER);
            PlayerListener.dropSkull(player);
        }
    }

    @EventHandler
    public void fakeDamageDeath(EntityDamageEvent e) {
        if (!MatchState.isState(MatchState.LOBBY) || !MatchState.isState(MatchState.LOBBY)) {
            if (e.getEntity() instanceof Player) {
                fakeDeath(e.getEntity(), e.getDamage());
            }
        } else {
            e.setCancelled(true);
        }
    }

}
