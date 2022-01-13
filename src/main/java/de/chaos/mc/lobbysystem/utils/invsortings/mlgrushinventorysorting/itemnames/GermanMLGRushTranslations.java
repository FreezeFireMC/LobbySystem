package de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.itemnames;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GermanMLGRushTranslations {
    STICK("§6Stick"),
    PICKAXT("§6Spitzhacke"),
    BLOCKS("§6Blöcke"),
    INVNAME("§6InventarSortierung"),
    INVENTORYUPDATED(AbstractMessages.PREFIX +  "Deine Inventar Sortierung für MLGRush wurde aktualisiert");

    @Getter
    private String translation;
}
