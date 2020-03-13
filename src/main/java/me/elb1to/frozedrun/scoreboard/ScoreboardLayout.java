package me.elb1to.frozedrun.scoreboard;

import me.elb1to.frozedrun.utils.board.Board;
import me.elb1to.frozedrun.utils.board.BoardAdapter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardLayout implements BoardAdapter {

    @Override
    public String getTitle(Player player) {
        return null;
    }

    @Override
    public List<String> getScoreboard(Player player, Board board) {
        return null;
    }

    @Override
    public long getInterval() {
        return 0;
    }

    @Override
    public void onScoreboardCreate(Player player, Scoreboard board) {

    }

    @Override
    public void preLoop() {

    }
}
