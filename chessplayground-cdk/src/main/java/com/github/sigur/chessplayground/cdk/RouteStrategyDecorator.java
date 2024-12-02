package com.github.sigur.chessplayground.cdk;

import java.util.Optional;
import java.util.Set;

public class RouteStrategyDecorator implements RouteStrategy {
  private final Optional<RouteStrategy> wrapped;
  private final RouteStrategy delegate;

  private RouteStrategyDecorator(RouteStrategy wrapped, RouteStrategy delegate) {
    this.wrapped = Optional.ofNullable(wrapped);
    this.delegate = delegate;
  }

  public static RouteStrategyDecorator decorate(RouteStrategy current, RouteStrategy decorator) {
    return new RouteStrategyDecorator(decorator, current);
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return this.delegate.existsRelation(from, target)
        || this.wrapped.map(e -> e.existsRelation(from, target)).orElse(Boolean.FALSE);
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = this.delegate.calculateAvailable(from);
    this.wrapped.ifPresent(e -> answer.addAll(e.calculateAvailable(from)));
    return answer;
  }
}
