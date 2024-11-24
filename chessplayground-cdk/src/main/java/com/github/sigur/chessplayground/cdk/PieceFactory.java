package com.github.sigur.chessplayground.cdk;

public interface PieceFactory {
    Piece createPawn(Coordinate position, Direction direction);
}
