package me.elb1to.frozedrun;

import lombok.Getter;
import lombok.Setter;
import me.elb1to.frozedrun.commands.FrozedRunCommand;
import me.elb1to.frozedrun.commands.ScatterCommand;
import me.elb1to.frozedrun.listeners.*;
import me.elb1to.frozedrun.managers.*;
import me.elb1to.frozedrun.utils.board.BoardManager;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.command.CommandFramework;
import me.elb1to.frozedrun.utils.config.ConfigFile;
import me.elb1to.frozedrun.listeners.MatchJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

@Getter
@Setter
public class FrozedUHCRun extends JavaPlugin {

    @Getter
    public static FrozedUHCRun instance;
    private CommandFramework framework;
    private List<ConfigFile> files = new ArrayList<>();
    public ArrayList<UUID> playersInGame = new ArrayList<>();
    private BoardManager boardManager;

    @Override
    public void onEnable() {
        instance = this;
        framework = new CommandFramework(this);

        registerConfigurations();

        //setBoardManager(new BoardManager(this, new ScoreboardLayout()));

        if (!this.getDescription().getAuthors().contains("Elb1to") ||
                !this.getDescription().getName().equals("FrozedRun") ||
                !this.getDescription().getDescription().equals("FrozedRun by Elb1to")) {
            System.exit(0);
            Bukkit.shutdown();
        }
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been enabled!"));

        // From the listeners package
        Bukkit.getPluginManager().registerEvents(new ItemDropListener(), this);
        Bukkit.getPluginManager().registerEvents(new MatchJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new LeafDecayListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PvPListener(), this);
        Bukkit.getPluginManager().registerEvents(new MatchListener(),this);

        // From the managers package
        new ScatteringManager();
        new RecipesManager(this);
        new MatchManager();
        new MatchEndManager();
        new EventsManager();

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
        File file = new File("world");
        deleteWorld(file);
    }

    private boolean deleteWorld(File path) {
        if (path.exists()) {
            File files[] = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
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

}
