package de.chaos.mc.lobbysystem.utils.stringUtils;

public enum Permissions {
    SETLOCATION("LobbySystem.setLocation"),
    SICHTBARKEITVIP("LobbySystem.VIP");

    public String string;
    private Permissions(String permission) {
        this.string = permission;
    }
}
