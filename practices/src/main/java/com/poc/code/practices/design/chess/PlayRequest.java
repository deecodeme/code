package com.poc.code.practices.design.chess;

import java.time.Instant;

public class PlayRequest {
    private Player player;
    private Level level;
    private Instant requestedAt;

    public PlayRequest(Player player, Level level, Instant requestedAt) {
        this.player = player;
        this.level = level;
        this.requestedAt = requestedAt;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }

    @Override
    public String toString() {
        return "PlayRequest{" +
                "player=" + player +
                ", level=" + level +
                ", requestedAt=" + requestedAt +
                '}';
    }
}
