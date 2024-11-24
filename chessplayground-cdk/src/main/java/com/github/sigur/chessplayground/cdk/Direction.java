package com.github.sigur.chessplayground.cdk;

public enum Direction {
  ASCENT(1),
  DESCENT(-1);

  private final int factor;

  Direction(int factor) {
    this.factor = factor;
  }

  Direction opposite() {
    return this == ASCENT ? DESCENT : ASCENT;
  }

  int apply(int value) {
    return factor * value;
  }
}
