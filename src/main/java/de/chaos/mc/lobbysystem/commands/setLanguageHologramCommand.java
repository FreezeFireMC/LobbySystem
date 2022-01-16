package de.chaos.mc.lobbysystem.commands;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class setLanguageHologramCommand implements CommandExecutor {
    public setLanguageHologramCommand() {
        if (LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo") != null) {
            Location location = LobbySystem.getLobbySystem().getLocationInterface().getLocation("LanguageHolo");
            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            String name = "%player_language_holo_name%";
            for (Player player : Bukkit.getOnlinePlayers()) {
                armorStand.setCustomName(PlaceholderAPI.setPlaceholders(player, name));
                armorStand.setCustomNameVisible(true);
                armorStand.setVisible(false);
            }
            LobbySystem.setLanguageHolo(armorStand);
            }

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerLanguage playerLanguage = ServerAPIBukkitMain.getOnlinePlayers().get(player.getUniqueId());
            if (player.hasPermission(Permissions.SETHOLOGRAM.getString())) {
                LobbySystem.getLobbySystem().getLocationInterface().addLocation("LanguageHolo", player.getLocation());
                ArmorStand armorStand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                String name = "%player_language_holo_name%";
                armorStand.setCustomName(PlaceholderAPI.setPlaceholders(player, name));
                armorStand.setCustomNameVisible(true);
                armorStand.setVisible(false);
            } else {
                player.sendMessage(playerLanguage.getNOPERMISSION());
            }
        }
        return false;
    }
}
