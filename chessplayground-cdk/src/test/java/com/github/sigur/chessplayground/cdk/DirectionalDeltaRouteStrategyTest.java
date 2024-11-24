package com.github.sigur.chessplayground.cdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionalDeltaRouteStrategyTest {

  @Test
  void availableCells() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(0, 1), Direction.ASCENT);

    Assertions.assertThat(strategy.calculateAvailable(new Coordinate(4, 5)))
        .contains(new Coordinate(4, 6));
  }

  @Test
  void availableCells2() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(0, 1), Direction.DESCENT);

    Assertions.assertThat(strategy.calculateAvailable(new Coordinate(4, 5)))
        .contains(new Coordinate(4, 4));
  }

  @Test
  void availableCells3() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(1, 1), Direction.ASCENT);

    Assertions.assertThat(strategy.calculateAvailable(new Coordinate(4, 5)))
        .contains(new Coordinate(5, 6), new Coordinate(3, 6));
  }

  @Test
  void availableCells4() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(1, 1), Direction.DESCENT);

    Assertions.assertThat(strategy.calculateAvailable(new Coordinate(4, 5)))
        .contains(new Coordinate(3, 4), new Coordinate(5, 4));
  }

  @Test
  void availableCells5() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(1, 1), Direction.DESCENT);

    Assertions.assertThat(strategy.calculateAvailable(new Coordinate(0, 0))).isEmpty();
  }

  @Test
  void test() {
    final DirectionalDeltaRouteStrategy strategy =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(1, 1), Direction.DESCENT);

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(-1, 0)))
        .withFailMessage("%s should be outside", new Coordinate(-1, 0))
        .isFalse();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(0, -1)))
        .withFailMessage("%s should be outside", new Coordinate(0, -1))
        .isFalse();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(0, 0)))
        .withFailMessage("%s should be inside", new Coordinate(0, 0))
        .isTrue();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(0, 7)))
        .withFailMessage("%s should be inside", new Coordinate(0, 7))
        .isTrue();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(0, 8)))
        .withFailMessage("%s should be outside", new Coordinate(0, 8))
        .isFalse();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(7, 8)))
        .withFailMessage("%s should be outside", new Coordinate(7, 8))
        .isFalse();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(7, 7)))
        .withFailMessage("%s should be inside", new Coordinate(7, 7))
        .isTrue();

    Assertions.assertThat(strategy.verifyCoordinateIsInside(new Coordinate(8, 7)))
        .withFailMessage("%s should be outside", new Coordinate(8, 7))
        .isFalse();
  }
}
