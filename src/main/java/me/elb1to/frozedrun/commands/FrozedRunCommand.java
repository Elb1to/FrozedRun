package me.elb1to.frozedrun.commands;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.command.BaseCommand;
import me.elb1to.frozedrun.utils.command.Command;
import me.elb1to.frozedrun.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class FrozedRunCommand extends BaseCommand {
    @Command(name = "frozedrun", aliases = {"uhc", "uhcrun", "info", "elb1to", "frozedclub"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        if (player.isOp()) {
            player.sendMessage(Color.translate("&7&m----------------------------------"));
            player.sendMessage(Color.translate("&7This server is using &b&lFrozedRun"));
            player.sendMessage(Color.translate("&7Authors: &fElb1to"));
            player.sendMessage(Color.translate("&7Version: &bv" + FrozedUHCRun.getInstance().getDescription().getVersion()));
            player.sendMessage(Color.translate("&8"));
            player.sendMessage(Color.translate("&b&lFrozedRun Help &7- &fUseful Commands"));
            player.sendMessage(Color.translate("&7* &b/stats &8- &fCheck your Stats"));
            player.sendMessage(Color.translate("&7* &b/respawn &8- &fRespawns a player"));
            player.sendMessage(Color.translate("&7* &b/announce &8- &fBungee Announce for the match"));
            player.sendMessage(Color.translate("&7* &b/settings &8- &fChange your SG settings"));
            player.sendMessage(Color.translate("&7&m----------------------------------"));
        } else {
            player.sendMessage(Color.translate("&7&m----------------------------------"));
            player.sendMessage(Color.translate("&7This server is using &b&lFrozedRun"));
            player.sendMessage(Color.translate("&7Authors: &fElb1to"));
            player.sendMessage(Color.translate("&7Version: &bv" + FrozedUHCRun.getInstance().getDescription().getVersion()));
            player.sendMessage(Color.translate("&8"));
            player.sendMessage(Color.translate("&b&lFrozedRun Help &7- &fUseful Commands"));
            player.sendMessage(Color.translate("&7* &b/stats &8- &fCheck your Stats"));
            player.sendMessage(Color.translate("&7* &b/announce &8- &fAnnounce the match"));
            player.sendMessage(Color.translate("&7* &b/settings &8- &fChange your SG settings"));
            player.sendMessage(Color.translate("&7&m----------------------------------"));
        }
    }
}
