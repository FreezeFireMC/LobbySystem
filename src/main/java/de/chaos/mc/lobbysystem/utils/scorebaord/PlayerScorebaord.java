package de.chaos.mc.lobbysystem.utils.scorebaord;

import lombok.Builder;
import lombok.Data;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

@Data
@Builder
public class PlayerScorebaord {
    public UUID uuid;
    public Scoreboard scoreboard;
    public Team coins;
    public Team visibility;
}
