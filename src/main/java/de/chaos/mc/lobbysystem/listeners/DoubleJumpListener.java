package de.chaos.mc.lobbysystem.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpListener implements Listener {
    @EventHandler
    public void setVelocity(PlayerToggleFlightEvent event) {
        event.setCancelled(true);
        System.out.println("Flight toggled");

        Player player = event.getPlayer();
        if (player.getLocation().subtract(0, 10, 0).getBlock().getType().equals(Material.AIR)) return;


        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || player.isFlying()) return;
        else {
            player.setAllowFlight(false);
            player.setFlying(false);

            player.setVelocity(event.getPlayer().getLocation().getDirection().multiply(2).setY(2));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);

            player.setAllowFlight(true);

        }

    }
}
