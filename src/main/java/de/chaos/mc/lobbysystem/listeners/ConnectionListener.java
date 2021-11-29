package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(DefaultMessages.joinMessage(player));
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).name("§6Navigator").itemStack());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(DefaultMessages.leaveMessage(event.getPlayer()));
    }
}
