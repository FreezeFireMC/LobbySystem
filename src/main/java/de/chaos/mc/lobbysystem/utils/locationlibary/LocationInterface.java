package de.chaos.mc.lobbysystem.utils.locationlibary;

import org.bukkit.Location;

import java.util.List;

public interface LocationInterface {
    List<String> getAllMaps();
    Location addLocation(String LocationName, Location location);
    Location getLocation(String LocationName);
    Location delLocation(String LocationName);
}
