package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnglishMLGRushITranslations {
    STICK("§6Stick"),
    PICKAXT("§6Pickaxt"),
    BLOCKS("§6Blocks"),
    INVNAME("§6Inventory-Sorting"),
    INVENTORYUPDATED(AbstractMessages.PREFIX +  "You inventory-sorting for mlgrush was updated");

    @Getter private String translation;
}
