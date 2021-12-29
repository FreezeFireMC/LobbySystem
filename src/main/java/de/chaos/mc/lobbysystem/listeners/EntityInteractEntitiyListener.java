package de.chaos.mc.lobbysystem.listeners;

import de.chaos.mc.lobbysystem.LobbySystem;
import de.chaos.mc.lobbysystem.utils.hologramutils.HologramTranslations;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityInteractEntitiyListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.ARMOR_STAND) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            if (armorStand.getCustomName().equalsIgnoreCase(HologramTranslations.ENGLISH.getTranslation()) || armorStand.getCustomName().equalsIgnoreCase(HologramTranslations.GERMAN.getTranslation()) || armorStand.getCustomName().equalsIgnoreCase(HologramTranslations.FRENCH.getTranslation()) || armorStand.getCustomName().equalsIgnoreCase("LANGUAGE_STAND")) {
                LobbySystem.getLobbySystem().getProfileInventorys().getLanguageInventory(event.getPlayer());
                event.setCancelled(true);
                return;
            }
        }
        event.setCancelled(true);
    }
}
