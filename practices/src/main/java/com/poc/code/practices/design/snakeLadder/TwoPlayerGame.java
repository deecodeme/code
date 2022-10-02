package com.poc.code.practices.design.snakeLadder;

import java.util.List;

public class TwoPlayerGame extends Game {
    public TwoPlayerGame(GameBoard board, Dice dice, List<Player> players) {
        super(board, dice, players);
    }

    @Override
    public void start() {
        while (true) {
            for (Player player : players) {
                int diceVal = dice.play();
                int currentPos = player.getCurrentPos();
                int nextPos = board.moveTo(currentPos, diceVal);
                player.setCurrentPos(nextPos);
                System.out.printf("\n%s rolled a %s and moved from %s to %s", player.getName(), diceVal, currentPos,
                        nextPos);
                if (board.isAWin(nextPos)) {
                    System.out.printf("\n%s won the game", player.getName());
                    return;
                }
            }
        }
    }
}
