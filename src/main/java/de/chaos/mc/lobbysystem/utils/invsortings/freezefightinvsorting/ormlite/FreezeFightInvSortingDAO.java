package de.chaos.mc.lobbysystem.utils.invsortings.freezefightinvsorting.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DatabaseTable(tableName = "FreezeFightInvSorting")
@Builder
public class FreezeFightInvSortingDAO {
    public static final String UUID_FIELD = "UUID";
    public static final String SWORD_SLOT = "SWORLD_SLOT";
    public static final String BOW_FIELD = "BOW_SLOT";
    public static final String EGG_FIELD = "EGG_SLOT";
    public static final String ARROW_FIELD = "ARROW_SLOT";

    @DatabaseField(id = true, columnName = UUID_FIELD)
    public UUID uuid;

    @DatabaseField(columnName = SWORD_SLOT)
    public int swordSlot;

    @DatabaseField(columnName = BOW_FIELD)
    public int bowSlot;

    @DatabaseField(columnName = EGG_FIELD)
    public int eggSlot;

    @DatabaseField(columnName = ARROW_FIELD)
    public int arrowSlot;
}