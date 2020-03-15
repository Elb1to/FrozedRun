package me.elb1to.frozedrun.managers;

import lombok.Getter;
import me.elb1to.frozedrun.enums.PlayerState;
import me.elb1to.frozedrun.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerManager {

    @Getter
    public static PlayerManager instance;

    public PlayerManager() {
        instance = this;
    }

    public List<Player> getOnlinePlayers() {
        return new ArrayList<>(Utils.getOnlinePlayers());
    }

    public List<Player> getLobbyPlayers() {
        return Utils.getOnlinePlayers().stream().filter(player -> PlayerDataManager.getInstance().getByUUID(player.getUniqueId()) != null &&
                PlayerDataManager.getInstance().getByUUID(player.getUniqueId()).getState() == PlayerState.LOBBY).collect(Collectors.toList());
    }
}
