package me.elb1to.frozedrun.enums;

public enum MatchState {

    LOBBY(true),
    INGAME(false),
    INPVP(false),
    ENDING(false);

    private boolean canJoin;
    private static MatchState currentState;

    MatchState(boolean canJoin) {
        this.canJoin = canJoin;
    }

    public boolean canJoin() {
        return canJoin;
    }

    public static void setState(MatchState state) {
        MatchState.currentState = state;
    }

    public static boolean isState(MatchState state) {
        return MatchState.currentState == state;
    }

    public static MatchState getState() {
        return currentState;
    }
}
