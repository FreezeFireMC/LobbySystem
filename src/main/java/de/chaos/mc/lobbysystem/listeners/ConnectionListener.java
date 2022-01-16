package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.PlayerLobbyLanguage;
import de.chaos.mc.lobbysystem.utils.playerlibary.PlayerInterface;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class ConnectionListener implements Listener {
    private PlayerInterface playerInterface;
    private ScoreboardManager scoreboardManager;
    public ConnectionListener(PlayerInterface playerInterface, ScoreboardManager scoreboardManager) {
        this.playerInterface = playerInterface;
        this.scoreboardManager = scoreboardManager;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SichtbarkeitsInterface sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        LobbySystem.getLobbySystem().getStringsLoader().getPlayerLobbyLanguage(uuid);
        event.setJoinMessage(AbstractMessages.joinMessage(player));
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setItem(1, new ItemBuilder(Material.MUSIC_DISC_PIGSTEP).name("§6GameModes").itemStack());
        player.getInventory().setItem(5, new ItemBuilder(Material.BARREL).name("§6Cosmetics").itemStack());
        player.getInventory().setItem(7, new ItemBuilder(Material.PLAYER_HEAD, 1, 3).skullOwner(player.getName()).name("§6Profile").itemStack());
        if (LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn") != null) {
            player.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn"));
        }
        player.setAllowFlight(true);
        player.setFlying(false);

        LobbySystem.getLobbySystem().getUpdateInventorySortingInterface().checkIfFirstJoin(player.getUniqueId());
        LobbySystem.getLobbySystem().getFreezeFightInventorySortingInterface().checkIfFirstJoin(player.getUniqueId());

        String name = "%LanguageHolo_name%";
        ArmorStand armorStand = LobbySystem.getLanguageHolo();
        armorStand.setCustomName(PlaceholderAPI.setPlaceholders(player, name));
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);

        PlayerLobbyLanguage lobbyLanguage = LobbySystem.getOnlinePlayers().get(player.getUniqueId());
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 0) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                player.showPlayer(LobbySystem.getLobbySystem(), all);
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.LIME_DYE, 1).name(lobbyLanguage.getPlayerVisiviltyItem()).itemStack());
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(Permissions.VIPPERMISSIONS.toString())) {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                } else {
                    player.showPlayer(LobbySystem.getLobbySystem(), all);
                }
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.PURPLE_DYE).name(lobbyLanguage.getPlayerVisiviltyItem()).itemStack());
        }
        if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 2) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                    player.hidePlayer(LobbySystem.getLobbySystem(), all);
                }
            }
            player.getInventory().setItem(3, new ItemBuilder(Material.RED_DYE).name(lobbyLanguage.getPlayerVisiviltyItem()).itemStack());
        }
        player.setPlayerWeather(WeatherType.CLEAR);
        playerInterface.checkIfFirstJoin(player.getUniqueId());
        scoreboardManager.getScorebaord(player);


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(AbstractMessages.leaveMessage(event.getPlayer()));
        scoreboardManager.playerScorebaordHashMap.remove(event.getPlayer().getUniqueId());
    }
}
