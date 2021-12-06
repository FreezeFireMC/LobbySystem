package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class ConnectionListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SichtbarkeitsInterface sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        event.setJoinMessage(DefaultMessages.joinMessage(player));
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setItem(1, new ItemBuilder(Material.COMPASS).name("§6Navigator").itemStack());
        player.getInventory().setItem(3, new ItemBuilder(Material.BLAZE_ROD).name("§6Spieler Sichtbarkeit").itemStack());
        player.getInventory().setItem(5, new ItemBuilder(Material.ENDER_CHEST).name("§6Cosmetics").itemStack());
        player.getInventory().setItem(7, new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner(player.getName()).name("§6Bald...").itemStack());
        if (LobbySystem.getLocationInterface().getLocation("Spawn") != null) {
            player.teleport(LobbySystem.getLocationInterface().getLocation("Spawn"));
        }
        player.setAllowFlight(true);
        player.setFlying(false);



        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 0) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(LobbySystem.getLobbySystem(), all);
            }
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                    player.showPlayer(LobbySystem.getLobbySystem(), all);
                } else {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                }
            }
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 2) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                }
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(DefaultMessages.leaveMessage(event.getPlayer()));
    }
}
