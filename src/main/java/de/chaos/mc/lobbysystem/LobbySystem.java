package de.chaos.mc.lobbysystem;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.chaos.mc.lobbysystem.commands.SetLocationCommand;
import de.chaos.mc.lobbysystem.commands.setLanguageHologramCommand;
import de.chaos.mc.lobbysystem.listeners.*;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.FreezeFightProfileInv;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.UpdateFreezeFightInvSortingRepository;
import de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite.UpdateFreezeFightInventorySortingInterface;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.InventoryClickListener;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.InventoryCloseListener;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.MLGRushProfileInv;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.UpdateMLGRushInvSortingRepository;
import de.chaos.mc.lobbysystem.utils.invsortings.mlgrushinventorysorting.ormlite.UpdateMLGRushInventorySortingInterface;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.LobbyStringsLoader;
import de.chaos.mc.lobbysystem.utils.lobbylanguagelibary.PlayerLobbyLanguage;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationInterface;
import de.chaos.mc.lobbysystem.utils.locationlibary.LocationRepository;
import de.chaos.mc.lobbysystem.utils.megaUtils.menu.MenuFactory;
import de.chaos.mc.lobbysystem.utils.placeholders.LanguageSwitchHoloPlaceholder;
import de.chaos.mc.lobbysystem.utils.playerlibary.PlayerInterface;
import de.chaos.mc.lobbysystem.utils.playerlibary.PlayerRepository;
import de.chaos.mc.lobbysystem.utils.profile.ProfileInventorys;
import de.chaos.mc.lobbysystem.utils.scorebaord.ScoreboardManager;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsInterface;
import de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils.SichtbarkeitsRepository;
import de.chaos.mc.serverapi.api.ServerAPI;
import de.chaos.mc.serverapi.utils.playerlibary.languageLibary.LanguageInterface;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

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
    @Getter private UpdateMLGRushInventorySortingInterface updateInventorySortingInterface;
    private UpdateMLGRushInvSortingRepository updateInvSortingRepository;
    @Getter private MLGRushProfileInv mlgRushProfileInv;
    @Getter private ProfileInventorys profileInventorys;
    @Getter private FreezeFightProfileInv freezeFightProfileInv;
    private LanguageInterface languageInterface;
    private PlayerInterface playerInterface;
    @Getter private static HashMap<UUID, PlayerLobbyLanguage> onlinePlayers;
    @Getter private LobbyStringsLoader stringsLoader;
    @Getter private UpdateFreezeFightInventorySortingInterface freezeFightInventorySortingInterface;
    @Getter @Setter
    private static ArmorStand languageHolo;


    @Override
    public void onEnable() {
        instance = this;
        serverAPI = new ServerAPI();
        onlinePlayers = new HashMap<>();
        languageInterface = serverAPI.getLanguageInterface();
        stringsLoader = new LobbyStringsLoader(languageInterface);
        connectionSource = serverAPI.getConnectionSource();
        locationRepository = new LocationRepository();
        locationInterface = locationRepository;
        sichtbarkeitsRepository = new SichtbarkeitsRepository(connectionSource);
        sichtbarkeitsIntreface = sichtbarkeitsRepository;
        new LanguageSwitchHoloPlaceholder().register();
        menuFactory = MenuFactory.register(this);

        scoreboardManager = new ScoreboardManager(this, languageInterface);

        updateInvSortingRepository = new UpdateMLGRushInvSortingRepository(connectionSource);
        updateInventorySortingInterface = updateInvSortingRepository;

        mlgRushProfileInv = new MLGRushProfileInv(updateInventorySortingInterface, languageInterface);
        freezeFightInventorySortingInterface = new UpdateFreezeFightInvSortingRepository(connectionSource);
        freezeFightProfileInv = new FreezeFightProfileInv(freezeFightInventorySortingInterface, languageInterface);

        profileInventorys = new ProfileInventorys(menuFactory, mlgRushProfileInv, languageInterface, scoreboardManager, freezeFightProfileInv);

        playerInterface = new PlayerRepository(connectionSource);

        getCommand("setLocation").setExecutor(new SetLocationCommand());
        getCommand("setLanguageHolo").setExecutor( new setLanguageHologramCommand());

        registerEvent(new ConnectionListener(playerInterface, scoreboardManager));
        registerEvent(new ClickListener(profileInventorys));
        registerEvent(new DoubleJumpListener());
        registerEvent(new EventListener());
        registerEvent(new PlayerMoveListener());
        registerEvent(new EntityInteractEntitiyListener());
        registerEvent(new InventoryClickListener(updateInventorySortingInterface));
        registerEvent(new InventoryCloseListener(freezeFightInventorySortingInterface, updateInventorySortingInterface, languageInterface));



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

    public static LobbySystem getLobbySystem() {
        return instance;
    }
}