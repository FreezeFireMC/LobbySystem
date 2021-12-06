package de.chaos.mc.lobbysystem.utils.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DatabaseTable(tableName = "LobbyLocations")
public class LocationDAO {
    public static final String NAME_FIELD  = "NAME";
    public static final String WORLD_FIELD  = "WORLD";
    public static final String X_FIELD  = "X";
    public static final String Y_FIELD  = "Y";
    public static final String Z_FIELD = "Z";
    public static final String PITCH_FIELD = "PITCH";
    public static final String YAW_FIELD = "YAW";

    @DatabaseField(id = true, columnName = NAME_FIELD)
    public String Name;
    @DatabaseField(columnName = WORLD_FIELD)
    public String World;
    @DatabaseField(columnName = X_FIELD)
    public long x;
    @DatabaseField(columnName = Y_FIELD)
    public long y;
    @DatabaseField(columnName = Z_FIELD)
    public long z;
    @DatabaseField(columnName = PITCH_FIELD)
    public long pitch;
    @DatabaseField(columnName = YAW_FIELD)
    public long yaw;
}