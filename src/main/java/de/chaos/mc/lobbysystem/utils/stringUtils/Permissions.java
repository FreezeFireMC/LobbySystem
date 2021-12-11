package de.chaos.mc.lobbysystem.utils.stringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permissions {
    SETLOCATION("FreezeFire.setLocation"),
    VIPPERMISSIONS("FreezeFire.fire");

    @Getter private String string;
}