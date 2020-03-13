package me.elb1to.frozedrun;

import lombok.Getter;
import me.elb1to.frozedrun.commands.FrozedRunCommand;
import me.elb1to.frozedrun.listeners.ItemDropListeners;
import me.elb1to.frozedrun.scoreboard.ScoreboardLayout;
import me.elb1to.frozedrun.utils.board.BoardManager;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FrozedUHCRun extends JavaPlugin {

    @Getter
    public static FrozedUHCRun instance;
    @Getter
    private CommandFramework framework;
    private BoardManager boardManager;

    @Override
    public void onEnable() {
        instance = this;

        framework = new CommandFramework(this);

        setBoardManager(new BoardManager(this, new ScoreboardLayout()));

        if (!this.getDescription().getAuthors().contains("Elb1to") ||
                !this.getDescription().getName().equals("FrozedRun") ||
                !this.getDescription().getDescription().equals("FrozedRun by Elb1to")) {
            System.exit(0);
            Bukkit.shutdown();
        }
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been enabled!"));

        // From the listeners package
        Bukkit.getPluginManager().registerEvents(new ItemDropListeners(), this);

        // From the managers package

        // From the commands package
        new FrozedRunCommand();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "Broadcast");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        checkLicense();

        Bukkit.getConsoleSender().sendMessage(Color.translate("&7&m--------------------------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&6This server is using &eGoodestSG"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&6Authors&7: &eScalebound and Elb1to"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&6Version&7: &e" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&7&m--------------------------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eChecking your spigot version..."));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&aSuccess! &6Your Server NMS version: " + getNmsVersion()));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been disabled!"));
    }

    private String getNmsVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public void checkLicense() {

    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
        long interval = this.boardManager.getAdapter().getInterval();
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, this.boardManager, interval, interval);
        this.getServer().getPluginManager().registerEvents(this.boardManager, this);
    }

}
