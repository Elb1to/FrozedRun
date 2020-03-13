package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.utils.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ItemDropListeners implements Listener {

    Random random = new Random();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        event.getDrops().clear();
        switch (event.getEntityType()) {
            case COW:
            case MUSHROOM_COW:
            case HORSE:
            case SHEEP:
                event.getDrops().add(new ItemStack(Material.LEATHER, getRandomUA()));
                event.getDrops().add(new ItemStack(Material.COOKED_BEEF, getRandomFA()));
                break;
            case PIG:
                event.getDrops().add(new ItemStack(Material.GRILLED_PORK, getRandomFA()));
                break;
            case CHICKEN:
                event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, getRandomFA()));
                event.getDrops().add(new ItemStack(Material.ARROW, getRandomUA()));
                break;
            case WITCH:
                event.getDrops().add(new ItemStack(Material.POTION, getRandomSA(), (short) 16421));
        }
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
