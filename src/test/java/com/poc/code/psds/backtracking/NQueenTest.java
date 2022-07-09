package com.poc.code.psds.backtracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NQueenTest {
    private NQueen nQueen;

    @BeforeEach
    public void setUp() {
        nQueen = new NQueen();
    }

    @Test
    public void totalNQueens() {
        Assertions.assertEquals(1, nQueen.totalNQueens(1));
        Assertions.assertEquals(2, nQueen.totalNQueens(4));
        Assertions.assertEquals(10, nQueen.totalNQueens(5));
        Assertions.assertEquals(4, nQueen.totalNQueens(6));
        Assertions.assertEquals(40, nQueen.totalNQueens(7));
        Assertions.assertEquals(92, nQueen.totalNQueens(8));
        Assertions.assertEquals(352, nQueen.totalNQueens(9));
    }
}