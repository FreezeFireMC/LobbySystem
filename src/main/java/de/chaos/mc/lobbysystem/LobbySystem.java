package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.commands.SetLocationCommand;
import de.chaos.mc.lobbysystem.listeners.*;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsRepository;
import de.chaos.mc.serverapi.api.ServerAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
    private ScoreboardManager scoreboardManager;

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

        scoreboardManager = new ScoreboardManager(this);

        getCommand("setLocation").setExecutor(new SetLocationCommand());

        registerEvent(new ConnectionListener());
        registerEvent(new ClickListener());
        registerEvent(new DoubleJumpListener());
        registerEvent(new EventListener());

    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getLobbySystem());
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
