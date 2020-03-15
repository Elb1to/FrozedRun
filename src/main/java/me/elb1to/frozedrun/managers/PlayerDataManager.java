package me.elb1to.frozedrun.managers;

import lombok.Getter;

public class PlayerDataManager {

    @Getter
    public static PlayerDataManager instance;

    public PlayerDataManager() {
        instance = this;
    }
}
