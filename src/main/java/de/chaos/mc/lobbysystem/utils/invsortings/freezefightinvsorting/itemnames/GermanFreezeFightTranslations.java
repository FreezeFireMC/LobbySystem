package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.itemnames;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GermanFreezeFightTranslations {
    SWORD("§7Sword"),
    BOW("§6Bow"),
    EGG("§6Egg"),
    ARROW("§6Arrow"),
    INVNAME("§6FreezeFight InventarSortierung"),
    INVENTORYUPDATED(AbstractMessages.PREFIX +  "Deine Inventar Sortierung für FreezeFight wurde aktualisiert");

    @Getter
    private String translation;
}
