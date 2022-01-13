package de.chaos.mc.lobbysystem.utils.profile;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.PlayerLobbyLanguage;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.Menu;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import org.bukkit.entity.Player;

// The Settingsinv for the player
public class SettingsInventory {
    Player player;
    MenuFactory menuFactory;
    public SettingsInventory(Player player, MenuFactory menuFactory) {
        this.player = player;
        this.menuFactory = menuFactory;
        getMenu().openInventory(player);
    }
    public Menu getMenu() {
        PlayerLobbyLanguage lobbyLanguage = LobbySystem.getOnlinePlayers().get(player.getUniqueId());
        Menu menu = menuFactory.createMenu(3*9, lobbyLanguage.getSettingsInv());
        return menu;
    }
}