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

  /**
   * Create a new {@code MultipleRouteStrategy} which is composed by the given strategies
   *
   * @param strategies, array containing the {@code RouteStrategy} that generates the current
   *     strategy. The behavior of the created instance is the union of the strategies provided as
   *     input
   */
  public MultipleRouteStrategy(RouteStrategy... strategies) {
    this.strategies = Arrays.asList(strategies);
  }

  /**
   * Delegate the control to the strategy list, when first of them is related to {@code target} no
   * further ones are verified. point is connected from a staring point
   *
   * @param from, staring point
   * @param target, coordinate to check if it's connected
   * @return {@code true} if at least one strategy is related, {@code false} otherwise
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    for (RouteStrategy current : strategies) {
      if (current.existsRelation(from, target)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Obtain all the available coordinates which are controlled by the strategies.
   *
   * @param from, starting coordinate to use as origin
   * @return {@code Set<Coordinate>} of all accessible ones, empty in case there are no related
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();
    for (RouteStrategy delegate : strategies) {
      answer.addAll(delegate.calculateAvailable(from));
    }
    return answer;
  }
}
