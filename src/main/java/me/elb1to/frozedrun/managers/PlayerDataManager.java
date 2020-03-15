package me.elb1to.frozedrun.managers;

import lombok.Getter;
import me.elb1to.frozedrun.player.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    @Getter
    public static PlayerDataManager instance;
    private Map<UUID, PlayerData> playerDatas = new HashMap<>();

    public PlayerDataManager() {
        instance = this;
    }

    public PlayerData getByUUID(UUID uuid) {
        return playerDatas.getOrDefault(uuid, null);
    }
}
