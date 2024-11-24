package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class is {@code LimitAwareRouteStrategy} which connects coordinates through horizontal or
 * vertical routes, where only one of the two types is considered (no both together). The strategy
 * does not valid diagonal routes.
 *
 * @author sigur
 * @see LimitAwareRouteStrategy
 */
public class StraightRouteStrategy extends LimitAwareRouteStrategy {
  /**
   * Define the borders of the {@code LimitAwareRouteStrategy}. The passed values represent the
   * maximum values for coordinates
   *
   * @param width, maximum value for horizontal coordinate
   * @param height, maximum value for vertical coordinate
   * @see Coordinate
   */
  public StraightRouteStrategy(int width, int height) {
    super(width, height);
  }

  /**
   * Checks whether the two coordinates belong to the same line, horizontal or vertical.
   *
   * <p>Two coordinates {@code a} and {@code b} are connected when just one coordinate differs. <br>
   * For example:
   *
   * <ul>
   *   <li>{@code a = (3,1)}, {@code b = (3,6)} are connected because {@code a.x = b.x} -
   *   <li>{@code a = (4,5)}{@code b = (0,5)} are connected because {@code a.y = b.y}
   *   <li>{@code a = (4,5)}, {@code b = (0,3)} are not connected because both point are not equal
   *       {@code a.x = b.x} {@code a.y != b.y}
   * </ul>
   *
   * @param from, is the origin coordinate (a)
   * @param target, is the destination coordinate (b)
   * @return When {@code target} coordinate is valid, it returns {@code true} if it's on same
   *     horizontal/vertical line; otherwise it returns {@code false}
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final int x = Math.abs(from.x() - target.x());
    final int y = Math.abs(from.y() - target.y());
    return !from.equals(target)
        && verifyCoordinateIsInside(target)
        && (x != y && (x == 0 || y == 0));
  }

  /**
   * Searches all horizontal coordinates and all vertical coordinates using the specified origin
   * point.
   *
   * @param from, starting coordinate to use as origin
   * @return empty set if there are no coordinates.
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    final int limit = Math.max(getWidth(), getHeight());

    for (int index = bottomLimit.x(); index <= limit; index++) {
      addCoordinateIfRelated(from, index, 0, answer);
      addCoordinateIfRelated(from, -index, 0, answer);
      addCoordinateIfRelated(from, 0, index, answer);
      addCoordinateIfRelated(from, 0, -index, answer);
    }

    return answer;
  }

  private void addCoordinateIfRelated(Coordinate from, int x, int y, Set<Coordinate> target) {
    final Coordinate coordinate = from.add(x, y);
    if (existsRelation(from, coordinate)) {
      target.add(coordinate);
    }
  }
}
