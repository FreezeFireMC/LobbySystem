package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public interface UpdateMLGRushInventorySortingInterface {
    public MLGRushInventoryDAO getInventory(UUID uuid);
    public void updateInventory(MLGRushInventoryDAO inventoryDAO);
    public void checkIfFirstJoin(UUID uuid);
    public void updateSorting(Player player, Inventory inventory);
}
