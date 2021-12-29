package de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.itemnames;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GermanMLGRushTranslations {
    STICK("§6Stick"),
    PICKAXT("§6Spitzhacke"),
    BLOCKS("§6Blöcke"),
    INVNAME("§6MLGRush-InventarSortierung"),
    INVENTORYUPDATED("§6Deine Inventar Sortierung für MLGRush wurde aktualisiert");

    @Getter
    private String translation;
}
