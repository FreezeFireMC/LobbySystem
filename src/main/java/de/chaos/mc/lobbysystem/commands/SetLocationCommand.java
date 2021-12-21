package de.chaos.mc.lobbysystem.commands;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.stringUtils.Permissions;
import de.chaos.mc.serverapi.ServerAPIBukkitMain;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.PlayerLanguage;
import de.chaos.mc.serverapi.utils.stringLibary.AbstractMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLocationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerLanguage playerLanguage = ServerAPIBukkitMain.getOnlinePlayers().get(player.getUniqueId());
            if (player.hasPermission(String.valueOf(Permissions.SETLOCATION))) {
                if (args.length == 1) {
                    String mapName = args[0];
                    LobbySystem.getLobbySystem().getLocationInterface().addLocation(mapName, player.getLocation());
                } else player.sendMessage(AbstractMessages.wrongSyntax("/setLocation [LocationName]"));

            } else player.sendMessage(playerLanguage.getNOPERMISSION());
        } else sender.sendMessage(AbstractMessages.BEAPLAYER);

        return true;
    }
}
