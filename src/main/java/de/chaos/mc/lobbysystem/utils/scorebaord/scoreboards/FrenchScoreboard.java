package de.chaos.mc.lobbysystem.utils.scorebaord.scoreboards;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.scorebaord.PlayerScorebaord;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class FrenchScoreboard {
    private static SichtbarkeitsInterface sichtbarkeitsIntreface;
    public HashMap<UUID, PlayerScorebaord> playerScorebaordHashMap;
    public FrenchScoreboard(Plugin plugin, HashMap<UUID, PlayerScorebaord> playerScorebaordHashMap) {
        sichtbarkeitsIntreface = LobbySystem.sichtbarkeitsIntreface;
        this.playerScorebaordHashMap = playerScorebaordHashMap;
        startUpdater(plugin);
    }
    public void startUpdater(Plugin plugin) {
        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : playerScorebaordHashMap.keySet()) {
                    PlayerScorebaord playerScorebaord = playerScorebaordHashMap.get(uuid);
                    Objective objective = playerScorebaord.getScoreboard().getObjective("Lobby");
                    if (playerScorebaord.getLanguageType() == LanguageType.FR) {
                        Player player = Bukkit.getPlayer(uuid);
                        playerScorebaord.getCoins().setSuffix("§b" + LobbySystem.getServerAPI().getCoinsInterface().getCoins(player.getUniqueId()));
                        Team visibility = playerScorebaord.getVisibility();
                        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 0) {
                            visibility.setSuffix("§bAlle");
                        }

                        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 1) {
                            visibility.setSuffix("§bNur VIP");
                        }

                        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 2) {
                            visibility.setSuffix("§bKeine");
                        }
                        visibility.addEntry(ChatColor.AQUA.toString());
                        objective.getScore(ChatColor.DARK_RED.toString()).setScore(4);
                        objective.getScore(ChatColor.AQUA.toString()).setScore(1);
                    }
                }
            }
        }.runTaskTimer(plugin,20, 1);
    }


    public Scoreboard getScorebaord(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Lobby", "2");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("  §bFreeze§cFire  ");
        if (scoreboard.getTeam(player.getName() + ".1") != null) {
            scoreboard.getTeam(player.getName() + ".1").unregister();
        }
        if (scoreboard.getTeam(player.getName() + ".2") != null) {
            scoreboard.getTeam(player.getName() + ".2").unregister();
        }

        Team coins = scoreboard.registerNewTeam(player.getName() + ".1");
        coins.setPrefix("§8» §b");
        coins.setSuffix("§b" + LobbySystem.getServerAPI().getCoinsInterface().getCoins(player.getUniqueId()));
        coins.addEntry(ChatColor.DARK_RED.toString());

        Team visibitlity = scoreboard.registerNewTeam( player.getName() + ".2");
        visibitlity.setPrefix("§8» §b");

        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 0) {
            visibitlity.setSuffix("§bAlle");
        }

        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 1) {
            visibitlity.setSuffix("§bNur VIP");
        }

        if (sichtbarkeitsIntreface.getCurrentMode(player.getUniqueId()) == 2) {
            visibitlity.setSuffix("§bKeine");
        }
        visibitlity.addEntry(ChatColor.AQUA.toString());

        objective.getScore("§0").setScore(6);
        objective.getScore("§cCoins:").setScore(5);
        objective.getScore(ChatColor.DARK_RED.toString()).setScore(4);
        objective.getScore("§1 ").setScore(3);
        objective.getScore("§cSichtbarkeit: ").setScore(2);
        objective.getScore(ChatColor.AQUA.toString()).setScore(1);
        objective.getScore("§3").setScore(0);


        PlayerScorebaord playerScorebaord = PlayerScorebaord.builder()
                .uuid(player.getUniqueId())
                .scoreboard(scoreboard)
                .coins(coins)
                .visibility(visibitlity)
                .languageType(LanguageType.FR)
                .build();

        playerScorebaordHashMap.put(player.getUniqueId(), playerScorebaord);
        player.setScoreboard(scoreboard);

        return scoreboard;
    }
}
