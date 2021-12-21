package de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.ormlite;


import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.utils.daos.DAOManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.SQLException;
import java.util.UUID;

public class UpdateMLGRushInvSortingRepository implements UpdateMLGRushInventorySortingInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<MLGRushInventoryDAO, UUID> daoManager;

    public UpdateMLGRushInvSortingRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<MLGRushInventoryDAO, UUID>(MLGRushInventoryDAO.class, connectionSource);
    }

    @Override
    public MLGRushInventoryDAO getInventory(UUID uuid) {
        MLGRushInventoryDAO inventoryDAO = null;

        try {
            inventoryDAO = daoManager.getDAO().queryForId(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return inventoryDAO;
    }

    @Override
    public void updateInventory(MLGRushInventoryDAO inventoryDAO) {
        try {
            daoManager.getDAO().createOrUpdate(inventoryDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void checkIfFirstJoin(UUID uuid) {
        MLGRushInventoryDAO MLGRushInventoryDAO = null;

        try {
            if (getInventory(uuid) == null) {
                MLGRushInventoryDAO = MLGRushInventoryDAO.builder()
                        .uuid(uuid)
                        .stickSlot(0)
                        .pickaxeSlot(1)
                        .sandstoneSlot(2)
                        .build();
                daoManager.getDAO().createOrUpdate(MLGRushInventoryDAO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void updateSorting(Player player, Inventory inventory) {
        int sword = 5;
        int sandstone = 5;
        int pickaxe = 5;

        if (inventory.getItem(0) != null) {
            if (inventory.getItem(0).getType().equals(Material.STICK)) {
                sword = 0;
            }
            if (inventory.getItem(0).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 0;
            }
            if (inventory.getItem(0).getType().equals(Material.SANDSTONE)) {
                sandstone = 0;
            }
        }

        if (inventory.getItem(1) != null) {
            if (inventory.getItem(1).getType().equals(Material.STICK)) {
                sword = 1;
            }
            if (inventory.getItem(1).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 1;
            }
            if (inventory.getItem(1).getType().equals(Material.SANDSTONE)) {
                sandstone = 1;
            }
        }

        if (inventory.getItem(2) != null) {
            if (inventory.getItem(2).getType().equals(Material.STICK)) {
                sword = 2;
            }
            if (inventory.getItem(2).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 2;
            }
            if (inventory.getItem(2).getType().equals(Material.SANDSTONE)) {
                sandstone = 2;
            }
        }

        if (inventory.getItem(3) != null) {
            if (inventory.getItem(3).getType().equals(Material.STICK)) {
                sword = 3;
            }
            if (inventory.getItem(3).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 3;
            }
            if (inventory.getItem(3).getType().equals(Material.SANDSTONE)) {
                sandstone = 3;
            }
        }

        if (inventory.getItem(4) != null) {
            if (inventory.getItem(4).getType().equals(Material.STICK)) {
                sword = 4;
            }
            if (inventory.getItem(4).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 4;
            }
            if (inventory.getItem(4).getType().equals(Material.SANDSTONE)) {
                sandstone = 4;
            }
        }

        if (inventory.getItem(5) != null) {
            if (inventory.getItem(5).getType().equals(Material.STICK)) {
                sword = 5;
            }
            if (inventory.getItem(5).getType().equals(Material.STONE_PICKAXE)) {
                pickaxe = 5;
            }
            if (inventory.getItem(5).getType().equals(Material.SANDSTONE)) {
                sandstone = 5;
            }
        }

    if (inventory.getItem(6) != null) {
        if (inventory.getItem(6).getType().equals(Material.STICK)) {
            sword = 6;
        }
        if (inventory.getItem(6).getType().equals(Material.STONE_PICKAXE)) {
            pickaxe = 6;
        }
        if (inventory.getItem(6).getType().equals(Material.SANDSTONE)) {
            sandstone = 6;
        }
    }

    if (inventory.getItem(7) != null) {
        if (inventory.getItem(7).getType().equals(Material.STICK)) {
            sword = 7;
        }
        if (inventory.getItem(7).getType().equals(Material.STONE_PICKAXE)) {
            pickaxe = 7;
        }
        if (inventory.getItem(7).getType().equals(Material.SANDSTONE)) {
            sandstone = 7;
        }
    }

    if (inventory.getItem(8) != null) {
        if (inventory.getItem(8).getType().equals(Material.STICK)) {
            sword = 8;
        }
        if (inventory.getItem(8).getType().equals(Material.STONE_PICKAXE)) {
            pickaxe = 8;
        }
        if (inventory.getItem(8).getType().equals(Material.SANDSTONE)) {
            sandstone = 8;
        }
    }

        MLGRushInventoryDAO MLGRushInventoryDAO = de.chaos.mc.lobbysystem.utils.mlgrushinventorylibary.ormlite.MLGRushInventoryDAO.builder()
                .uuid(player.getUniqueId())
                .stickSlot(sword)
                .pickaxeSlot(pickaxe)
                .sandstoneSlot(sandstone)
                .build();
        this.updateInventory(MLGRushInventoryDAO);
    }
}
