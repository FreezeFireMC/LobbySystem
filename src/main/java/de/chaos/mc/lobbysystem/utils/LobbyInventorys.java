package de.chaos.mc.lobbysystem.utils;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LobbyInventorys {
    public static void setLobbyInventory(Player player) {
        Menu menu = LobbySystem.menuFactory.createMenu(45, "§6Navigator");
        menu.additem(0, new ItemBuilder(Material.WOOD_AXE).name("§6GunGame").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLocationInterface().getLocation("GunGame"));
            player1.closeInventory();
        });
        menu.additem(8, new ItemBuilder(Material.GRASS).name("§6CityBuild").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLocationInterface().getLocation("CityBuild"));
            player1.closeInventory();
        });
        menu.additem(22, new ItemBuilder(Material.NETHER_STAR).name("§6Spawn").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLocationInterface().getLocation("Spawn"));
            player1.closeInventory();
        });
        menu.additem(36, new ItemBuilder(Material.SNOW_BALL).name("§6FreezeFight").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLocationInterface().getLocation("SkyWars"));
            player1.closeInventory();
        });
        menu.additem(44, new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner("ChaosNeko").name("§6Bald...").itemStack(), player1 -> {
            player1.sendMessage(DefaultMessages.normalMessage("Kommt bald..."));
            player1.closeInventory();
        });
        menu.openInventory(player);
    }
    public static void setMainCosmeticInventory(Player player) {
        Menu menu = LobbySystem.menuFactory.createMenu(27, "§6Cosmetics");
        menu.addSubmenu(11, new ItemBuilder(Material.LEATHER_CHESTPLATE).name("§cRüstung").itemStack(), getArmourMenu());
        menu.addSubmenu(15, new ItemBuilder(Material.SKULL_ITEM, 1 ,3).skullOwner(player.getName()).name("§6Köpfe").itemStack(), getHeadMenu(player));
        menu.openInventory(player);
    }

    public static Menu getArmourMenu() {
        Menu menu = LobbySystem.menuFactory.createMenu(27, "§6Rüstung");
        return menu;
    }
    public static Menu getHeadMenu(Player invplayer) {
        Menu menu = LobbySystem.menuFactory.createMenu(27, "§6Köpfe");
        menu.additem(10, new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner("GommeHD").name("§6GommeHD´s Kopd").itemStack(), player -> {
                    player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner("GommeHD").name("§6GommeHD´s Kopd").itemStack());
            player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
                });

        menu.additem(11, new ItemBuilder(Material.SKULL_ITEM, 1, 3).name("§6BastiGHG´s Kopf").skullOwner("BastiGHG").itemStack(), player -> {
            player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).name("§6BastiGHG´s Kopf").skullOwner("BastiGHG").itemStack());
            player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
        });
        menu.additem(12, new ItemBuilder(Material.SKULL_ITEM, 1, 3).name("§6thejocraft´s Kopf").skullOwner("thejocraft").itemStack(), player -> {
            player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM, 1, 3).name("§6thejocraft´s Kopf").skullOwner("thejocraft").itemStack());
            player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
        });
        menu.additem(13, new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6RedSlxzer´s Kopf").skullOwner("RedSlxzer").itemStack(), player -> {
            if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6RedSlxzer´s Kopf").skullOwner("RedSlxzer").itemStack());
                player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
            } else {
                player.sendMessage(DefaultMessages.NOPERMISSION);
            }
        });
        menu.additem(14, new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6 " + invplayer.getName() + "´s Kopf").skullOwner(invplayer.getName()).itemStack(), player -> {
            if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6 " + invplayer.getName() + "´s Kopf").skullOwner(invplayer.getName()).itemStack());
                player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
            } else {
                player.sendMessage(DefaultMessages.NOPERMISSION);
            }
        });
        menu.additem(15, new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6TNT").skullOwner("Nirz63").itemStack(), player -> {
            if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6TNT").skullOwner("Nirz63").itemStack());
                player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
            } else {
                player.sendMessage(DefaultMessages.NOPERMISSION);
            }
        });
        menu.additem(16, new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6ChaosNeko´s Kopf").skullOwner("ChaosNeko").itemStack(), player -> {
            if (player.hasPermission(String.valueOf(Permissions.VIPPERMISSIONS))) {
                player.getInventory().setHelmet(new ItemBuilder(Material.SKULL_ITEM ,1 ,3).name("§6ChaosNeko´s Kopf").skullOwner("ChaosNeko").itemStack());
                player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde geändert!"));
            } else {
                player.sendMessage(DefaultMessages.NOPERMISSION);
            }
        });
        menu.additem(26, new ItemBuilder(Material.BARRIER).name("§cKopf entfernen...").itemStack(), player -> {
            player.getInventory().setHelmet(null);
            player.sendMessage(DefaultMessages.normalMessage("Dein Kopf wurde entfernt!"));
        });

        return menu;
    }

}
