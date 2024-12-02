package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CoordinateTest {

  @Test
  void add() {
    final Coordinate source = new Coordinate(-3, -4);

    assertThat(source.add(new Coordinate(3, 4))).isEqualTo(new Coordinate(0, 0));

    assertThat(source.add(2, 2)).isEqualTo(new Coordinate(-1, -2));

    assertThat(source.add(-1, 2)).isEqualTo(new Coordinate(-4, -2));
  }

  @Test
  void subtract() {
    final Coordinate source = new Coordinate(3, 4);

    assertThat(source.subtract(new Coordinate(3, 4))).isEqualTo(new Coordinate(0, 0));

    assertThat(source.subtract(2, 2)).isEqualTo(new Coordinate(1, 2));

    assertThat(source.subtract(-2, -7)).isEqualTo(new Coordinate(5, 11));
  }

  @Test
  void abs() {
    final Coordinate source = new Coordinate(-7, -10);

    assertThat(source.abs()).isEqualTo(new Coordinate(7, 10));
  }

  @Test
  void verifyEqual() {
    final Coordinate actual = new Coordinate(1, 2);
    final Coordinate expected = new Coordinate(1, 2);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void compareTo() {
    final Coordinate actual = new Coordinate(1, 2);
    assertThat(actual.compareTo(new Coordinate(1, 2))).isEqualTo(0);
    assertThat(actual.compareTo(new Coordinate(0, 2))).isGreaterThanOrEqualTo(1);
    assertThat(actual.compareTo(new Coordinate(2, 0))).isLessThanOrEqualTo(-1);
  }

  @Test
  void isLowerOrEqualThan() {
    final Coordinate actual = new Coordinate(1, 2);
    assertThat(actual.isLowerOrEqualThan(new Coordinate(1, 2))).isTrue();
    assertThat(actual.isLowerOrEqualThan(new Coordinate(2, 0))).isTrue();
    assertThat(actual.isLowerOrEqualThan(new Coordinate(0, 2))).isFalse();
  }

  @Test
  void isGreaterOrEqualThan() {
    final Coordinate actual = new Coordinate(1, 2);
    assertThat(actual.isGreaterOrEqualThan(new Coordinate(1, 2))).isTrue();
    assertThat(actual.isGreaterOrEqualThan(new Coordinate(0, 2))).isTrue();
    assertThat(actual.isGreaterOrEqualThan(new Coordinate(2, 0))).isFalse();
  }
}
