package com.poc.code.design.chess;

public abstract class Piece {
    private PieceType pieceType;

    abstract Position move(MoveRequest moveRequest) throws IllegalAccessException;

    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
