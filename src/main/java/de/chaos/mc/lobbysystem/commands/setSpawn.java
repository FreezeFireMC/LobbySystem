package de.chaos.mc.lobbysystem.commands;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.utils.stringLibary.DefaultMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(String.valueOf(Permissions.SETSPAWN))) {
                if (LobbySystem.getLocationInterface().getLocation("Spawn") != null) {
                    LobbySystem.getLocationInterface().delLocation("Spawn");
                }
                LobbySystem.getLocationInterface().addLocation("Spawn", player.getLocation());
            } else {
                player.sendMessage(DefaultMessages.NOPERMISSION);
            }
        } else {
            sender.sendMessage(DefaultMessages.BEAPLAYER);
        }
        return false;
    }
}
