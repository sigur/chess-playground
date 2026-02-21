package com.github.sigur.chessplayground.cdk;

import java.util.Set;

public class DelegationRouteAction implements RouteAction {
  private final RouteStrategy route;

  public DelegationRouteAction(RouteStrategy route) {
    this.route = route;
  }

  @Override
  public RouteActionOutcome execute(Coordinate from, Coordinate target) {
    final RouteActionOutcome.MovementOutcomeBuilder result = RouteActionOutcome.builder();

    boolean controllable = existsRelation(from, target);
    result.executed(controllable);
    if (controllable) {
      result.position(target).capture(target);
    }

    return result.build();
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
