package com.github.sigur.chessplayground.engine;

import com.github.sigur.chessplayground.cdk.Coordinate;
import com.github.sigur.chessplayground.cdk.Direction;
import com.github.sigur.chessplayground.cdk.Piece;

public interface PieceFactory {
    Piece createPawn(Coordinate position, Direction direction);
}
