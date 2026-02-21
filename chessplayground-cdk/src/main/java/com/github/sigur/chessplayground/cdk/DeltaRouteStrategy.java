package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

public class DeltaRouteStrategy extends LimitAwareRouteStrategy {

  private final Coordinate delta;

  /**
   * Define the borders of the {@code LimitAwareRouteStrategy}. The passed values represent the
   * maximum values for coordinates
   *
   * @param width
   * @param height
   */
  DeltaRouteStrategy(int width, int height, Coordinate delta) {
    super(width, height);
    this.delta = delta;
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final var destination = new Coordinate(target.x() - from.x(), target.y() - from.y()).abs();
    return delta.equals(destination) && super.existsRelation(from, target);
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    addCoordinateIfRelated(from, -delta.x(), -delta.y(), answer);
    addCoordinateIfRelated(from, -delta.x(), delta.y(), answer);
    addCoordinateIfRelated(from, delta.x(), -delta.y(), answer);
    addCoordinateIfRelated(from, delta.x(), delta.y(), answer);

    return answer;
  }

  private void addCoordinateIfRelated(Coordinate from, int x, int y, Set<Coordinate> target) {
    final Coordinate coordinate = from.add(x, y);
    if (existsRelation(from, coordinate)) {
      target.add(coordinate);
    }
  }
}
