package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.commands.SetLocationCommand;
import de.chaos.mc.lobbysystem.listeners.*;
import de.chaos.mc.lobbysystem.utils.LobbyInventorys;
import de.chaos.mc.lobbysystem.utils.inventorylibary.InventoryClickListener;
import de.chaos.mc.lobbysystem.utils.inventorylibary.MLGRushProfileInv;
import de.chaos.mc.lobbysystem.utils.inventorylibary.ormlite.UpdateInvSortingRepository;
import de.chaos.mc.lobbysystem.utils.inventorylibary.ormlite.UpdateInventorySortingInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.lobbysystem.utils.profile.ProfileInventorys;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsRepository;
import de.chaos.mc.lobbysystem.utils.worldutils.RedStoneLampLiter;
import de.chaos.mc.serverapi.api.ServerAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {
    private static ServerAPI serverAPI;
    private static LobbySystem instance;
    private JdbcPooledConnectionSource connectionSource;
    private LocationRepository locationRepository;
    @Getter private LocationInterface locationInterface;
    public static MenuFactory menuFactory;
    public static SichtbarkeitsInterface sichtbarkeitsIntreface;
    private SichtbarkeitsRepository sichtbarkeitsRepository;
    private ScoreboardManager scoreboardManager;
    private RedStoneLampLiter redStoneLampLiter;
    @Getter private UpdateInventorySortingInterface updateInventorySortingInterface;
    private UpdateInvSortingRepository updateInvSortingRepository;
    private MLGRushProfileInv mlgRushProfileInv;
    private ProfileInventorys profileInventorys;

    @Override
    public void onEnable() {
        instance = this;
        serverAPI = new ServerAPI();
        connectionSource = serverAPI.getConnectionSource();
        locationRepository = new LocationRepository();
        locationInterface = locationRepository;
        sichtbarkeitsRepository = new SichtbarkeitsRepository(connectionSource);
        sichtbarkeitsIntreface = sichtbarkeitsRepository;
        menuFactory = MenuFactory.register(this);

        scoreboardManager = new ScoreboardManager(this);

        updateInvSortingRepository = new UpdateInvSortingRepository(connectionSource);
        updateInventorySortingInterface = updateInvSortingRepository;

        mlgRushProfileInv = new MLGRushProfileInv(updateInventorySortingInterface, menuFactory);

        profileInventorys = new ProfileInventorys(menuFactory, mlgRushProfileInv);

        getCommand("setLocation").setExecutor(new SetLocationCommand());

        registerEvent(new ConnectionListener());
        registerEvent(new ClickListener(profileInventorys));
        registerEvent(new DoubleJumpListener());
        registerEvent(new EventListener());
        registerEvent(new PlayerInteractListener());
        registerEvent(new PlayerMoveListener());
        registerEvent(new InventoryClickListener(updateInventorySortingInterface));
        redStoneLampLiter = new RedStoneLampLiter(Bukkit.getWorld("world"), this);
    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getLobbySystem());
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
        redStoneLampLiter.getRunnable().cancel();
    }

    public static ServerAPI getServerAPI() {
        return serverAPI;
    }

    public static LobbySystem getLobbySystem() {
        return instance;
    }
}
