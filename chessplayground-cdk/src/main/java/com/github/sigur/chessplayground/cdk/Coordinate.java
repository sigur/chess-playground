package com.github.sigur.chessplayground.cdk;

public record Coordinate(int x, int y) implements Comparable<Coordinate> {

  public Coordinate add(Coordinate addend) {
    return add(addend.x, addend.y);
  }

  public Coordinate add(int x, int y) {
    return new Coordinate(this.x + x, this.y + y);
  }

  public Coordinate subtract(Coordinate subtrahend) {
    return subtract(subtrahend.x, subtrahend.y);
  }

  public Coordinate subtract(int x, int y) {
    return new Coordinate(this.x - x, this.y - y);
  }

  public Coordinate abs() {
    return new Coordinate(Math.abs(x), Math.abs(y));
  }

  public Coordinate atMaximum(Coordinate point) {
    return new Coordinate(Math.max(x, point.x()), Math.max(y, point.y()));
  }

  public Coordinate atMinimum(Coordinate point) {
    return new Coordinate(Math.min(x, point.x()), Math.min(y, point.y()));
  }

  @Override
  public int compareTo(Coordinate o) {
    return x == o.x ? Integer.compare(y, o.y) : Integer.compare(x, o.x);
  }

  public boolean isLowerOrEqualThan(Coordinate o) {
    return compareTo(o) < 1;
  }

  public boolean isGreaterOrEqualThan(Coordinate o) {
    return compareTo(o) > -1;
  }
}
