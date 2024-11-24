package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DiagonalRouteStrategyTest {

  @Test
  void existsRelation() {
    final DiagonalRouteStrategy strategy = new DiagonalRouteStrategy(3, 3);

    assertThat(strategy.existsRelation(new Coordinate(1, 0), new Coordinate(2, 1))).isTrue();
  }

  @Test
  void notExistsRelation() {
    final DiagonalRouteStrategy strategy = new DiagonalRouteStrategy(3, 3);

    assertThat(strategy.existsRelation(new Coordinate(1, 0), new Coordinate(3, 1))).isFalse();
  }

  @Test
  void invalidCoordinates() {
    final DiagonalRouteStrategy strategy = new DiagonalRouteStrategy(3, 3);

    assertThat(strategy.existsRelation(new Coordinate(1, 0), new Coordinate(4, 3))).isFalse();
    assertThat(strategy.existsRelation(new Coordinate(1, 0), new Coordinate(1, 0))).isFalse();
  }

  @Test
  void calculateAvailableDiagonalCoordinates() {
    final DiagonalRouteStrategy strategy = new DiagonalRouteStrategy(7, 7);

    assertThat(strategy.calculateAvailable(new Coordinate(3, 2)))
        .containsExactly(
            new Coordinate(0, 5),
            new Coordinate(1, 0),
            new Coordinate(1, 4),
            new Coordinate(2, 1),
            new Coordinate(2, 3),
            new Coordinate(4, 1),
            new Coordinate(4, 3),
            new Coordinate(5, 0),
            new Coordinate(5, 4),
            new Coordinate(6, 5),
            new Coordinate(7, 6));
  }
}
