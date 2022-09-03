package com.poc.code.design.snakeLadder;

public interface GameBoard {

    void addSnake(int head, int tail);

    void addStair(int bottom, int top);

    int moveTo(int currentPos, int forward);

    boolean isAWin(int pos);
}
