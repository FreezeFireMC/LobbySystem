package de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary;

import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.ormlite.UpdateMLGRushInventorySortingInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    private UpdateMLGRushInventorySortingInterface sortingInterface;
    private LanguageInterface languageInterface;
    public InventoryCloseListener(UpdateMLGRushInventorySortingInterface inventorySortingInterface, LanguageInterface languageInterface) {
        this.sortingInterface = inventorySortingInterface;
        this.languageInterface = languageInterface;
    }
    @EventHandler
    public void onInventoryClosing(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getInventory().getName().equalsIgnoreCase(GermanMLGRushTranslations.INVNAME.getTranslation()) || event.getInventory().getName().equalsIgnoreCase(EnglishMLGRushITranslations.INVNAME.getTranslation()) || event.getInventory().getName().equalsIgnoreCase(FrenchMLGRushTranslations.INVNAME.getTranslation())) {
            sortingInterface.updateSorting(player, event.getInventory());
            player.sendMessage(getClosingMessage(player));
        }
    }

    public String getClosingMessage(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishMLGRushITranslations.INVENTORYUPDATED.getTranslation();
                break;
            case DE:
                string = GermanMLGRushTranslations.INVENTORYUPDATED.getTranslation();
                break;
            case FR:
                string = FrenchMLGRushTranslations.INVENTORYUPDATED.getTranslation();
                break;
            case OTHER:
                string = EnglishMLGRushITranslations.INVENTORYUPDATED.getTranslation();
                break;
        }
        return string;
    }
}
