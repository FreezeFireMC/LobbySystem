package de.chaos.mc.lobbysystem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerWeather(WeatherType.CLEAR);
        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        event.setDamage(0);
        event.setCancelled(true);
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
