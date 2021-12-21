package de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FrenchMLGRushTranslations {
    STICK("§6Stick"),
    PICKAXT("§6Spitzhacke"),
    BLOCKS("§6Blöcke"),
    INVNAME("§6Inventory-Sorting"),
    INVENTORYUPDATED("§6You inventory-sorting for mlgrush was updated");

    @Getter
    private String translation;
}
