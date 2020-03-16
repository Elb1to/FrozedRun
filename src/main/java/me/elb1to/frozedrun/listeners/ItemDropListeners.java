package me.elb1to.frozedrun.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ItemDropListeners implements Listener {

    Random random = new Random();

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        switch (event.getBlock().getType()) {
            case GRAVEL:
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.ARROW, 2));
                break;
            case SAND:
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GLASS, 1));
                break;
            case IRON_ORE:
                if (hasRequiredTool(player) && player.getGameMode() != GameMode.CREATIVE) {
                    event.getBlock().getDrops().clear();
                    event.getBlock().setType(Material.AIR);
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                    event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class).setExperience(3);
                }
                break;
            case GOLD_ORE:
                if (hasRequiredTool(player) && player.getGameMode() != GameMode.CREATIVE) {
                    event.getBlock().getDrops().clear();
                    event.getBlock().setType(Material.AIR);
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                    event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class).setExperience(3);
                }
                break;
            case LAPIS_ORE:
                if (hasRequiredTool(player) && player.getGameMode() != GameMode.CREATIVE) {
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.EXP_BOTTLE, 1));
                }
                break;
            case COAL_ORE:
                if (hasRequiredTool(player) && player.getGameMode() != GameMode.CREATIVE) {
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.TORCH, 2));
                    event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class).setExperience(3);
                }
                break;
            case EMERALD_ORE:
                if (hasRequiredTool(player) && player.getGameMode() != GameMode.CREATIVE) {
                    event.getBlock().setType(Material.AIR);
                    event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.EXP_BOTTLE, 3));
                    event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class).setExperience(4);
                }
                break;
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        switch (event.getEntityType()) {
            case COW:
            case MUSHROOM_COW:
            case HORSE:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.LEATHER, getRandomUA()));
                event.getDrops().add(new ItemStack(Material.COOKED_BEEF, getRandomFA()));
                break;
            case SHEEP:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.STRING, getRandomUA()));
                event.getDrops().add(new ItemStack(Material.COOKED_BEEF, getRandomFA()));
                break;
            case PIG:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.GRILLED_PORK, getRandomFA()));
                break;
            case CHICKEN:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, getRandomFA()));
                event.getDrops().add(new ItemStack(Material.ARROW, getRandomUA()));
                break;
        }
    }

    public boolean hasRequiredTool(Player player) {
        if ((player.getItemInHand().getType() != Material.DIAMOND_PICKAXE) && (player.getItemInHand().getType() != Material.GOLD_PICKAXE) &&
                (player.getItemInHand().getType() != Material.IRON_PICKAXE) && (player.getItemInHand().getType() != Material.STONE_PICKAXE)) {
            return false;
        } else
            return true;
    }

    public int getRandomFA() {
        int randomFA = random.nextInt(3);
        if (randomFA == 0) {
            randomFA++;
        }
        return randomFA;
    }

    public int getRandomUA() {
        int randomUA = random.nextInt(4);
        if (randomUA == 0) {
            randomUA++;
        }
        return randomUA;
    }

    public int getRandomSA() {
        int randomSA = random.nextInt(4);
        if (randomSA == 0) {
            randomSA++;
        }
        return randomSA;
    }

}
