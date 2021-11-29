package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.serverapi.api.ServerAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {
    private static ServerAPI serverAPI;
    private JdbcPooledConnectionSource connectionSource;
    private LocationRepository locationRepository;
    private static LocationInterface locationInterface;
    public static MenuFactory menuFactory;

    @Override
    public void onEnable() {
        serverAPI = new ServerAPI();
        connectionSource = serverAPI.getConnectionSource();
        locationRepository = new LocationRepository(connectionSource);
        locationInterface = locationRepository;
        menuFactory = MenuFactory.register(this);
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
    }

    public static ServerAPI getServerAPI() {
        return serverAPI;
    }

    public static LocationInterface getLocationInterface() {
        return locationInterface;
    }
}
