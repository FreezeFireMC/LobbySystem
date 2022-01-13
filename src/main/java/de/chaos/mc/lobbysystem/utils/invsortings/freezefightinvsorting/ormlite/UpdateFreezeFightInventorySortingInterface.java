package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public interface UpdateFreezeFightInventorySortingInterface {
    public FreezeFightInvSortingDAO getInventory(UUID uuid);
    public void updateInventory(FreezeFightInvSortingDAO inventoryDAO);
    public void checkIfFirstJoin(UUID uuid);
    public void updateSorting(Player player, Inventory inventory);
}
