package de.chaos.mc.lobbysystem.utils.sichtbarkeitsutils;

import java.util.UUID;

public interface SichtbarkeitsInterface {
    public int getCurrentMode(UUID uuid);
    public void updateCurrentMode(UUID uuid, int mode);
}
