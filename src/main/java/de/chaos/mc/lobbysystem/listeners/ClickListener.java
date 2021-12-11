package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class ClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getItem().getType().equals(Material.COMPASS)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 ,10);
                LobbyInventorys.setLobbyInventory(event.getPlayer());
            }
            if (event.getItem().getType().equals(Material.ENDER_CHEST)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 ,10);
                LobbyInventorys.setMainCosmeticInventory(event.getPlayer());
            }
            if (event.getItem().getType().equals(Material.INK_SACK)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 10);
                SichtbarkeitsInterface sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
                if (sichtbarkeitsIntreface.getCurrentMode(uuid) != 2) {
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, Math.addExact(sichtbarkeitsIntreface.getCurrentMode(uuid), 1));
                    if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 1) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(Permissions.VIPPERMISSIONS.toString())) {
                                player.showPlayer(LobbySystem.getLobbySystem(), all);
                            } else {
                                player.hidePlayer(LobbySystem.getLobbySystem(), all);
                            }
                        }
                        sichtbarkeitsIntreface.updateCurrentMode(uuid, 1);
                        player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.PURPLE).name("§6Spieler Sichtbarkeit").itemStack());
                        player.sendMessage(DefaultMessages.normalMessage("Du siehtst nur noch VIP Spieler"));
                    }
                    if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 2) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            player.hidePlayer(LobbySystem.getLobbySystem(), all);
                        }
                        sichtbarkeitsIntreface.updateCurrentMode(uuid, 2);
                        player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.RED).name("§6Spieler Sichtbarkeit").itemStack());
                        player.sendMessage(DefaultMessages.normalMessage("Du siehtst keine Spieler mehr"));
                    }
                } else {
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, Math.addExact(sichtbarkeitsIntreface.getCurrentMode(uuid), 0));
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(LobbySystem.getLobbySystem(), all);
                    }
                    player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK, 1, 0, DyeColor.LIME).name("§6Spieler Sichtbarkeit").itemStack());
                    player.sendMessage(DefaultMessages.normalMessage("Du siehtst nun alle Spieler!"));
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, 0);
                }
            }
            if (event.getItem().getType().equals(Material.SKULL_ITEM)) {
                player.sendMessage(DefaultMessages.normalMessage("Bald..."));
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_HIT,1 ,1);
            }
        }
    }
}
