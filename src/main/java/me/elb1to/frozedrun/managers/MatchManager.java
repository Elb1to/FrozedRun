package me.elb1to.frozedrun.managers;

import lombok.Data;
import lombok.Getter;
import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.chat.Color;

@Data
public class MatchManager {

    @Getter
    public static MatchManager instance;
    private MatchState matchState = MatchState.LOBBY;
    private String frozedRunPrefix = Color.translate(FrozedUHCRun.getInstance().getConfiguration("config").getString("PREFIXES.GAME"));
    private String tsInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.TS");
    private String webInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.WEB");
    private String ipInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.IP");
    private String storeInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.STORE");
    private String serverName = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.SERVER");
    private int maxPlayers = FrozedUHCRun.getInstance().getConfiguration("config").getInt("MAXIMUM-PLAYERS-PER-GAME");
    private int minPlayers = FrozedUHCRun.getInstance().getConfiguration("config").getInt("MINIMUM-PLAYERS-TO-START-GAME");
    private String rebootCommand = FrozedUHCRun.getInstance().getConfiguration("config").getString("REBOOT_COMMAND");

    public int getRequiredPlayersToJoin() {
        return getMinPlayers() - PlayerManager.getInstance().getLobbyPlayers().size();
    }
}
