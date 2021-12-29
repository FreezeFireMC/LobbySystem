package de.chaos.mc.lobbysystem.utils.stringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permissions {
    SETLOCATION("FreezeFire.setLocation"),
    VIPPERMISSIONS("FreezeFire.fire"),
    SETHOLOGRAM("FreezeFire.setHolo");

    @Getter private String string;
}