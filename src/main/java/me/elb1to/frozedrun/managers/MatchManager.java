package me.elb1to.frozedrun.managers;

import lombok.Getter;
import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.chat.Color;

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
    private int startCountdownValue = FrozedUHCRun.getInstance().getConfiguration("config").getInt("COUNTDOWNS.IN-SECONDS.START");
    private int prematchCountdownValue = FrozedUHCRun.getInstance().getConfiguration("config").getInt("COUNTDOWNS.IN-SECONDS.PRE-MATCH");
    private int pvpCountdownValue = FrozedUHCRun.getInstance().getConfiguration("config").getInt("COUNTDOWNS.IN-SECONDS.PVP-PROT");
    private int deathMatchCountdownValue = FrozedUHCRun.getInstance().getConfiguration("config").getInt("COUNTDOWNS.IN-MINUTES.DEATH-MATCH");
    private int rebootCountdownValue = FrozedUHCRun.getInstance().getConfiguration("config").getInt("COUNTDOWNS.IN-SECONDS.REBOOT");
    private int pointsPerKill = FrozedUHCRun.getInstance().getConfiguration("config").getInt("POINTS.PER-KILL");
    private int pointsPerWin = FrozedUHCRun.getInstance().getConfiguration("config").getInt("POINTS.PER-WIN");
    private String rebootCommand = FrozedUHCRun.getInstance().getConfiguration("config").getString("REBOOT_COMMAND");
}
