package de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.utils.daos.DAOManager;
import de.chaos.mc.lobbysystem.utils.daos.HidePlayerDAO;
import de.chaos.mc.lobbysystem.utils.daos.LocationDAO;

import java.sql.SQLException;
import java.util.UUID;

public class SichtbarkeitsRepository implements SichtbarkeitsInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<HidePlayerDAO, UUID> daoManager;

    public SichtbarkeitsRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<HidePlayerDAO, UUID>(HidePlayerDAO.class, connectionSource);
    }
    @Override
    public int getCurrentMode(UUID uuid) {
        HidePlayerDAO hidePlayerDAO = null;
        try {
            hidePlayerDAO = daoManager.getDAO().queryForId(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (hidePlayerDAO == null) {
            return 0;
        }
        return hidePlayerDAO.getMode();
    }

    @Override
    public void updateCurrentMode(UUID uuid, int mode) {
        HidePlayerDAO hidePlayerDAO = HidePlayerDAO.builder()
                .uuid(uuid)
                .mode(mode)
                .build();
        try {
            daoManager.getDAO().createOrUpdate(hidePlayerDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
