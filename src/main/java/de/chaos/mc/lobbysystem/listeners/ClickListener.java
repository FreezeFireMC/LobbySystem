package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.PlayerLobbyLanguage;
import de.chaos.mc.lobbysystem.utils.profile.ProfileInventorys;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class ClickListener implements Listener {
    private ProfileInventorys profileInventorys;
    public ClickListener(ProfileInventorys profileInventorys) {
        this.profileInventorys = profileInventorys;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() == null ) return;
        if (event.getAction() == null) return;
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerLobbyLanguage playerLobbyLanguage = LobbySystem.getOnlinePlayers().get(uuid);
        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.FARMLAND) event.setCancelled(true);
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.NOTE_BLOCK) {
                event.setCancelled(true);
            }
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.BEACON ||
                    event.getClickedBlock().getType() == Material.CHEST ||
                    event.getClickedBlock().getType() == Material.ENDER_CHEST ||
                    event.getClickedBlock().getType() == Material.ANVIL ||
                    event.getClickedBlock().getType() == Material.FLOWER_POT ||
                    event.getClickedBlock().getType() == Material.HOPPER ||
                    event.getClickedBlock().getType() == Material.HOPPER_MINECART ||
                    event.getClickedBlock().getType() == Material.ACACIA_DOOR ||
                    event.getClickedBlock().getType() == Material.DARK_OAK_DOOR ||
                    event.getClickedBlock().getType() == Material.DARK_OAK_FENCE_GATE ||
                    event.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR ||
                    event.getClickedBlock().getType() == Material.BOOKSHELF ||
                    event.getClickedBlock().getType() == Material.NOTE_BLOCK ||
                    event.getClickedBlock().getType() == Material.CAULDRON ||
                    event.getClickedBlock().getType() == Material.TRAPPED_CHEST ||
                    event.getClickedBlock().getType() == Material.FURNACE ||
                    event.getClickedBlock().getType() == Material.DISPENSER) {
                event.setCancelled(true);
            }
        }

        if (event.getItem().getType().equals(Material.PLAYER_HEAD)) {
            profileInventorys.getMainInventory(player);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1 ,1);
        }
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getItem().getType().equals(Material.MUSIC_DISC_PIGSTEP)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                LobbyInventorys.setLobbyInventory(event.getPlayer());
            }
            if (event.getItem().getType().equals(Material.BARREL)) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                LobbyInventorys.setMainCosmeticInventory(event.getPlayer());
            }
            SichtbarkeitsInterface sichtbarkeitsInterface = LobbySystem.sichtbarkeitsIntreface;
            if (event.getItem().getType().equals(Material.PURPLE_DYE)) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(LobbySystem.getLobbySystem(), all);
                    }
                    sichtbarkeitsInterface.updateCurrentMode(uuid, 2);
                    player.getInventory().setItem(3, new ItemBuilder(Material.RED_DYE, 1, 0).name(playerLobbyLanguage.getPlayerVisiviltyItem()).itemStack());
                    player.sendMessage(playerLobbyLanguage.noPlayerVisible);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1 ,1);
            }
            if (event.getItem().getType().equals(Material.RED_DYE)) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(LobbySystem.getLobbySystem(), all);
                    }
                    sichtbarkeitsInterface.updateCurrentMode(uuid, 0);
                    player.getInventory().setItem(3, new ItemBuilder(Material.LIME_DYE, 1, 0).name(playerLobbyLanguage.getPlayerVisiviltyItem()).itemStack());
                    player.sendMessage(playerLobbyLanguage.allPlayerVisible);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1 ,1);
            }
            if (event.getItem().getType().equals(Material.LIME_DYE)) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(Permissions.VIPPERMISSIONS.toString())) {
                            player.showPlayer(LobbySystem.getLobbySystem(), all);
                        } else {
                            player.hidePlayer(LobbySystem.getLobbySystem(), all);
                        }
                    }
                    sichtbarkeitsInterface.updateCurrentMode(uuid, 1);
                    player.getInventory().setItem(3, new ItemBuilder(Material.PURPLE_DYE, 1, 0).name(playerLobbyLanguage.getPlayerVisiviltyItem()).itemStack());
                    player.sendMessage(playerLobbyLanguage.onlyVipVisivble);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1 ,1);
            }
        }
    }
}