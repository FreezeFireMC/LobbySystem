package de.chaos.mc.lobbysystem.utils.inventorylibary;

import de.chaos.mc.lobbysystem.utils.ItemBuilder;
import de.chaos.mc.lobbysystem.utils.inventorylibary.ormlite.InventoryDAO;
import de.chaos.mc.lobbysystem.utils.inventorylibary.ormlite.UpdateInventorySortingInterface;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MLGRushProfileInv {
    private UpdateInventorySortingInterface invInterface;
    private MenuFactory menuFactory;
    public MLGRushProfileInv(UpdateInventorySortingInterface updateInventorySortingInterface, MenuFactory menuFactory) {
        this.invInterface = updateInventorySortingInterface;
        this.menuFactory = menuFactory;
    }

    public void setInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getStickSlot(), new ItemBuilder(Material.STICK).name("§6Stick").itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getPickaxeSlot(), new ItemBuilder(Material.STONE_PICKAXE).name("§6Spitzhacke").itemStack());
        player.getInventory().setItem(invInterface.getInventory(player.getUniqueId()).getSandstoneSlot(),new ItemBuilder(Material.SANDSTONE, 32).name("§6SandStein").itemStack());
    }

    public Inventory getInventory(Player player) {
        InventoryDAO inventoryDAO = invInterface.getInventory(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(player, 18, "§6MLGRush-InventarSortierung");
        inventory.setItem(9, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(10, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(11, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(12, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(13, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(14, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(15, new ItemBuilder(Material.BARRIER).itemStack());
        inventory.setItem(16, new ItemBuilder(Material.BARRIER).itemStack());

        inventory.setItem(17, new ItemBuilder(Material.WOOL).name("§6Bestätigen").itemStack());

        inventory.setItem(inventoryDAO.getStickSlot(), new ItemBuilder(Material.STICK).name("§6Stick").itemStack());
        inventory.setItem(inventoryDAO.getPickaxeSlot(), new ItemBuilder(Material.STONE_PICKAXE).name("§6Spitzhacke").itemStack());
        inventory.setItem(inventoryDAO.getSandstoneSlot(), new ItemBuilder(Material.SANDSTONE).name("§6SandStein").itemStack());
        return inventory;
    }

}
