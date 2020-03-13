package me.elb1to.frozedrun.listeners;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ItemDropListeners implements Listener {

    Random random = new Random();
    int randomFoodAmount = random.nextInt(3);
    int randomUtilAmount = random.nextInt(2);
    int specialItemAmount = random.nextInt(1);

    @EventHandler
    public void onCowDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Cow) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, randomFoodAmount));
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.LEATHER, randomUtilAmount));
        }
    }

    @EventHandler
    public void onHorseDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Horse) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, randomFoodAmount));
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.LEATHER, randomUtilAmount));
        }
    }

    @EventHandler
    public void onSheepDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Sheep) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, randomFoodAmount));
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.LEATHER, randomUtilAmount));
        }
    }

    @EventHandler
    public void onPigDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Pig) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GRILLED_PORK, randomFoodAmount));
        }
    }

    @EventHandler
    public void onChickenDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Chicken) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_CHICKEN, randomFoodAmount));
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ARROW, randomUtilAmount));
        }
    }

    @EventHandler
    public void onVillagerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Villager) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.DIAMOND, specialItemAmount));
        }
    }

    @EventHandler
    public void onMushroomCowDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof MushroomCow) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.MUSHROOM_SOUP, randomFoodAmount));
        }
    }

    @EventHandler
    public void onOcelotDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Ocelot) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COOKED_FISH, randomFoodAmount));
        }
    }

    @EventHandler
    public void onWitchDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Witch) {
            event.getDrops().clear();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.POTION, specialItemAmount, (short) 16421));
        }
    }

}
