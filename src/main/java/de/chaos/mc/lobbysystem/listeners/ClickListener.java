package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsIntreface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
                LobbyInventorys.setLobbyInventory(event.getPlayer());
            }
            if (event.getItem().getType().equals(Material.ENDER_CHEST)) {
                LobbyInventorys.setMainCosmeticInventory(event.getPlayer());
            }
            if (event.getItem().getType().equals(Material.BLAZE_ROD)) {
                SichtbarkeitsIntreface sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
                if (sichtbarkeitsIntreface.getCurrentMode(uuid) != 2) {
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, Math.addExact(sichtbarkeitsIntreface.getCurrentMode(uuid), 1));
                    if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 1) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(String.valueOf(Permissions.SICHTBARKEITVIP))) {
                                player.showPlayer(LobbySystem.getLobbySystem(), all);
                            } else {
                                player.hidePlayer(LobbySystem.getLobbySystem(), all);
                            }
                        }
                        sichtbarkeitsIntreface.updateCurrentMode(uuid, 1);
                        player.sendMessage(DefaultMessages.normalMessage("Du siehtst nur noch VIP Spieler"));
                    }
                    if (sichtbarkeitsIntreface.getCurrentMode(uuid) == 2) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(String.valueOf(Permissions.SICHTBARKEITVIP))) {
                                player.hidePlayer(LobbySystem.getLobbySystem(), all);
                            }
                        }
                        sichtbarkeitsIntreface.updateCurrentMode(uuid, 2);
                        player.sendMessage(DefaultMessages.normalMessage("Du siehtst keine Spieler mehr"));
                    }
                } else {
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, Math.addExact(sichtbarkeitsIntreface.getCurrentMode(uuid), 0));
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(LobbySystem.getLobbySystem(), all);
                    }
                    player.sendMessage(DefaultMessages.normalMessage("Du siehtst nun alle Spieler!"));
                    sichtbarkeitsIntreface.updateCurrentMode(uuid, 0);
                }
            }
        }
    }
}
