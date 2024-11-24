package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

/**Ø
 * This class is {@code LimitAwareRouteStrategy} which connects coordinates through an oriented
 * delta distance.
 *
 * <p>Through this class, the coordinates are defined as connected when they are exactly the
 * specified delta distance apart.
 *
 * <p>When the delta value connects a coordinate outside the limits, it's replaced with the most
 * suitable coordinate within those limits.
 *
 * @author sigur
 * @see LimitAwareRouteStrategy
 * @see Direction
 */
public class DirectionalDeltaRouteStrategy extends LimitAwareRouteStrategy {
  protected final Coordinate delta;
  protected final Direction direction;

  /**
   * Create a new {@code DirectionalDeltaRouteStrategy} that connects the various coordinates that
   * are within its limits, and they are apart exactly in the direction.
   *
   * @param width, max value for {@code x} coordinate
   * @param height, max value for {@code y} coordinate
   * @param delta, distance length
   * @param direction, direction in which to measure the distance between the coordinates
   * @see LimitAwareRouteStrategy
   */
  public DirectionalDeltaRouteStrategy(
      int width, int height, Coordinate delta, Direction direction) {
    super(width, height);
    this.delta = delta;
    this.direction = direction;
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
    int targetY = direction.apply(target.y() - from.y());
    final Coordinate destination = new Coordinate(targetX, targetY);
    return !from.equals(target) && verifyCoordinateIsInside(target) && delta.equals(destination);
  }

  /**
   * Searches for points connected to the specified coordinate with respect to the configured delta
   * radius. A coordinate is said to be connected if it reflects the function 'relation exists',
   * which means every calculated coordinate must be controlled by {@code from} coordinate, for each
   * item {@code i} the outcome of function {@code existsRelation(from, i)} is {@code true};
   *
   * @param from, starting coordinate to use as origin
   * @return empty set if there are no coordinates. Coordinates output matches the delta toward the
   *     direction
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    final Coordinate min = calculatePossibleLowerLimit(from);
    final Coordinate max = calculatePossibleUpperLimit(from);
    Coordinate tmp;
    for (int row = min.x(); row <= max.x(); row++) {
      for (int column = min.y(); column <= max.y(); column++) {
        tmp = new Coordinate(row, column);
        if (existsRelation(from, tmp)) {
          answer.add(tmp);
        }
      }
    }

    return answer;
  }

  /**
   * @param point, coordinate to check against the upper limit
   * @return the minimum value between the {@code point} and its upper limit
   */
  private Coordinate calculatePossibleUpperLimit(Coordinate point) {
    final int x = Math.min(topLimit.x(), point.x() + delta.x());
    final int y = Math.min(topLimit.y(), point.y() + direction.apply(delta.y()));
    return new Coordinate(x, y);
  }

  /**
   * @param point, coordinate to check against the bottom limit
   * @return the maximum value between the {@code point} and its bottom limit
   */
  private Coordinate calculatePossibleLowerLimit(Coordinate point) {
    final int x = Math.max(bottomLimit.x(), point.x() - delta.x());
    final int y = Math.max(bottomLimit.y(), point.y() + direction.apply(delta.y()));
    return new Coordinate(x, y);
  }

  /**
   * @return distance to match coordinates
   */
  public Coordinate getDelta() {
    return delta;
  }

  /**
   * @return configured direction where this current strategy looks to find connected coordinantes
   */
  public Direction getDirection() {
    return direction;
  }
}
