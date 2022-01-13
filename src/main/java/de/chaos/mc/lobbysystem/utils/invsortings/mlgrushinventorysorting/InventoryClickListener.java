package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting;

import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.EnglishFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.FrenchFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.GermanFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.UpdateMLGRushInventorySortingInterface;
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
            if (event.getView().getTitle().equalsIgnoreCase(GermanFreezeFightTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(EnglishFreezeFightTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(FrenchFreezeFightTranslations.INVNAME.getTranslation())) {
                if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
