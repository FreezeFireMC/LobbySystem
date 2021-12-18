package de.chaos.mc.lobbysystem.utils.worldutils;

import lombok.Getter;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RedStoneLampLiter {
    private World world;
    @Getter private BukkitTask runnable;
    public RedStoneLampLiter(World world, Plugin plugin) {
        this.world = world;
        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                updateLamps();
            }
        }.runTaskTimerAsynchronously(plugin, 10, 20);
    }

    private void updateLamps() {
        for (Chunk chunk : world.getLoadedChunks()) {
            for (BlockState blockState : chunk.getTileEntities()) {
                if (blockState.getBlock().getType() == Material.REDSTONE_LAMP_OFF) {
                    blockState.setType(Material.REDSTONE_LAMP_ON);
                }
            }
        }
    }
}
