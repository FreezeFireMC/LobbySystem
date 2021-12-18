package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() <= 1) {
            player.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn"));
        }
    }
}
