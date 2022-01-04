package de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary;

import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.ormlite.UpdateMLGRushInventorySortingInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    private UpdateMLGRushInventorySortingInterface sortingInterface;
    public InventoryClickListener(UpdateMLGRushInventorySortingInterface inventorySortingInterface) {
        this.sortingInterface = inventorySortingInterface;
    }
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getAction() == InventoryAction.HOTBAR_SWAP) {
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equalsIgnoreCase(GermanMLGRushTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(EnglishMLGRushITranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(FrenchMLGRushTranslations.INVNAME.getTranslation())) {
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }
}
