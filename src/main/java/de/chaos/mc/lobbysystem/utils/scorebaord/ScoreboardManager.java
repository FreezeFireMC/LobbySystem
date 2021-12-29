package de.chaos.mc.lobbysystem.utils.scorebaord;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.scorebaord.scoreboards.EnglishScoreboard;
import de.chaos.mc.lobbysystem.utils.scorebaord.scoreboards.FrenchScoreboard;
import de.chaos.mc.lobbysystem.utils.scorebaord.scoreboards.GermanScoreboard;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager {
    private final
    SichtbarkeitsInterface sichtbarkeitsIntreface;
    public HashMap<UUID, PlayerScorebaord> playerScorebaordHashMap;
    public HashMap<UUID, BossBar> bossBarHashMap;
    private LanguageInterface languageInterface;


    private  EnglishScoreboard englishScoreboards;
    private  GermanScoreboard germanScoreboards;
    private  FrenchScoreboard frenchScoreboards;

    public ScoreboardManager(Plugin plugin, LanguageInterface languageInterface) {
         playerScorebaordHashMap = new HashMap<>();
         bossBarHashMap = new HashMap<>();
         this.sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
         this.languageInterface = languageInterface;

         startUpdater(plugin);
    }
    public void startUpdater(Plugin plugin) {
        englishScoreboards = new EnglishScoreboard(plugin, playerScorebaordHashMap);
        germanScoreboards = new GermanScoreboard(plugin,  playerScorebaordHashMap);
        frenchScoreboards = new FrenchScoreboard(plugin,  playerScorebaordHashMap);
    }


    public void getScorebaord(@NotNull Player player) {
        switch (languageInterface.getLanguageType(player.getUniqueId())) {
            case EG:
                englishScoreboards.getScorebaord(player);
                break;
            case DE:
                germanScoreboards.getScorebaord(player);
                break;
            case FR:
                frenchScoreboards.getScorebaord(player);
                break;
            case OTHER:
                englishScoreboards.getScorebaord(player);
                break;
        }
    }

    public void switchBoard(Player player, @NotNull LanguageType languageType) {
        switch (languageType) {
            case DE:
                playerScorebaordHashMap.remove(player.getUniqueId());
                germanScoreboards.getScorebaord(player);
                break;
            case EG:
                playerScorebaordHashMap.remove(player.getUniqueId());
                englishScoreboards.getScorebaord(player);
                break;
            case FR:
                playerScorebaordHashMap.remove(player.getUniqueId());
                frenchScoreboards.getScorebaord(player);
                break;
            case OTHER:
                playerScorebaordHashMap.remove(player.getUniqueId());
                englishScoreboards.getScorebaord(player);
                break;
        }
    }
}
