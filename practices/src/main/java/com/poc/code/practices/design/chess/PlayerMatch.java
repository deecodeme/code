package com.poc.code.practices.design.chess;

public class PlayerMatch {
    private Player player1;
    private Player player2;

    public PlayerMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String toString() {
        return "PlayerMatch{" +
            "player1=" + player1 +
            ", player2=" + player2 +
            '}';
    }
}
