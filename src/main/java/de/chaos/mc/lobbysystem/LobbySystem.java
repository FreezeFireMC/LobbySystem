package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.query.In;
import de.chaos.mc.lobbysystem.commands.setLocation;
import de.chaos.mc.lobbysystem.commands.setSpawn;
import de.chaos.mc.lobbysystem.listeners.ClickListener;
import de.chaos.mc.lobbysystem.listeners.ConnectionListener;
import de.chaos.mc.lobbysystem.listeners.InventoryCklickListener;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.serverapi.api.ServerAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
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

        getCommand("setSpawn").setExecutor(new setSpawn());
        getCommand("setLocation").setExecutor(new setLocation());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new ClickListener(), this);
        pluginManager.registerEvents(new InventoryCklickListener(), this);
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
