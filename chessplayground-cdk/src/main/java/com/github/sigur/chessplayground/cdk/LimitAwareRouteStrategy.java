package com.github.sigur.chessplayground.cdk;

/**
 * A {@code LimitAwareRouteStrategy} is a type of {@link RouteStrategy} whose actions are confined
 * within defined borders.
 *
 * @author sigur
 */
abstract class LimitAwareRouteStrategy implements RouteStrategy {

  protected final Coordinate bottomLimit;
  protected final Coordinate topLimit;

  /**
   * Define the borders of the {@code LimitAwareRouteStrategy}. The passed values represent the
   * maximum values for coordinates
   *
   * @param width, maximum value for {@code Coordinate.x()}
   * @param height, maximum value for {@code Coordinate.y()}
   */
  LimitAwareRouteStrategy(int width, int height) {
    this.bottomLimit = new Coordinate(0, 0);
    this.topLimit = new Coordinate(width, height);
  }

  /**
   * Check if the specified point is inside the border. A point is inside when both {@code x} and
   * {@code y} are within limit.
   *
   * @param coordinate, point value to check
   * @return {@code true} if coordinate is inside, {@code false} otherwise
   */
  boolean verifyCoordinateIsInside(Coordinate coordinate) {
    return bottomLimit.isLowerOrEqualThan(coordinate) && topLimit.isGreaterOrEqualThan(coordinate);
  }

  protected int getWidth() {
    return topLimit.x();
  }

  protected int getHeight() {
    return topLimit.y();
  }
}
