package com.poc.code.design.snakeLadder;

import java.util.List;

public class GameFactory {
    public static Game createAGame(GameBoard gameBoard, Dice dice, List<Player> playerList) {
        int playersCount = 0;
        if (playerList != null) {
            playersCount = playerList.size();
        }
        if (playersCount == 2) {
            return new TwoPlayerGame(gameBoard, dice, playerList);
        } else if (playersCount > 2) {
            return new MultiplayerGame(gameBoard, dice, playerList);
        } else {
            return null;
        }
    }
}
