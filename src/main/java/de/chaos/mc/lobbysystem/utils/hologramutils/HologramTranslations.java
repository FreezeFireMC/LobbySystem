package de.chaos.mc.lobbysystem.utils.hologramutils;

import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HologramTranslations {
    GERMAN(AbstractMessages.normalMessage("Sprache wechseln")),
    ENGLISH(AbstractMessages.normalMessage("Switch language")),
    FRENCH(AbstractMessages.normalMessage("NOT TRANSLATED YET"));

    @Getter private String translation;
}
