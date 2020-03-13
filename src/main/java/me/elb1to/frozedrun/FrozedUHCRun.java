package me.elb1to.frozedrun;

import me.elb1to.frozedrun.utils.chat.Color;
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
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been enabled!"));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Color.translate("&8[&b&lFrozedRun&8] &3has been disabled!"));
    }

    public static FrozedUHCRun get() {
        return FrozedUHCRun;
    }
}
