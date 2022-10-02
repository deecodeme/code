package com.poc.code.practices.design.snakeLadder;

import java.util.List;

public class MultiplayerGame extends Game {
    public MultiplayerGame(GameBoard gameBoard, Dice dice, List<Player> players) {
        super(gameBoard, dice, players);
    }

    @Override
    public void start() {
        int playerPlaying = this.players.size();
        while (playerPlaying > 1) {
            for (Player player : this.players) {
                int diceVal = dice.play();
                int currentPos = player.getCurrentPos();
                int nextPos = board.moveTo(currentPos, diceVal);
                player.setCurrentPos(nextPos);
                System.out.printf("\n%s rolled a %s and moved from %s to %s", player.getName(), diceVal, currentPos,
                        nextPos);
                if (board.isAWin(nextPos)) {
                    playerPlaying--;
                }
            }
        }
    }
}
