package com.github.sigur.chessplayground.cdk;

import java.util.Collections;
import java.util.Set;

public class CapturelessMovement implements Movement {
  private final RouteStrategy movement;

  public CapturelessMovement(RouteStrategy movement) {
    this.movement = movement;
  }

  @Override
  public MovementOutcome execute(Coordinate from, Coordinate target) {
    final MovementOutcome.MovementOutcomeBuilder result = MovementOutcome.builder();

    boolean controllable = existsRelation(from, target);
    result.executed(controllable);
    if (controllable) {
      result.position(target);
    }

    return result.build();
  }

  @Override
  public boolean canCapture(Coordinate from, Coordinate target) {
    return false;
  }

  @Override
  public Set<Coordinate> calculatePossibleCaptures(Coordinate from) {
    return Collections.emptySet();
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return this.movement.existsRelation(from, target);
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    return this.movement.calculateAvailable(from);
  }
}
