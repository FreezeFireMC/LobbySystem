package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite;


import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.serverapi.utils.daos.DAOManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;
import java.util.UUID;

public class UpdateFreezeFightInvSortingRepository implements UpdateFreezeFightInventorySortingInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<FreezeFightInvSortingDAO, UUID> daoManager;

    public UpdateFreezeFightInvSortingRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<FreezeFightInvSortingDAO, UUID>(FreezeFightInvSortingDAO.class, connectionSource);
    }

    @Override
    public FreezeFightInvSortingDAO getInventory(UUID uuid) {
        FreezeFightInvSortingDAO inventoryDAO = null;

        try {
            inventoryDAO = daoManager.getDAO().queryForId(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return inventoryDAO;
    }

    @Override
    public void updateInventory(FreezeFightInvSortingDAO inventoryDAO) {
        try {
            daoManager.getDAO().createOrUpdate(inventoryDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void checkIfFirstJoin(UUID uuid) {
        FreezeFightInvSortingDAO FreezeFightInvSortingDAO = null;

        try {
            if (getInventory(uuid) == null) {
                FreezeFightInvSortingDAO = FreezeFightInvSortingDAO.builder()
                        .uuid(uuid)
                        .swordSlot(0)
                        .bowSlot(1)
                        .eggSlot(2)
                        .arrowSlot(8)
                        .build();
                daoManager.getDAO().createOrUpdate(FreezeFightInvSortingDAO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void updateSorting(Player player, Inventory inventory) {
        int sword = 0;
        int bow = 1;
        int egg = 2;
        int arrow = 8;

        if (inventory.getItem(0) != null) {
            if (inventory.getItem(0).getType().equals(Material.IRON_SWORD)) {
                sword = 0;
            }
            if (inventory.getItem(0).getType().equals(Material.BOW)) {
                bow = 0;
            }
            if (inventory.getItem(0).getType().equals(Material.EGG)) {
                egg = 0;
            }
            if (inventory.getItem(0).getType().equals(Material.ARROW)) {
                arrow = 0;
            }
        }

        if (inventory.getItem(1) != null) {
            if (inventory.getItem(1).getType().equals(Material.IRON_SWORD)) {
                sword = 1;
            }
            if (inventory.getItem(1).getType().equals(Material.BOW)) {
                bow = 1;
            }
            if (inventory.getItem(1).getType().equals(Material.EGG)) {
                egg = 1;
            }
            if (inventory.getItem(1).getType().equals(Material.ARROW)) {
                arrow = 1;
            }
        }

        if (inventory.getItem(2) != null) {
            if (inventory.getItem(2).getType().equals(Material.IRON_SWORD)) {
                sword = 2;
            }
            if (inventory.getItem(2).getType().equals(Material.BOW)) {
                bow = 2;
            }
            if (inventory.getItem(2).getType().equals(Material.EGG)) {
                egg = 2;
            }
            if (inventory.getItem(2).getType().equals(Material.ARROW)) {
                arrow = 2;
            }
        }

        if (inventory.getItem(3) != null) {
            if (inventory.getItem(3).getType().equals(Material.IRON_SWORD)) {
                sword = 3;
            }
            if (inventory.getItem(3).getType().equals(Material.BOW)) {
                bow = 3;
            }
            if (inventory.getItem(3).getType().equals(Material.EGG)) {
                egg = 3;
            }
            if (inventory.getItem(3).getType().equals(Material.ARROW)) {
                arrow = 3;
            }
        }

        if (inventory.getItem(4) != null) {
            if (inventory.getItem(4).getType().equals(Material.IRON_SWORD)) {
                sword = 4;
            }
            if (inventory.getItem(4).getType().equals(Material.BOW)) {
                bow = 4;
            }
            if (inventory.getItem(4).getType().equals(Material.EGG)) {
                egg = 4;
            }
            if (inventory.getItem(4).getType().equals(Material.ARROW)) {
                arrow = 4;
            }
        }

        if (inventory.getItem(5) != null) {
            if (inventory.getItem(5).getType().equals(Material.IRON_SWORD)) {
                sword = 5;
            }
            if (inventory.getItem(5).getType().equals(Material.BOW)) {
                bow = 5;
            }
            if (inventory.getItem(5).getType().equals(Material.EGG)) {
                egg = 5;
            }
            if (inventory.getItem(5).getType().equals(Material.ARROW)) {
                arrow = 5;
            }
        }

    if (inventory.getItem(6) != null) {
        if (inventory.getItem(6).getType().equals(Material.IRON_SWORD)) {
            sword = 6;
        }
        if (inventory.getItem(6).getType().equals(Material.BOW)) {
            bow = 6;
        }
        if (inventory.getItem(6).getType().equals(Material.EGG)) {
            egg = 6;
        }
        if (inventory.getItem(6).getType().equals(Material.ARROW)) {
            arrow = 6;
        }
    }

    if (inventory.getItem(7) != null) {
        if (inventory.getItem(7).getType().equals(Material.IRON_SWORD)) {
            sword = 7;
        }
        if (inventory.getItem(7).getType().equals(Material.BOW)) {
            bow = 7;
        }
        if (inventory.getItem(7).getType().equals(Material.EGG)) {
            egg = 7;
        }
        if (inventory.getItem(7).getType().equals(Material.ARROW)) {
            arrow = 7;
        }
    }

    if (inventory.getItem(8) != null) {
        if (inventory.getItem(8).getType().equals(Material.IRON_SWORD)) {
            sword = 8;
        }
        if (inventory.getItem(8).getType().equals(Material.BOW)) {
            bow = 8;
        }
        if (inventory.getItem(8).getType().equals(Material.EGG)) {
            egg = 8;
        }
        if (inventory.getItem(8).getType().equals(Material.ARROW)) {
            arrow = 8;
        }
    }

        FreezeFightInvSortingDAO FreezeFightInvSortingDAO = de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.FreezeFightInvSortingDAO.builder()
                .uuid(player.getUniqueId())
                .swordSlot(sword)
                .arrowSlot(arrow)
                .bowSlot(bow)
                .eggSlot(egg)
                .build();
        this.updateInventory(FreezeFightInvSortingDAO);
    }
}
