package de.chaos.mc.lobbysystem.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerDamageEvent implements Listener {
    @EventHandler
    public void onPlayerDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}
