package com.poc.code.practices.design.chess;

public class MoveRequest {
    Position currPosition;
    Direction direction;
    int steps;

    public MoveRequest(Position currPosition, Direction direction, int steps) {
        this.currPosition = currPosition;
        this.direction = direction;
        this.steps = steps;
    }

    public Position getCurrPosition() {
        return currPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }
}
