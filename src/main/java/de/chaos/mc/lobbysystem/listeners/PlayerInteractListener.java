package de.chaos.mc.lobbysystem.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onMove(PlayerInteractEvent event) {
        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) event.setCancelled(true);
        if (event.getMaterial().equals(Material.ARMOR_STAND)) {
            event.setCancelled(true);
        }
    }
}
