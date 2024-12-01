package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class is {@code LimitAwareRouteStrategy} which connects coordinates through diagonal routes.
 *
 * @author sigur
 * @see LimitAwareRouteStrategy
 */
public class DiagonalRouteStrategy extends LimitAwareRouteStrategy {
  /**
   * Define the borders of the {@code LimitAwareRouteStrategy}. The passed values represent the
   * maximum values for coordinates
   *
   * @param width, maximum value for horizontal coordinate
   * @param height, maximum value for vertical coordinate
   */
  public DiagonalRouteStrategy(int width, int height) {
    super(width, height);
  }

  /**
   * Checks whether the two coordinates belong to the same diagonal line.
   *
   * <p>Two coordinates {@code a} and {@code b} are on same diagonal when {@code | a.x - b.x| = |a.y
   * - b.y|}.
   *
   * @param from, is the origin coordinate (a)
   * @param target, is the destination coordinate (b)
   * @return When {@code target} coordinate is valid, it returns {@code true} if it's on same
   *     diagonal of {@code from}; otherwise it returns {@code false}
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final int x = Math.abs(from.x() - target.x());
    final int y = Math.abs(from.y() - target.y());
    return x == y && verifyCoordinateIsInside(target) && !from.equals(target);
  }

  /**
   * Searches for all diagonals, in which the ratio is valid {@code | a.x - b.x| = |a.y - * b.y|},
   * that pass through the described point.
   *
   * @param from, starting coordinate to use as origin
   * @return empty set if there are no coordinates.
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    final int limit = Math.max(getWidth(), getHeight());

    for (int index = bottomLimit.x(); index <= limit; index++) {
      addCoordinateIfRelated(from, index, index, answer);
      addCoordinateIfRelated(from, -index, index, answer);
      addCoordinateIfRelated(from, index, -index, answer);
      addCoordinateIfRelated(from, -index, -index, answer);
    }

    return answer;
  }

  /**
   * Insert the coordinate {@code c(x,y)} into {@code target} set if {@code existsRelation(from, c)}
   * is {@code true}
   *
   * @param from, origin coordinate to check
   * @param x, value for the coordinate to check
   * @param y, value fo the coordinate to check
   * @param target, set if coordinate is related with {@code from}
   */
  private void addCoordinateIfRelated(Coordinate from, int x, int y, Set<Coordinate> target) {
    final Coordinate coordinate = from.add(x, y);
    if (existsRelation(from, coordinate)) {
      target.add(coordinate);
    }
  }
}
