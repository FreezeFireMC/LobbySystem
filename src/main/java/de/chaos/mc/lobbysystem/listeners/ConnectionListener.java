package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.*;
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
        player.getInventory().setItem(5, new ItemBuilder(Material.ENDER_CHEST).name("§6Cosmetics").itemStack());
        player.getInventory().setItem(7, new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner(player.getName()).name("§6Bald...").itemStack());
        if (LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn") != null) {
            player.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn"));
        }
        player.setAllowFlight(true);
        player.setFlying(false);

        LobbySystem.getLobbySystem().getUpdateInventorySortingInterface().checkIfFirstJoin(player.getUniqueId());

        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 0) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(LobbySystem.getLobbySystem(), all);
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.LIME).name("§6Spieler Sichtbarkeit").itemStack());
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission(Permissions.VIPPERMISSIONS.toString())) {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                } else {
                    player.showPlayer(LobbySystem.getLobbySystem(), all);
                }
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.PURPLE).name("§6Spieler Sichtbarkeit").itemStack());
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 2) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                }
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.RED).name("§6Spieler Sichtbarkeit").itemStack());
        }
        player.setPlayerWeather(WeatherType.CLEAR);
        ScoreboardManager.getScorebaord(player);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(DefaultMessages.leaveMessage(event.getPlayer()));
        ScoreboardManager.playerScorebaordHashMap.remove(event.getPlayer().getUniqueId());
    }
}
