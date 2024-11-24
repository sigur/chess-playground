package com.github.sigur.chessplayground.cdk;

// todo(sigur) - Maybe this could be generated so the 'worth' value could be parametrized
// todo(sigur) - Maybe this could be the factory for the pieces
public enum PieceName {
  BISHOP(3),
  KING(10),
  KNIGHT(3),
  PAWN(1),
  QUEEN(9),
  ROOK(5);

  public final int value;

  PieceName(int value) {
    this.value = value;
  }
}
