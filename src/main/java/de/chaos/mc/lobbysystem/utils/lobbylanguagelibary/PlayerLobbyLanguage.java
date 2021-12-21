package de.chaos.mc.lobbysystem.utils.lobbylanguagelibary;

import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlayerLobbyLanguage {
    public UUID uuid;
    public LanguageType languageType;

    public String playerVisiviltyItem;
    public String onlyVipVisivble;
    public String noPlayerVisible;
    public String allPlayerVisible;
}
