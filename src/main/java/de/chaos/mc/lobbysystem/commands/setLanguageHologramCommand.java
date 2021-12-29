package de.chaos.mc.lobbysystem.commands;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.hologramutils.Hologram;
import de.chaos.mc.lobbysystem.utils.hologramutils.HologramTranslations;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class setLanguageHologramCommand implements CommandExecutor {
    LanguageInterface languageInterface;
    public static BukkitTask bukkitTask;
    private static Hologram germanHolo;
    private static Hologram frenchHolo;
    private static Hologram englishHolo;
    public setLanguageHologramCommand(LanguageInterface languageInterface, Plugin plugin) {
        this.languageInterface = languageInterface;
        if (LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo") != null) {
            Location location = LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo");
            germanHolo = new Hologram(location, HologramTranslations.GERMAN.getTranslation());
            frenchHolo = new Hologram(location, HologramTranslations.FRENCH.getTranslation());
            englishHolo = new Hologram(location, HologramTranslations.ENGLISH.getTranslation());
            }
        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo") != null) {
                    Location location = LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo");
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.DE)) {
                            germanHolo.display(target);
                            englishHolo.hide(target);
                            frenchHolo.hide(target);
                        }
                        if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.EG)) {
                            englishHolo.display(target);
                            germanHolo.hide(target);
                            frenchHolo.hide(target);
                        }
                        if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.FR)) {
                            frenchHolo.display(target);
                            englishHolo.hide(target);
                            germanHolo.hide(target);
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20, 5);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerLanguage playerLanguage = ServerAPIBukkitMain.getOnlinePlayers().get(player.getUniqueId());
            if (player.hasPermission(Permissions.SETHOLOGRAM.getString())) {
                LobbySystem.getLobbySystem().getLocationInterface().addLocation("LanguageHolo", player.getLocation());
                Hologram germanHolo = new Hologram(player.getLocation(), HologramTranslations.GERMAN.getTranslation());
                Hologram frenchHolo = new Hologram(player.getLocation(), HologramTranslations.FRENCH.getTranslation());
                Hologram englishHolo = new Hologram(player.getLocation(), HologramTranslations.ENGLISH.getTranslation());

                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.DE)) {
                        germanHolo.display(target);
                        englishHolo.hide(target);
                        frenchHolo.hide(target);
                    }
                    if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.EG)) {
                        englishHolo.display(target);
                        germanHolo.hide(target);
                        frenchHolo.hide(target);
                    }
                    if (ServerAPIBukkitMain.getOnlinePlayers().get(target.getUniqueId()).getLanguageType().equals(LanguageType.FR)) {
                        frenchHolo.display(target);
                        englishHolo.hide(target);
                        germanHolo.hide(target);
                    }
                }
            } else {
                player.sendMessage(playerLanguage.getNOPERMISSION());
            }
        }
        return false;
    }
}
