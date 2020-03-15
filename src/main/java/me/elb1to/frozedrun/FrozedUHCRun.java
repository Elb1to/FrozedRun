package me.elb1to.frozedrun;

import lombok.Getter;
import lombok.Setter;
import me.elb1to.frozedrun.commands.FrozedRunCommand;
import me.elb1to.frozedrun.commands.ScatterCommand;
import me.elb1to.frozedrun.listeners.ItemDropListeners;
import me.elb1to.frozedrun.listeners.WorldListener;
import me.elb1to.frozedrun.managers.MatchManager;
import me.elb1to.frozedrun.managers.PlayerDataManager;
import me.elb1to.frozedrun.managers.PlayerManager;
import me.elb1to.frozedrun.managers.RecipesManager;
import me.elb1to.frozedrun.player.PlayerData;
import me.elb1to.frozedrun.scoreboard.ScoreboardLayout;
import me.elb1to.frozedrun.utils.board.BoardManager;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.command.CommandFramework;
import me.elb1to.frozedrun.utils.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class FrozedUHCRun extends JavaPlugin {

    @Getter
    public static FrozedUHCRun instance;
    private CommandFramework framework;
    private List<ConfigFile> files = new ArrayList<>();
    private BoardManager boardManager;
    private HashMap<Player, Location> scatterLocation;
    private MatchManager matchManager;

    @Override
    public void onEnable() {
        instance = this;
        framework = new CommandFramework(this);

        registerConfigurations();

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
        Bukkit.getPluginManager().registerEvents(new WorldListener(), this);

        // From the managers package
        new RecipesManager(this);
        new PlayerDataManager();
        new PlayerManager();
        new MatchManager();

        // From the commands package
        new FrozedRunCommand();
        new ScatterCommand();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "Broadcast");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        checkLicense();

        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&7&m--------------------------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3This server is using &bFrozedRun"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3Author&7: &bElb1to"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&3Version&7: &b" + getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&7&m--------------------------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&bChecking your spigot version..."));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&aSuccess! &bYour Server NMS version: " + getNmsVersion()));
        Bukkit.getConsoleSender().sendMessage(" ");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been disabled!"));
    }

    public ConfigFile getConfiguration(String name) {
        return files.stream().filter(config -> config.getName().equals(name)).findFirst().orElse(null);
    }

    public void registerConfigurations() {
        files.addAll(Arrays.asList(
                new ConfigFile("config"),
                new ConfigFile("scoreboard")
        ));
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

    public HashMap<Player, Location> getScatterLocation() {
        return scatterLocation;
    }

    public MatchManager getMatchManager() {
        return matchManager;
    }

}
