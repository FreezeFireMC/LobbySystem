package de.chaos.mc.lobbysystem.utils;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
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
        menu.additem(36, new ItemBuilder(Material.IRON_SWORD).name("§6SkyWars").itemStack(), player1 -> {
            player1.teleport(LobbySystem.getLocationInterface().getLocation("SkyWars"));
            player1.closeInventory();
        });
        menu.additem(44, new ItemBuilder(Material.SKULL_ITEM, 1, 3).skullOwner("ChaosNeko").name("§6Bald...").itemStack(), player1 -> {
            player1.sendMessage(DefaultMessages.normalMessage("Kommt bald..."));
            player1.closeInventory();
        });
        menu.openInventory(player);
    }
}
