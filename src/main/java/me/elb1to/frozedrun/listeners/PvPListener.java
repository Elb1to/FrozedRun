package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.WorldSounds;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class PvPListener implements Listener {

    public static HashMap<Player, Integer> kills = new HashMap<>();

    @EventHandler
    public void fakeDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        fakeDeath(player);
    }

    private void fakeDeath(Player player) {
        respawnInstant(player);
        player.setGameMode(GameMode.SPECTATOR);
        Bukkit.broadcastMessage(FrozedUHCRun.getInstance().getConfiguration("config").getString("PREFIX") + player.getName() + " ha sido eliminado!");
        FrozedUHCRun.getInstance().playersInGame.remove(player.getUniqueId());
        new WorldSounds(player.getLocation()).playSound(Sound.AMBIENCE_THUNDER);
        PlayerListener.dropSkull(player);
    }

    private void respawnInstant(final Player player) {
        Bukkit.getScheduler().runTaskLater(FrozedUHCRun.getInstance(), new Runnable() {
            @Override
            public void run() {
                PacketPlayInClientCommand packet = new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN);
                ((CraftPlayer) player).getHandle().playerConnection.a(packet);
            }
        }, 5L);
    }


    @EventHandler
    public void fakeDamageDeath(EntityDamageEvent event) {
        if (MatchState.isState(MatchState.LOBBY)) {
            event.setCancelled(true);
        }
    }

}
