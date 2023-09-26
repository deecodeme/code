package com.poc.code.practices.design.snakeLadder;

public class GameBoardImpl implements GameBoard {
    private int end;
    private Integer[] snakes;
    private Integer[] stairs;

    public GameBoardImpl(int end) {
        this.end = end;
        this.snakes = new Integer[end + 1];
        this.stairs = new Integer[end + 1];
    }

    public void addSnake(int head, int tail) {
        System.out.printf("\nSnake added, head: %s, tail %s", head, tail);
        this.snakes[head] = tail;
    }

    public void addStair(int bottom, int top) {
        this.stairs[bottom] = top;
        System.out.printf("\nStair added, bottom: %s, up %s", bottom, top);
    }

    public int moveTo(int currentPos, int forward) {
        int next = currentPos + forward;
        if (next > end) {
            return currentPos;
        } else if (snakes[next] != null) {
            return snakes[next];
        } else if (stairs[next] != null) {
            return stairs[next];
        } else {
            return next;
        }
    }

    public boolean isAWin(int pos) {
        return pos == end;
    }
}
