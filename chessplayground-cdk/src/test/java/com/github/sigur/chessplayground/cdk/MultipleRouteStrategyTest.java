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

  @Test
  void existRelation() {
    final MultipleRouteStrategy actual =
        new MultipleRouteStrategy(new StraightRouteStrategy(7, 7), new DiagonalRouteStrategy(7, 7));
    assertThat(actual.existsRelation(new Coordinate(3, 3), new Coordinate(7, 7))).isTrue();

    assertThat(actual.existsRelation(new Coordinate(1, 7), new Coordinate(1, 0))).isTrue();
  }

  @Test
  void notExistRelation() {
    final MultipleRouteStrategy actual =
        new MultipleRouteStrategy(new StraightRouteStrategy(7, 7), new DiagonalRouteStrategy(7, 7));
    assertThat(actual.existsRelation(new Coordinate(3, 3), new Coordinate(2, 7))).isFalse();
    assertThat(actual.existsRelation(new Coordinate(1, 7), new Coordinate(-1, 7))).isFalse();
    assertThat(actual.existsRelation(new Coordinate(7, 7), new Coordinate(0, 3))).isFalse();
  }

  @Test
  void calculateAvailableLikeQueen() {
    final MultipleRouteStrategy actual =
        new MultipleRouteStrategy(new StraightRouteStrategy(7, 7), new DiagonalRouteStrategy(7, 7));

    assertThat(actual.calculateAvailable(new Coordinate(4, 4)))
        .containsExactly(
            new Coordinate(0, 0),
            new Coordinate(0, 4),
            new Coordinate(1, 1),
            new Coordinate(1, 4),
            new Coordinate(1, 7),
            new Coordinate(2, 2),
            new Coordinate(2, 4),
            new Coordinate(2, 6),
            new Coordinate(3, 3),
            new Coordinate(3, 4),
            new Coordinate(3, 5),
            new Coordinate(4, 0),
            new Coordinate(4, 1),
            new Coordinate(4, 2),
            new Coordinate(4, 3),
            new Coordinate(4, 5),
            new Coordinate(4, 6),
            new Coordinate(4, 7),
            new Coordinate(5, 3),
            new Coordinate(5, 4),
            new Coordinate(5, 5),
            new Coordinate(6, 2),
            new Coordinate(6, 4),
            new Coordinate(6, 6),
            new Coordinate(7, 1),
            new Coordinate(7, 4),
            new Coordinate(7, 7));
  }
}
