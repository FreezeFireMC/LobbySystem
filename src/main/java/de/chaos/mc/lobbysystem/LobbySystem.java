package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.commands.SetLocationCommand;
import de.chaos.mc.lobbysystem.listeners.ClickListener;
import de.chaos.mc.lobbysystem.listeners.ConnectionListener;
import de.chaos.mc.lobbysystem.listeners.DoubleJumpListener;
import de.chaos.mc.lobbysystem.listeners.InventoryClickListener;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsRepository;
import de.chaos.mc.serverapi.api.ServerAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {
    private static ServerAPI serverAPI;
    private static LobbySystem instance;
    private JdbcPooledConnectionSource connectionSource;
    private LocationRepository locationRepository;
    private static LocationInterface locationInterface;
    public static MenuFactory menuFactory;
    public static SichtbarkeitsInterface sichtbarkeitsIntreface;
    private SichtbarkeitsRepository sichtbarkeitsRepository;

    @Override
    public void onEnable() {
        instance = this;
        serverAPI = new ServerAPI();
        connectionSource = serverAPI.getConnectionSource();
        locationRepository = new LocationRepository(connectionSource);
        locationInterface = locationRepository;
        sichtbarkeitsRepository = new SichtbarkeitsRepository(connectionSource);
        sichtbarkeitsIntreface = sichtbarkeitsRepository;
        menuFactory = MenuFactory.register(this);

        getCommand("setLocation").setExecutor(new SetLocationCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new ClickListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new DoubleJumpListener(), this);
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

    public static LobbySystem getLobbySystem() {
        return instance;
    }
}
