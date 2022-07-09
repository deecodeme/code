package com.poc.code.psds.backtracking;

public class Sudoku {
    public void solveSudoku(char[][] board) {
        solveSudokuUtil(board);
    }

    private boolean solveSudokuUtil(char[][] board) {
        boolean allFilled = true;
        int row = -1;
        int col = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    allFilled = false;
                    row = i;
                    col = j;
                    break;
                }
            }
            if (!allFilled) {
                break;
            }
        }

        if (allFilled) {
            return true;
        }

        for (char k = '1'; k <= '9'; k++) {
            if (isSafe(board, row, col, k)) {
                board[row][col] = k;
                if (solveSudokuUtil(board)) {
                    return true;
                } else {
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }

    private boolean isSafe(char[][] board, int i, int j, char val) {
        //validate in column
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == val) {
                return false;
            }
        }

        //validate in row
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        //validate in sub box
        int iBox = i / 3;
        int jBox = j / 3;

        for (int row = iBox * 3; row < iBox * 3 + 3; row++) {
            for (int col = jBox * 3; col < jBox * 3 + 3; col++) {
                if (board[row][col] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku(board);
        for (char[] b : board) {
            System.out.println();
            for (char ch : b) {
                System.out.printf("%s ", ch);
            }
        }
    }
}
