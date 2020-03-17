package me.elb1to.frozedrun.listeners;

import me.elb1to.frozedrun.FrozedUHCRun;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.generator.BlockPopulator;

public class WorldListener implements Listener {

    public FrozedUHCRun plugin;

    public WorldListener(FrozedUHCRun instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {

        for(BlockPopulator pop : event.getWorld().getPopulators()){

            if (pop instanceof OresListener) {
                return;
            } else if (pop instanceof NetherListener) {
                return;
            } else if (pop instanceof LobbyListener) {
                return;
            }

            if (event.getWorld().getEnvironment() == World.Environment.NORMAL) {
                event.getWorld().getPopulators().add(new OresListener(plugin));
                event.getWorld().getPopulators().add(new NetherListener(plugin));
                event.getWorld().getPopulators().add(new LobbyListener(plugin));
            }
        }
    }

    @EventHandler
    public void onWorldLoaded(WorldLoadEvent event) {

        World w = event.getWorld();
        w.setPVP(false);
        w.setGameRuleValue("naturalRegeration", "false");
        w.setGameRuleValue("doDayLightCycle", "false");
        w.setThundering(false);
        w.setTime(6000);

        //new UHCBordures().setBorder(1000);
    }

    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
