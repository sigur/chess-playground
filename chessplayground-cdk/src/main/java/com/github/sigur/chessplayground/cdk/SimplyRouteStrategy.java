package com.github.sigur.chessplayground.cdk;

import java.util.Collections;
import java.util.Set;

public class SimplyRouteStrategy implements RouteStrategy {
  private Set<Coordinate> coordinates = Collections.emptySet();

  public void setCoordinates(Set<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return coordinates.contains(target);
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    return Collections.unmodifiableSet(coordinates);
  }
}
