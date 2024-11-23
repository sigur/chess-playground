package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EnPassantRouteStrategyTest {

  @Test
  void ascent() {
    var strategy = new EnPassantRouteStrategy(5, 5, new Coordinate(0, 2), Direction.ASCENT);

    assertThat(strategy.existsRelation(new Coordinate(3, 3), new Coordinate(2, 4)))
        .withFailMessage("%s should not be available because it is outside", new Coordinate(2, 6))
        .isFalse();
    assertThat(strategy.existsRelation(new Coordinate(1, 1), new Coordinate(0, 2)))
        .withFailMessage("%s should be valid", new Coordinate(0, 4))
        .isTrue();
    assertThat(strategy.calculateAvailable(new Coordinate(3, 3)))
        .containsExactly(new Coordinate(3, 5));
  }

  @Test
  void descent() {
    var strategy = new EnPassantRouteStrategy(5, 5, new Coordinate(0, 2), Direction.DESCENT);

    assertThat(strategy.existsRelation(new Coordinate(3, 3), new Coordinate(4, 4)))
        .withFailMessage("%s should be available because it is inside", new Coordinate(2, 4))
        .isTrue();
    assertThat(strategy.existsRelation(new Coordinate(1, 1), new Coordinate(0, 1)))
        .withFailMessage("%s should not be valid because it is outside", new Coordinate(0, -1))
        .isFalse();
    assertThat(strategy.calculateAvailable(new Coordinate(3, 3)))
        .containsExactly(new Coordinate(3, 1));
  }
}
