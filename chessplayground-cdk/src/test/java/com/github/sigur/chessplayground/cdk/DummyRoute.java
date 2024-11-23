package com.github.sigur.chessplayground.cdk;

import java.util.Collections;
import java.util.Set;

public class DummyRoute implements RouteStrategy {
  private Set<Coordinate> coordinates = Collections.emptySet();
  private boolean control;

  public void setCoordinates(Set<Coordinate> coordinates) {
    this.coordinates = coordinates;
  }


  public void setControl(boolean control) {
    this.control = control;
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return control;
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    return coordinates;
  }
}
