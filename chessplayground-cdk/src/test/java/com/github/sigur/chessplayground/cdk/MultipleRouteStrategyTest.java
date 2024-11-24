package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MultipleRouteStrategyTest {

  @Test
  void calculateAvailable() {
    final int width = 7;
    final int height = 7;
    final Direction direction = Direction.ASCENT;
    final MultipleRouteStrategy actual =
        new MultipleRouteStrategy(
            new DirectionalDeltaRouteStrategy(width, height, new Coordinate(0, 1), direction),
            new DirectionalDeltaRouteStrategy(width, height, new Coordinate(1, 1), direction),
            new DirectionalDeltaRouteStrategy(width, height, new Coordinate(0, 2), direction));

    assertThat(actual.calculateAvailable(new Coordinate(2, 2)))
        .contains(
            new Coordinate(2, 3), new Coordinate(2, 4), new Coordinate(3, 3), new Coordinate(1, 3));
  }
}
