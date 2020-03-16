package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.enums.MatchState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    @EventHandler
    public void fallDamage(EntityDamageEvent event) {

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (!MatchState.isState(MatchState.INPVP)) {
                event.setCancelled(true);
            }
        }
    }

    public static void dropSkull(Player victim) {

        ItemStack skullVictim = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skullVictim.getItemMeta();
        skullMeta.setOwner(victim.getName());
        skullVictim.setItemMeta(skullMeta);
        victim.getWorld().dropItemNaturally(victim.getLocation(), skullVictim);
    }

    @EventHandler
    public void skullClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Material material = event.getMaterial();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (material != null) {
                if (material == Material.SKULL_ITEM) {
                    player.getInventory().remove(event.getItem());
                    player.updateInventory();
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
                }
            }
        }
    }

}
