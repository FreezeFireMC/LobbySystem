package de.chaos.mc.lobbysystem.utils;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.cosmetics.BannerMenu;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.service.ICloudService;
import eu.thesimplecloud.api.servicegroup.ICloudServiceGroup;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyInventorys {
    public static void setLobbyInventory(Player player) {
        PlayerLanguage playerLanguage = ServerAPIBukkitMain.getOnlinePlayers().get(player.getUniqueId());
        Menu menu = LobbySystem.menuFactory.createMenu(45, "§6Navigator");
        menu.additem(10, new ItemBuilder(Material.RED_BED).name("§6BedWars").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("BedWars"));
            player1.closeInventory();
        });
        menu.additem(16, new ItemBuilder(Material.GRASS_BLOCK, getOnlineCount("FreeBuild")).name("§6FreeBuild").itemStack(), player1 -> {
            CloudAPI.getInstance().getCloudPlayerManager().connectPlayer(CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player1.getUniqueId()), CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName("FreeBuild-1"));
            player1.closeInventory();
        });
        menu.additem(22, new ItemBuilder(Material.NETHER_STAR, getOnlineCount("Lobby")).name("§6Spawn").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("Spawn"));
            player1.closeInventory();
        });
        menu.additem(28, new ItemBuilder(Material.SNOWBALL, getOnlineCount("FreezeFight")).name("§6FreezeFight").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLobbySystem().getLocationInterface().getLocation("FreezeFight"));
            player1.closeInventory();
        });
        menu.additem(34, new ItemBuilder(Material.BLAZE_ROD, getOnlineCount("MLGRushHub")).name("§6MLGRush").itemStack(), player1 -> {
            ICloudServiceGroup group = CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName("MLGRushHub");
            ICloudService cloudService = group.getAllServices().get(0);
            int index = 0;
            for (ICloudService service : group.getAllServices()) {
                index++;
                if (group.getAllServices().size() != index) {
                    if (group.getAllServices().get(index).getOnlineCount() < cloudService.getOnlineCount()) {
                        cloudService = service;
                    }
                }
            }
            CloudAPI.getInstance().getCloudPlayerManager().connectPlayer(CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player1.getUniqueId()), cloudService);
        });
        menu.openInventory(player);
    }
    public static void setMainCosmeticInventory(Player player) {
        Menu menu = LobbySystem.menuFactory.createMenu(3*9, "§6Cosmetics");
        menu.addSubmenu(10, new ItemBuilder(Material.BLAZE_POWDER).name("§6Particles").itemStack(), getParticlesMenu(player));
        menu.addSubmenu(12, new ItemBuilder(Material.ENCHANTING_TABLE).name("§6Cloaks").itemStack(), getCloaksMenu(player));
        menu.additem(14, new ItemBuilder(Material.BLACK_BANNER).name("§6Banners").itemStack(), LobbyInventorys::getBannerMenu);
        menu.addSubmenu(16,new ItemBuilder(Material.PLAYER_HEAD).skullOwner("ChaosNeko").name("§6Heads").itemStack(), getHeadsMenu(player));


        menu.openInventory(player);
    }

    public static Menu getParticlesMenu(Player player) {
        return null;
    }
    public static void getBannerMenu(Player player) {
        new BannerMenu(LobbySystem.menuFactory, player);
    }
    public static Menu getCloaksMenu(Player player) {
        return null;
    }
    public static Menu getHeadsMenu(Player player) {
        return null;
    }



    public static int getOnlineCount(String group) {
        if (CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(group).getOnlinePlayerCount() != 0) {
                return CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(group).getOnlinePlayerCount();
        } else {
            return 1;
        }
    }

}
