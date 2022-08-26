package com.poc.code.design.chess;

public interface GameService {
    Game create(Player player1, Player player2);

    boolean move(Game game, Player player, MoveRequest moveRequest);
}
