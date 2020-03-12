package me.elb1to.frozedrun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FrozedUHCRun extends JavaPlugin {

    private static FrozedUHCRun FrozedUHCRun;

    @Override
    public void onEnable() {
        FrozedUHCRun = this;

        if (!this.getDescription().getAuthors().contains("Elb1to") ||
                !this.getDescription().getName().equals("FrozedRun") ||
                !this.getDescription().getDescription().equals("FrozedRun by Elb1to")) {
            System.exit(0);
            Bukkit.shutdown();
        }

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage();
    }

    public static FrozedUHCRun get() {
        return FrozedUHCRun;
    }
}
