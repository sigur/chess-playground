package com.github.sigur.chessplayground.cdk;

import com.github.sigur.chessplayground.cdk.util.Guards;

public record BoundingBox(Coordinate bottom, Coordinate top) {
  public BoundingBox {
    Guards.argument(bottom.isLowerOrEqualThan(top), "Bottom should be lower or equal than top");
  }

  private static boolean isBetween(int value, int start, int end) {
    return start <= value && value <= end;
  }

  public boolean encloses(Coordinate coordinate) {
    return isBetween(coordinate.x(), bottom().x(), top().x())
        && isBetween(coordinate.y(), bottom().y(), top().y());
  }
}
