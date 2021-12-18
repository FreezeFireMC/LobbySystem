package de.chaos.mc.lobbysystem.utils.inventorylibary;

import de.chaos.mc.lobbysystem.utils.inventorylibary.ormlite.UpdateInventorySortingInterface;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    private UpdateInventorySortingInterface sortingInterface;
    public InventoryClickListener(UpdateInventorySortingInterface inventorySortingInterface) {
        this.sortingInterface = inventorySortingInterface;
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase("§6MLGRush-InventarSortierung")) {
            if (event.getCurrentItem().getType().equals(Material.WOOL)) {
                event.setCancelled(true);
                sortingInterface.updateSorting(player, event.getInventory());
                player.closeInventory();
            }
            if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }
}
