package com.poc.code.practices.design.chess;

public class Pawn extends Piece {
    public Pawn(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    Position move(MoveRequest moveRequest) throws IllegalAccessException {
        Position targetPosition;
        switch (moveRequest.getDirection()) {
            case UP -> targetPosition = new Position(moveRequest.getCurrPosition().getRow() + 1, moveRequest.getCurrPosition().getCol() + 1);
            case DOWN -> targetPosition = new Position(moveRequest.getCurrPosition().getRow() - 1, moveRequest.getCurrPosition().getCol() - 1);
            default -> throw new IllegalAccessException("Illegal move");
        }
        return targetPosition;
    }
}
