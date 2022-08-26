package com.poc.code.design.snakeLadder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameGameBoardImplTest {
    private GameBoardImpl gameBoard;

    @BeforeEach
    public void setUp() throws Exception {
        gameBoard = new GameBoardImpl(100);
        gameBoard.addStair(3, 100);
        gameBoard.addSnake(90, 2);
    }

    @Test
    public void moveTo() {
        Assertions.assertEquals(100, gameBoard.moveTo(0, 3));
        Assertions.assertEquals(2, gameBoard.moveTo(89, 1));
        Assertions.assertEquals(96, gameBoard.moveTo(96, 6));
    }

    @Test
    public void isAWin() {
        Assertions.assertTrue(gameBoard.isAWin(100));
        Assertions.assertFalse(gameBoard.isAWin(99));
    }
}