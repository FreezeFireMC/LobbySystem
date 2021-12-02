package de.chaos.mc.lobbysystem.utils.daos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DatabaseTable(tableName = "LobbySpielerSichtbarkeit")
public class HidePlayerDAO {
    public static final String UUID_FIELD  = "UUID";
    public static final String MODE_FIELD  = "CurrentStatus";

    @DatabaseField(id = true, columnName = UUID_FIELD)
    public UUID uuid;
    @DatabaseField(columnName = MODE_FIELD)
    public int mode;
}
