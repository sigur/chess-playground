package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeltaRouteStrategyTest {

  @Test
  void existsRelation() {}

  @Test
  void calculateAvailable() {
    final DeltaRouteStrategy strategy = new DeltaRouteStrategy(7, 7, new Coordinate(0, 3));

    assertThat(strategy.calculateAvailable(new Coordinate(0, 0)))
        .containsExactly(new Coordinate(0, 3));

    assertThat(strategy.calculateAvailable(new Coordinate(6, 7)))
            .containsExactly(new Coordinate(6, 4));
  }

  @Test
  void calculateAvailableLikeKnight() {
    final DeltaRouteStrategy xGreater = new DeltaRouteStrategy(7, 7, new Coordinate(2, 1));
    assertThat(xGreater.calculateAvailable(new Coordinate(3, 4)))
        .containsExactly(
            new Coordinate(1, 3), new Coordinate(1, 5), new Coordinate(5, 3), new Coordinate(5, 5));

    final DeltaRouteStrategy yGreater = new DeltaRouteStrategy(7, 7, new Coordinate(1, 2));
    assertThat(yGreater.calculateAvailable(new Coordinate(3, 2)))
        .containsExactly(
            new Coordinate(2, 0), new Coordinate(2, 4), new Coordinate(4, 0), new Coordinate(4, 4));
  }
}
