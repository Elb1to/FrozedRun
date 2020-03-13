package me.elb1to.frozedrun.managers;

import me.elb1to.frozedrun.FrozedUHCRun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;

public class RecipesManager {

    public RecipesManager(FrozedUHCRun plugin) {
        //Pickaxes
        createPickaxeRecipe(Material.WOOD, Material.STONE_PICKAXE, Enchantment.DIG_SPEED, 1);
        createPickaxeRecipe(Material.COBBLESTONE, Material.IRON_PICKAXE, Enchantment.DIG_SPEED, 1);
        createPickaxeRecipe(Material.IRON_INGOT, Material.DIAMOND_PICKAXE, Enchantment.DIG_SPEED, 1);
        createPickaxeRecipe(Material.GOLD_INGOT, Material.GOLD_PICKAXE, Enchantment.DIG_SPEED, 2);
        createPickaxeRecipe(Material.DIAMOND, Material.DIAMOND_PICKAXE, Enchantment.DIG_SPEED, 3);
        //Axes
        createAxeRecipe(Material.WOOD, Material.STONE_AXE, Enchantment.DIG_SPEED, 1);
        createAxeRecipe(Material.COBBLESTONE, Material.IRON_AXE, Enchantment.DIG_SPEED, 1);
        createAxeRecipe(Material.IRON_INGOT, Material.DIAMOND_AXE, Enchantment.DIG_SPEED, 1);
        createAxeRecipe(Material.GOLD_INGOT, Material.GOLD_AXE, Enchantment.DIG_SPEED, 2);
        createAxeRecipe(Material.DIAMOND, Material.DIAMOND_AXE, Enchantment.DIG_SPEED, 3);
    }

    public void createPickaxeRecipe(Material blockType, Material pickaxeType, Enchantment enchantment, int amplifier) {
        ItemStack pickaxe = new ItemStack(pickaxeType, 1);
        ItemMeta itemMeta = pickaxe.getItemMeta();

        itemMeta.addEnchant(Enchantment.DURABILITY, 3, false);
        itemMeta.addEnchant(enchantment, amplifier, false);

        pickaxe.setItemMeta(itemMeta);

        Iterator<Recipe> iterator = Bukkit.getServer().recipeIterator();
        Recipe recipe;
        while (iterator.hasNext()) {
            recipe = iterator.next();
            if (recipe != null && (recipe.getResult().getType() == pickaxeType || recipe.getResult().getType() == Material.WOOD_PICKAXE)) {
                iterator.remove();
            }
        }

        ShapedRecipe pickaxeRecipe = new ShapedRecipe(pickaxe);
        pickaxeRecipe.shape("XXX", " # ", " # ");
        pickaxeRecipe.setIngredient('#', Material.STICK);
        pickaxeRecipe.setIngredient('X', blockType);

        Bukkit.getServer().addRecipe(pickaxeRecipe);
    }

    public void createAxeRecipe(Material blockType, Material axeType, Enchantment enchantment, int amplifier) {
        ItemStack axe = new ItemStack(axeType, 1);
        ItemMeta itemMeta = axe.getItemMeta();

        itemMeta.addEnchant(Enchantment.DURABILITY, 3, false);
        itemMeta.addEnchant(enchantment, amplifier, false);

        axe.setItemMeta(itemMeta);

        Iterator<Recipe> iterator = Bukkit.getServer().recipeIterator();
        Recipe recipe;
        while (iterator.hasNext()) {
            recipe = iterator.next();
            if (recipe != null && (recipe.getResult().getType() == axeType || recipe.getResult().getType() == Material.WOOD_AXE)) {
                iterator.remove();
            }
        }

        ShapedRecipe leftAxeRecipe = new ShapedRecipe(axe);
        leftAxeRecipe.shape("XX ", "X# ", " # ");
        leftAxeRecipe.setIngredient('#', Material.STICK);
        leftAxeRecipe.setIngredient('X', blockType);

        ShapedRecipe rightAxeRecipe = new ShapedRecipe(axe);
        rightAxeRecipe.shape(" XX", " #X", " # ");
        rightAxeRecipe.setIngredient('#', Material.STICK);
        rightAxeRecipe.setIngredient('X', blockType);

        Bukkit.getServer().addRecipe(leftAxeRecipe);
        Bukkit.getServer().addRecipe(rightAxeRecipe);
    }

}