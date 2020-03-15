package me.elb1to.frozedrun.player;

import lombok.Data;
import me.elb1to.frozedrun.enums.PlayerState;

@Data
public class PlayerData {

    private PlayerState state = PlayerState.LOBBY;
}
