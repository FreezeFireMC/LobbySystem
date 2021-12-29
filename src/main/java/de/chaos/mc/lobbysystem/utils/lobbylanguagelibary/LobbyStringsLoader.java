package de.chaos.mc.lobbysystem.utils.lobbylanguagelibary;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.lobbytranslations.LobbyEnglishTranslation;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.lobbytranslations.LobbyFrenchTranslation;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.lobbytranslations.LobbyGermanTranslation;
import de.chaos.mc.lobbysystem.utils.scorebaord.scoreboards.FrenchScoreboard;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;

import java.util.UUID;

public class LobbyStringsLoader {
    private LanguageInterface languageInterface;
    public LobbyStringsLoader(LanguageInterface languageInterface) {
        this.languageInterface = languageInterface;
    }
    public PlayerLobbyLanguage getPlayerLobbyLanguage(UUID uuid) {
        PlayerLobbyLanguage playerLobbyLanguage = null;
        switch(languageInterface.getLanguageType(uuid)) {
            case EG:
                playerLobbyLanguage = PlayerLobbyLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.EG)
                        .playerVisiviltyItem(LobbyEnglishTranslation.playerVisiviltyItem)
                        .onlyVipVisivble(LobbyEnglishTranslation.onlyVipVisivle)
                        .noPlayerVisible(LobbyEnglishTranslation.noPlayerVisible)
                        .allPlayerVisible(LobbyEnglishTranslation.allPlayerVisivle)
                        .languageUpdated(LobbyEnglishTranslation.languageUpdated)
                        .languageInv(LobbyEnglishTranslation.languageInv)
                        .visibilityItem(LobbyEnglishTranslation.visibilityItem)
                        .build();
                LobbySystem.getOnlinePlayers().put(uuid, playerLobbyLanguage);
                break;
            case DE:
                playerLobbyLanguage = PlayerLobbyLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.DE)
                        .playerVisiviltyItem(LobbyGermanTranslation.playerVisiviltyItem)
                        .onlyVipVisivble(LobbyGermanTranslation.onlyVipVisivle)
                        .noPlayerVisible(LobbyGermanTranslation.noPlayerVisible)
                        .allPlayerVisible(LobbyGermanTranslation.allPlayerVisivle)
                        .languageUpdated(LobbyGermanTranslation.languageUpdated)
                        .languageInv(LobbyGermanTranslation.languageInv)
                        .visibilityItem(LobbyGermanTranslation.visibilityItem)
                        .build();
                LobbySystem.getOnlinePlayers().put(uuid, playerLobbyLanguage);
                break;
            case FR:
                playerLobbyLanguage = PlayerLobbyLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.EG)
                        .playerVisiviltyItem(LobbyFrenchTranslation.playerVisiviltyItem)
                        .onlyVipVisivble(LobbyFrenchTranslation.onlyVipVisivle)
                        .noPlayerVisible(LobbyFrenchTranslation.noPlayerVisible)
                        .allPlayerVisible(LobbyFrenchTranslation.allPlayerVisivle)
                        .languageUpdated(LobbyFrenchTranslation.languageUpdated)
                        .languageInv(LobbyFrenchTranslation.languageInv)
                        .visibilityItem(LobbyFrenchTranslation.visibilityItem)
                        .build();
                LobbySystem.getOnlinePlayers().put(uuid, playerLobbyLanguage);
                break;
            case OTHER:
                playerLobbyLanguage = PlayerLobbyLanguage.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.EG)
                        .playerVisiviltyItem(LobbyEnglishTranslation.playerVisiviltyItem)
                        .onlyVipVisivble(LobbyEnglishTranslation.onlyVipVisivle)
                        .noPlayerVisible(LobbyEnglishTranslation.noPlayerVisible)
                        .allPlayerVisible(LobbyEnglishTranslation.allPlayerVisivle)
                        .languageUpdated(LobbyEnglishTranslation.languageUpdated)
                        .languageInv(LobbyEnglishTranslation.languageInv)
                        .visibilityItem(LobbyEnglishTranslation.visibilityItem)
                        .build();
                LobbySystem.getOnlinePlayers().put(uuid, playerLobbyLanguage);
                break;
        }
        return playerLobbyLanguage;
    }
}
