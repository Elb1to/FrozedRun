package me.elb1to.frozedrun.commands;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.utils.command.BaseCommand;
import me.elb1to.frozedrun.utils.command.Command;
import me.elb1to.frozedrun.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ScatterCommand extends BaseCommand {
    @Command(name = "scatter", permission = "frozedrun.scatter")
    public void onCommand(CommandArgs command) {

        Player player = command.getPlayer();
        String[] args = command.getArgs();
        Player target = Bukkit.getPlayer(args[0]);

        Random randomLocation = new Random();

        int x = randomLocation.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        int z = randomLocation.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        int y = Bukkit.getWorld("uhc_world").getHighestBlockYAt(x, z);

        Location location = new Location(Bukkit.getWorld("uhc_world"), x, y ,z);

        target.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
    }
}
