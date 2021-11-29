package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getItem().getType().equals(Material.COMPASS)) {
                LobbyInventorys.setLobbyInventory(event.getPlayer());
            }
        }
    }
}
