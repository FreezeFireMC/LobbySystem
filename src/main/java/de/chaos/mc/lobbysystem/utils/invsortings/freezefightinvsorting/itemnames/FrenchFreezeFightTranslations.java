package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FrenchFreezeFightTranslations {
    SWORD("§7Sword"),
    BOW("§6Bow"),
    EGG("§6Egg"),
    ARROW("§6Arrow"),
    INVNAME("§6Inventory-Sorting"),
    INVENTORYUPDATED(AbstractMessages.PREFIX +  "You inventory-sorting for mlgrush was updated");

    @Getter
    private String translation;
}
