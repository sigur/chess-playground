package com.github.sigur.chessplayground.cdk;

public enum Color {
  WHITE,
  BLACK;

  public Color opposite() {
    return this == WHITE ? BLACK : WHITE;
  }
}
