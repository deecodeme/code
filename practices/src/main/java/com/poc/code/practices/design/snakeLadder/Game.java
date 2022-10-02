package com.poc.code.practices.design.snakeLadder;

import java.util.List;

public abstract class Game {
    protected GameBoard board;
    protected List<Player> players;
    protected Dice dice;

    public Game(GameBoard board, Dice dice, List<Player> players) {
        this.board = board;
        this.players = players;
        this.dice = dice;
    }

    abstract void start();

    void addPlayer(String player) {
        this.players.add(new PlayerImpl(player));
    }
}
