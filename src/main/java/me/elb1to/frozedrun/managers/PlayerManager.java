package me.elb1to.frozedrun.managers;

import lombok.Getter;

public class PlayerManager {

    @Getter
    public static PlayerManager instance;

    public PlayerManager() {
        instance = this;
    }


}
