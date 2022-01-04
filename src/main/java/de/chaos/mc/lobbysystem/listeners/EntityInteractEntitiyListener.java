package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteractEntitiyListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        System.out.println(event.getRightClicked().toString());
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            System.out.println(armorStand.getCustomName());
            if (armorStand.getCustomName().equalsIgnoreCase("LANGUAGE_STAND")) {
                LobbySystem.getLobbySystem().getProfileInventorys().getLanguageInventory(event.getPlayer());
            }
        }
        event.setCancelled(true);
    }
}