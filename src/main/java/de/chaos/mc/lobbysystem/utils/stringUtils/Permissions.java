package de.chaos.mc.lobbysystem.utils.stringUtils;

public enum Permissions {
    SETSPAWN("LobbySystem.setSpawn"),
    SETLOCATION("LobbySystem.setLocation");

    public String string;
    private Permissions(String permission) {
        this.string = permission;
    }
}
