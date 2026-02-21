package com.github.sigur.chessplayground.cdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RouteStrategyDecoratorTest {
  @Test
  void behaveLikeKing() {
    final RouteStrategyDecorator decorator =
        RouteStrategyDecorator.decorate(
            new StraightRouteStrategy(7, 7), new DiagonalRouteStrategy(7, 7));
    Assertions.assertThat(decorator.existsRelation(new Coordinate(3, 3), new Coordinate(7, 7)))
        .isTrue();
  }
}
