package me.elb1to.frozedrun.managers;

import lombok.Data;
import lombok.Getter;
import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.utils.chat.Color;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

@Data
public class MatchManager {

    @Getter
    private FrozedUHCRun match = FrozedUHCRun.getInstance();
    public static MatchManager instance;
    private MatchState matchState = MatchState.LOBBY;
    private String gamePrefix = Color.translate(FrozedUHCRun.getInstance().getConfiguration("config").getString("PREFIXES.GAME"));
    private String tsInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.TS");
    private String webInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.WEB");
    private String ipInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.IP");
    private String storeInfo = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.STORE");
    private String serverName = FrozedUHCRun.getInstance().getConfiguration("config").getString("INFORMATIONS.SERVER");
    private int maxPlayers = FrozedUHCRun.getInstance().getConfiguration("config").getInt("MAXIMUM-PLAYERS-PER-GAME");
    private int minPlayers = FrozedUHCRun.getInstance().getConfiguration("config").getInt("MINIMUM-PLAYERS-TO-START-GAME");
    private String rebootCommand = FrozedUHCRun.getInstance().getConfiguration("config").getString("REBOOT_COMMAND");


    public void setScatterLocation(Player player, Location location) {
        match.getScatterLocation().put(player, location);
    }

    public void scatterPlayer(Player player, World world, int radius) {

        Random random = new Random();

        int x = random.nextInt(radius - 1);
        int z = random.nextInt(radius - 1);
        int y = Bukkit.getWorld(world.getName()).getHighestBlockYAt(x, z);

        Location location = new Location(Bukkit.getWorld(world.getName()), x, y ,z);

        player.teleport(location);
    }

    public int getRequiredPlayersToJoin() {
        return getMinPlayers() - PlayerManager.getInstance().getLobbyPlayers().size();
    }
}
