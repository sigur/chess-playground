package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class is {@code LimitAwareRouteStrategy} which connects coordinates via a predefined
 * distance, points are connected exactly as far apart as indicated in the {@code delta} value.
 *
 * <p>Through this class, the coordinates are defined as connected when they are exactly the
 * specified delta distance apart.
 *
 * <p>When the delta value connects a coordinate outside the limits, this coordinate is not valid.
 *
 * @author sigur
 * @see LimitAwareRouteStrategy
 */
public class DeltaRouteStrategy extends LimitAwareRouteStrategy {

  private final Coordinate delta;

  /**
   * Create a new {@code DeltaRouteStrategy} that connects the various coordinates that are within
   * its limits, and they are apart exactly in the {@code delta} value.
   *
   * @param width, maximum value for horizontal coordinate
   * @param height, maximum value for vertical coordinate
   * @param delta, distance to check
   */
  DeltaRouteStrategy(int width, int height, Coordinate delta) {
    super(width, height);
    this.delta = delta;
  }

  /**
   * Check if {@code target} point is the distance specified by the {@code delta) value
   * @param from, staring coordinate point
   * @param target, coordinate to check if it's connected
   * @return {@code true} when {@code target} matches the {@code delta} from {@code from},
   *  {@code false} when does not match or it's outside
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    int targetX = Math.abs(target.x() - from.x());
    int targetY = Math.abs(target.y() - from.y());
    final Coordinate destination = new Coordinate(targetX, targetY);
    return delta.equals(destination) && !from.equals(target) && verifyCoordinateIsInside(target);
  }

  /**
   * Searches for points connected to the specified coordinate with respect to the configured {@code
   * delta}. A coordinate is said to be connected if it reflects the function 'relation exists',
   * which means every calculated coordinate must be controlled by {@code from} coordinate, for each
   * item {@code i} the outcome of function {@code existsRelation(from, i)} is {@code true};
   *
   * @param from, starting coordinate to use as origin
   * @return empty set if there are no coordinates. Set of coordinates which matches the delta
   *     distance
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    addCoordinateIfRelated(from, -delta.x(), -delta.y(), answer);
    addCoordinateIfRelated(from, -delta.x(), delta.y(), answer);
    addCoordinateIfRelated(from, delta.x(), -delta.y(), answer);
    addCoordinateIfRelated(from, delta.x(), delta.y(), answer);

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
