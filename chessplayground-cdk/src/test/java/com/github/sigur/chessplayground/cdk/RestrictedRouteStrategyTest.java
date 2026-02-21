package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestrictedRouteStrategyTest {

  @Test
  void existsRelation() {}

  @Test
  void calculateAvailable() {}

  @Test
  void calculateAvailableLikeKing() {
    final RouteStrategy strategyMoves =
        RouteStrategyDecorator.decorate(
            new StraightRouteStrategy(7, 7), new DiagonalRouteStrategy(7, 7));

    final RestrictedRouteStrategy strategy =
        new RestrictedRouteStrategy(new Coordinate(1, 1), strategyMoves);

    assertThat(strategy.calculateAvailable(new Coordinate(3, 2)))
        .containsExactlyInAnyOrder(
            new Coordinate(2, 1),
            new Coordinate(2, 2),
            new Coordinate(2, 3),
            new Coordinate(3, 1),
            new Coordinate(3, 3),
            new Coordinate(4, 1),
            new Coordinate(4, 2),
            new Coordinate(4, 3));
  }
}
