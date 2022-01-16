package de.chaos.mc.lobbysystem.utils.placeholders;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.hologramutils.HologramTranslations;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class LanguageSwitchHoloPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "LanguageHolo";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Chaoshero5567";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("name")){
            return getName(player);
        }
        return null;
    }

    @Override
    public boolean persist() {
        return true;
    }

    public String getName(OfflinePlayer player) {
        switch (LobbySystem.getServerAPI().getLanguageInterface().getLanguageType(player.getUniqueId())) {
            case DE:
                return HologramTranslations.GERMAN.getTranslation();
            case FR:
                return HologramTranslations.FRENCH.getTranslation();
            case OTHER:
                return HologramTranslations.ENGLISH.getTranslation();
            case EG:
                return HologramTranslations.ENGLISH.getTranslation();
            default:
                return HologramTranslations.ENGLISH.getTranslation();
        }
    }
}
