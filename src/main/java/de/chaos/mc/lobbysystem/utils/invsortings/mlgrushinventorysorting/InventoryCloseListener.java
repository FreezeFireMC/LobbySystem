package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting;

import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.EnglishFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.FrenchFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames.GermanFreezeFightTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.UpdateFreezeFightInventorySortingInterface;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.EnglishMLGRushITranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.FrenchMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames.GermanMLGRushTranslations;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.UpdateMLGRushInventorySortingInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    private UpdateMLGRushInventorySortingInterface sortingInterface;
    private LanguageInterface languageInterface;
    private UpdateFreezeFightInventorySortingInterface freezeFightInventorySortingInterface;
    public InventoryCloseListener(UpdateFreezeFightInventorySortingInterface freezeFightInventorySortingInterface , UpdateMLGRushInventorySortingInterface inventorySortingInterface, LanguageInterface languageInterface) {
        this.sortingInterface = inventorySortingInterface;
        this.languageInterface = languageInterface;
        this.freezeFightInventorySortingInterface = freezeFightInventorySortingInterface;
    }
    @EventHandler
    public void onInventoryClosing(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase(GermanMLGRushTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(EnglishMLGRushITranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(FrenchMLGRushTranslations.INVNAME.getTranslation())) {
            sortingInterface.updateSorting(player, event.getInventory());
            player.sendMessage(getMLGRushClosingMessage(player));
            return;
        }
        if (event.getView().getTitle().equalsIgnoreCase(GermanFreezeFightTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(GermanFreezeFightTranslations.INVNAME.getTranslation()) || event.getView().getTitle().equalsIgnoreCase(GermanFreezeFightTranslations.INVNAME.getTranslation())) {
            freezeFightInventorySortingInterface.updateSorting(player, event.getInventory());
            player.sendMessage(getFreezeFightClosingMessage(player));
            return;
        }
    }

    public String getMLGRushClosingMessage(Player player) {
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
    public String getFreezeFightClosingMessage(Player player) {
        String string = null;
        switch(languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                string = EnglishFreezeFightTranslations.INVENTORYUPDATED.getTranslation();
                break;
            case DE:
                string = GermanFreezeFightTranslations.INVENTORYUPDATED.getTranslation();
                break;
            case FR:
                string = FrenchFreezeFightTranslations.INVENTORYUPDATED.getTranslation();
                break;
            case OTHER:
                string = EnglishFreezeFightTranslations.INVENTORYUPDATED.getTranslation();
                break;
        }
        return string;
    }
}
