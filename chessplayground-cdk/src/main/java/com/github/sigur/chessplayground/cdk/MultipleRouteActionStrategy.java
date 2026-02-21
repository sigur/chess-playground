package com.github.sigur.chessplayground.cdk;

import java.util.*;

public class MultipleRouteActionStrategy implements RouteAction {
  private final Collection<RouteAction> routes;

  public MultipleRouteActionStrategy(RouteAction... routes) {
    this.routes = Arrays.asList(routes);
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return findAnyWhichCanControl(from, target).isPresent();
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();
    for (RouteAction delegate : routes) {
      answer.addAll(delegate.calculateAvailable(from));
    }
    return answer;
  }

  @Override
  public RouteActionOutcome execute(Coordinate from, Coordinate target) {
    final Optional<RouteAction> delegate = findAnyWhichCanControl(from, target);
    return delegate.map(e -> e.execute(from, target)).orElseGet(RouteActionOutcome::invalid);
  }

  private Optional<RouteAction> findAnyWhichCanControl(Coordinate from, Coordinate target) {
    return routes.stream().filter(i -> i.existsRelation(from, target)).findAny();
  }

}
