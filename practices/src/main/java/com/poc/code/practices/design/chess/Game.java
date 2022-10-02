package com.poc.code.practices.design.chess;

public class Game {
    private GameBoard gameBoard;
    private Player winner;
    private Player player1;
    private Player player2;
    private boolean isOver;
    private Player rollingPlayer;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public Player getRollingPlayer() {
        return rollingPlayer;
    }

    public void setRollingPlayer(Player rollingPlayer) {
        this.rollingPlayer = rollingPlayer;
    }
}
