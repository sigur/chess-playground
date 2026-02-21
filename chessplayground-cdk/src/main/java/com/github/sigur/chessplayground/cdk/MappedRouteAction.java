package com.github.sigur.chessplayground.cdk;

import java.util.Set;

public class MappedRouteAction implements RouteAction {
  private final RouteStrategy link;
  private final RouteStrategy route;

  public MappedRouteAction(RouteStrategy link, RouteStrategy route) {
    this.link = link;
    this.route = route;
  }

  @Override
  public RouteActionOutcome execute(Coordinate from, Coordinate target) {
    final var answer = RouteActionOutcome.builder();

    final boolean related = existsRelation(from, target);
    answer.executed(related);
    if (related) {
      answer.position(target);
    }

    if (this.link.existsRelation(from, target)) {
      final Set<Coordinate> targets = route.calculateAvailable(target);
      answer.capture(targets);
    }

    return answer.build();
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return this.route.existsRelation(from, target);
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    return this.route.calculateAvailable(from);
  }
}
