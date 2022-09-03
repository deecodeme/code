package com.poc.code.design.snakeLadder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        GameBoard gameBoard = new GameBoardImpl(100);
        Dice dice = new DiceImpl();
        List<Player> playerList = new ArrayList<>();

        gameBoard.addSnake(62, 5);
        gameBoard.addSnake(33, 6);
        gameBoard.addSnake(49, 9);
        gameBoard.addSnake(88, 16);
        gameBoard.addSnake(41, 20);
        gameBoard.addSnake(56, 53);
        gameBoard.addSnake(98, 64);
        gameBoard.addSnake(93, 73);
        gameBoard.addSnake(95, 75);

        gameBoard.addStair(2, 37);
        gameBoard.addStair(27, 46);
        gameBoard.addStair(10, 32);
        gameBoard.addStair(51, 68);
        gameBoard.addStair(61, 79);
        gameBoard.addStair(65, 84);
        gameBoard.addStair(71, 91);
        gameBoard.addStair(81, 100);

        playerList.add(new PlayerImpl("Gaurav"));
        playerList.add(new PlayerImpl("Sagar"));
        game = GameFactory.createAGame(gameBoard, dice, playerList);
    }

    @Test
    public void playTest() {
        game.start();
    }
}