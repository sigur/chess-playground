package com.github.sigur.chessplayground.cdk;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MultipleRouteStrategy implements RouteStrategy {
  private final Collection<RouteStrategy> strategies;

  public MultipleRouteStrategy(RouteStrategy... strategies) {
    this.strategies = Arrays.asList(strategies);
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return strategies.stream().anyMatch(i -> i.existsRelation(from, target));
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();
    for (RouteStrategy delegate : strategies) {
      answer.addAll(delegate.calculateAvailable(from));
    }
    return answer;
  }
}
