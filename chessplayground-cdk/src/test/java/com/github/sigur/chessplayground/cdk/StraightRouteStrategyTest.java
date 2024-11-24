package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StraightRouteStrategyTest {

  @Test
  void existsRelation() {
    final StraightRouteStrategy strategy = new StraightRouteStrategy(5, 5);
    assertThat(strategy.existsRelation(new Coordinate(3, 2), new Coordinate(5, 2))).isTrue();
    assertThat(strategy.existsRelation(new Coordinate(3, 2), new Coordinate(3, 0))).isTrue();
    assertThat(strategy.existsRelation(new Coordinate(0, 0), new Coordinate(0, 5))).isTrue();
  }

  @Test
  void notExistsRelation() {
    final StraightRouteStrategy strategy = new StraightRouteStrategy(7, 7);
    assertThat(strategy.existsRelation(new Coordinate(7, 7), new Coordinate(5, 2))).isFalse();
    assertThat(strategy.existsRelation(new Coordinate(7, 7), new Coordinate(-7, 0))).isFalse();
    assertThat(strategy.existsRelation(new Coordinate(4, 2), new Coordinate(4, 8))).isFalse();
  }

  @Test
  void calculateAvailable() {
    final StraightRouteStrategy strategy = new StraightRouteStrategy(6, 6);
    assertThat(strategy.calculateAvailable(new Coordinate(3, 1)))
        .containsExactly(
            new Coordinate(0, 1),
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(3, 0),
            new Coordinate(3, 2),
            new Coordinate(3, 3),
            new Coordinate(3, 4),
            new Coordinate(3, 5),
            new Coordinate(3, 6),
            new Coordinate(4, 1),
            new Coordinate(5, 1),
            new Coordinate(6, 1));
  }
}
