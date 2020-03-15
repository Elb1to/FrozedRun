package me.elb1to.frozedrun.scoreboard;

import me.elb1to.frozedrun.FrozedUHCRun;
import me.elb1to.frozedrun.enums.MatchState;
import me.elb1to.frozedrun.managers.MatchManager;
import me.elb1to.frozedrun.managers.PlayerDataManager;
import me.elb1to.frozedrun.managers.PlayerManager;
import me.elb1to.frozedrun.player.PlayerData;
import me.elb1to.frozedrun.utils.board.Board;
import me.elb1to.frozedrun.utils.board.BoardAdapter;
import me.elb1to.frozedrun.utils.chat.Color;
import me.elb1to.frozedrun.utils.chat.Symbols;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import net.minecraft.util.org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardLayout implements BoardAdapter {

    @Override
    public String getTitle(Player player) {
        String title = FrozedUHCRun.getInstance().getConfiguration("scoreboard").getString("title");
        title = title.replace("<I>", StringEscapeUtils.unescapeJava("\u2503"));
        return Color.translate(title);
    }

    @Override
    public List<String> getScoreboard(Player player, Board board) {
        return Color.translate(getPlayerScoreboard(player));
    }

    private List<String> getPlayerScoreboard(Player player) {
        List<String> board = new ArrayList<>();
        PlayerData data = PlayerDataManager.getInstance().getByUUID(player.getUniqueId());
        MatchManager matchManager = MatchManager.instance;
        PlayerManager playerManager = PlayerManager.getInstance();

        if (matchManager.getMatchState() == MatchState.LOBBY) {
            //if (PlayerManager.getInstance().getLobbyPlayers().size() < MatchManager.getInstance().getMinPlayers()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("lobby-scoreboard")) {
                    board.add(replace(string, player));
                }
            }/* else if (matchManager.getStartCountdown() != null && !matchManager.getStartCountdown().hasExpired()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("lobby-countdown-scoreboard")) {
                    board.add(replace(string, player));
                }
            }*/
        //}

        /*if (gameManager.getGameState() == GameState.PREMATCH) {
            for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("pre-match-scoreboard")) {
                board.add(replace(string, player));
            }
        }

        if (gameManager.getGameState() == GameState.INGAME) {
            if (gameManager.getPvpCountdown() != null && !gameManager.getPvpCountdown().hasExpired()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("pvp-countdown-scoreboard")) {
                    board.add(borderReplace(replace(string, player)));
                }
            } else if (gameManager.getFeastCountdown() != null && !gameManager.getFeastCountdown().hasExpired()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("feast-countdown-scoreboard")) {
                    board.add(borderReplace(replace(string, player)));
                }
            } else if (gameManager.getDeathMatchCountdown() != null && !gameManager.getDeathMatchCountdown().hasExpired()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("deathmatch-countdown-scoreboard")) {
                    board.add(borderReplace(replace(string, player)));
                }
            } else if (gameManager.getDeathMatchCountdown().hasExpired()) {
                for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("deathmatch-scoreboard")) {
                    board.add(borderReplace(replace(string, player)));
                }
            }
        }

        if (gameManager.getGameState() == GameState.ENDING) {
            for (String string : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getStringList("winner-scoreboard")) {
                board.add(replace(string, player));
            }
        }*/

        return board;
    }

    @Override
    public long getInterval() {
        return 2L;
    }

    @Override
    public void onScoreboardCreate(Player player, Scoreboard board) {

    }

    public String replace(String string, Player player) {
        PlayerData data = PlayerDataManager.getInstance().getByUUID(player.getUniqueId());
        MatchManager matchManager = MatchManager.instance;
        PlayerManager playerManager = PlayerManager.getInstance();
        return string
                .replaceAll("<server_name>", matchManager.getServerName())
                //.replaceAll("<spectator_chat_boolean>", (data.isSpecChat() ? FrozedUHCRun.getInstance().getConfiguration("scoreboard").getString("enabled") : FrozedUHCRun.getInstance().getConfiguration("scoreboard").getString("disabled")))
                .replaceAll("<require_players>", String.valueOf(matchManager.getRequiredPlayersToJoin()))
                /*.replaceAll("<prematch_players_size>", String.valueOf(playerManager.getPrematchPlayers().size()))
                .replaceAll("<game_players>", String.valueOf(playerManager.getGamePlayers().size()))*/
                .replaceAll("<max_players>", String.valueOf(matchManager.getMaxPlayers()))
                /*.replaceAll("<countdown_players>", String.valueOf(playerManager.getLobbyPlayers().size()))
                .replaceAll("<player_kills>", String.valueOf(data.getGameKills().getAmount()))*/
                .replaceAll("<player_ping>", String.valueOf(((CraftPlayer) player).getHandle().ping))
                .replaceAll("<server_ip>", matchManager.getIpInfo())
                /*.replaceAll("<winner>", gameManager.getWinner())
                .replaceAll("<winner_kills>", String.valueOf(gameManager.getWinnerKills()))*/

                /*.replaceAll("<game_time>", gameManager.getInstance().getGameRunnable() != null ? String.valueOf(gameManager.getInstance().getGameRunnable().getTime()) : "null")
                .replaceAll("<start_countdown>", gameManager.getStartCountdown() != null ? String.valueOf(gameManager.getStartCountdown().getTimeLeft()) : "null" )
                .replaceAll("<prematch_countdown>", gameManager.getPrematchCountdown() != null ? String.valueOf(gameManager.getPrematchCountdown().getTimeLeft()) : "null")
                .replaceAll("<enderpearl_countdown>", data.getEnderpearlCooldown() != null ? String.valueOf(data.getEnderpearlCooldown().getMiliSecondsLeft()) : "null")
                .replaceAll("<combat_countdown>", data.getCombatCooldown() != null ? String.valueOf(data.getCombatCooldown().getMiliSecondsLeft()) : "null")
                .replaceAll("<pvp_protection_countdown>", gameManager.getPvpCountdown() != null ? String.valueOf(gameManager.getPvpCountdown().getTimeLeft()) : "null")
                .replaceAll("<feast_countdown>", gameManager.getFeastCountdown() != null ? String.valueOf(gameManager.getFeastCountdown().getTimeLeft()) : "null")
                .replaceAll("<deathmatch_countdown>", gameManager.getDeathMatchCountdown() != null ? String.valueOf(gameManager.getDeathMatchCountdown().getTimeLeft()) : "null")
                .replaceAll("<reboot_countdown>", gameManager.getRebootCountdown() != null ? String.valueOf(gameManager.getRebootCountdown().getTimeLeft()) : "null")*/

                .replaceAll("<arrow_left>", Symbols.ARROW_LEFT)
                .replaceAll("<arrow_right>", Symbols.ARROW_RIGHT);
    }

    /*public String borderReplace(String string) {
        return string.replaceAll("<border_size>", String.valueOf(BorderManager.getInstance().getBorder().getSize()))
                .replaceAll("<border_info>", BorderManager.getInstance().getBorderInfo());
    }*/

    @Override
    public void preLoop() {}
}