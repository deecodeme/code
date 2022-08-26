package com.poc.code.ps.backtracking;

public class NQueen {
    private int sol;

    public int totalNQueens(int n) {
        if (n == 1) {
            return 1;
        }
        sol = 0;
        boolean[][] board = new boolean[n][n];
        totalNQueensBacktracking(board, n, 0);
        return sol;
    }

    public void totalNQueensBacktracking(boolean[][] board, int n, int index) {
        if (index == n) {
            sol++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if (isSafe(board, n, index, j)) {
                board[index][j] = true;
                totalNQueensBacktracking(board, n, index + 1);
                board[index][j] = false;
            }
        }
    }

    private boolean isSafe(boolean[][] board, int n, int i, int j) {
        //check in the same column
        for (int row = 0; row < i; row++) {
            if (board[row][j] == true) {
                return false;
            }
        }

        //check diagonally left
        int iTemp = i - 1;
        int jTemp = j - 1;
        while (isSafeIndex(n, iTemp, jTemp)) {
            if (board[iTemp][jTemp] == true) {
                return false;
            }
            iTemp--;
            jTemp--;
        }

        //check diagonally right
        iTemp = i - 1;
        jTemp = j + 1;
        while (isSafeIndex(n, iTemp, jTemp)) {
            if (board[iTemp][jTemp] == true) {
                return false;
            }
            iTemp--;
            jTemp++;
        }
        return true;
    }

    private boolean isSafeIndex(int n, int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n) {
            return true;
        }
        return false;
    }
}
