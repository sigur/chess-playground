package com.github.sigur.chessplayground.cdk;

import java.util.*;

/**
 * A {@code MultipleRouteStrategy} is a type of {@link RouteStrategy} whose behavior is composed by
 * the union of other strategies.
 *
 * @author sigur
 */
public class MultipleRouteStrategy implements RouteStrategy {
  private final Iterable<RouteStrategy> strategies;

  public MultipleRouteStrategy(RouteStrategy... strategies) {
    this.strategies = Arrays.asList(strategies);
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final Iterator<RouteStrategy> iterator = strategies.iterator();
    boolean notExist;
    for (notExist = iterator.hasNext(); !iterator.next().existsRelation(from, target); ) {
      if (notExist) {
        System.out.println("keep searching"); // todo(sigur) tracelog
      }
    }

    return !notExist;
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
