package de.chaos.mc.lobbysystem.utils.locationlibary;

import de.chaos.mc.lobbysystem.LobbySystem;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class LocationConfigHandler {

    @Getter private File locationsFile;
    @Getter private YamlConfiguration locationConfig;

    public void loadConfig() {
        locationsFile = new File(LobbySystem.getLobbySystem().getDataFolder(), "locations.yml");
        locationConfig = YamlConfiguration.loadConfiguration(locationsFile);

        if (!LobbySystem.getLobbySystem().getDataFolder().exists()) {
            LobbySystem.getLobbySystem().getDataFolder().mkdir();
        }

        if (!locationsFile.exists()) {
            try {
                locationsFile.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        saveLocationFile();
    }


    public void saveMap(String name, Location location) {
        locationConfig.set(name, location);
        saveLocationFile();
    }

    public Location readLocation(String name) {
        if (locationsFile.length() == 0) {
            return null;
        }

        Location location = locationConfig.getSerializable(name, Location.class);
        return location;
    }

    public void saveLocationFile() {
        try {
            if (!locationsFile.exists()) locationsFile.createNewFile();
            locationConfig.save(locationsFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}