package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FrenchMLGRushTranslations {
    STICK("§6Stick"),
    PICKAXT("§6Spitzhacke"),
    BLOCKS("§6Blöcke"),
    INVNAME("§6Inventory-Sorting"),
    INVENTORYUPDATED("You inventory-sorting for mlgrush was updated");

    @Getter
    private String translation;
}
