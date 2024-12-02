package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A {@code RestrictedRouteStrategy} is a type of {@link RouteStrategy} that deals with limiting the
 * connection between {@code coordinates} to a specific area within defined borders.
 *
 * @author sigur
 * @see RouteStrategy
 */
public class RestrictedRouteStrategy implements RouteStrategy {
  private final Coordinate limit;
  private final RouteStrategy delegate;

  /**
   * Create a new {@code RestrictedRouteStrategy} instance which applies the specified {@code limit}
   * to the passed {@code strategy}
   *
   * @param limit, restriction borders to apply to the strategy
   * @param delegate, strategy on which to apply the restriction
   */
  public RestrictedRouteStrategy(Coordinate limit, RouteStrategy delegate) {
    this.limit = limit;
    this.delegate = delegate;
  }

  /**
   * Checks, despite restrictions being applied, whether the indicated {@code target} point is
   * connected to the originating {@code from} point.The limits are calculates as difference between
   * {@code target} and {@code from}
   *
   * @param from, source coordinate point to check
   * @param target, coordinate point to check
   * @return {@code true} when delegate strategy returns {@code true} and it's still valid inside
   *     the border limits that has been applied, {@code false} otherwise
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final Coordinate spaces = target.subtract(from).abs();
    return spaces.isLowerOrEqualThan(limit) && delegate.existsRelation(from, target);
  }

  /**
   * Filter the available coordinates, which have been retrieved through {@code from} by the
   * delegated strategy, to the configured limit
   *
   * @param from, source coordinate
   * @return empty set if there are no coordinates
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    return delegate.calculateAvailable(from).stream()
        .filter(f -> existsRelation(from, f))
        .collect(Collectors.toSet());
  }
}
