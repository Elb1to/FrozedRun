package me.elb1to.frozedrun.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {

    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
