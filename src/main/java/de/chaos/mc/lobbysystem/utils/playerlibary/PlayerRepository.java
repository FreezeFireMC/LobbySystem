package de.chaos.mc.lobbysystem.utils.playerlibary;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.utils.daos.DAOManager;
import de.chaos.mc.serverapi.utils.daos.PlayerDAO;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageType;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerRepository implements PlayerInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<PlayerDAO, UUID> daoManager;

    public PlayerRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager(PlayerDAO.class, this.connectionSource);
    }
    @Override
    public void checkIfFirstJoin(UUID uuid) {
        PlayerDAO playerDAO = null;
        try {
            if (daoManager.getDAO().queryForId(uuid) == null) {
                playerDAO = PlayerDAO.builder()
                        .uuid(uuid)
                        .languageType(LanguageType.DE.getLanguageType())
                        .onlineTime(0)
                        .build();
                daoManager.getDAO().createOrUpdate(playerDAO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
