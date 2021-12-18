package de.chaos.mc.lobbysystem.listeners;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJumpListener implements Listener {
    @EventHandler
    public void onPlayerFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().multiply(3.0D).setY(1.9D));
            player.playEffect(player.getLocation(), Effect.BLAZE_SHOOT, 15);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if ((event.getPlayer().getGameMode() != GameMode.CREATIVE)
                && (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)) {
            player.setAllowFlight(true);
        }
    }
}
