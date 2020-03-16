package me.elb1to.frozedrun.commands;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.command.BaseCommand;
import me.elb1to.frozedrun.utils.command.Command;
import me.elb1to.frozedrun.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ScatterCommand extends BaseCommand {

    @Command(name = "scatter", permission = "frozedrun.scatter")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(Color.translate("&cCorrect usage: /scatter <playerName>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Color.translate("&cThat player is currently offline."));
            return;
        }

        Random randomLocation = new Random();

        int x = randomLocation.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        int z = randomLocation.nextInt(FrozedUHCRun.getInstance().getConfiguration("config").getInt("BORDER.SIZE") - 1);
        int y = Bukkit.getWorld("world").getHighestBlockYAt(x, z);

        World world = target.getWorld();
        Location randomLoc = new Location(world, x, y, z);

        target.teleport(randomLoc);
        target.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
    }
}
