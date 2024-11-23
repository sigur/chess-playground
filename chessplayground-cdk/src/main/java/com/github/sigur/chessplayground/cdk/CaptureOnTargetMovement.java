package com.github.sigur.chessplayground.cdk;

import java.util.Set;

public class CaptureOnTargetMovement implements Movement {
  private final RouteStrategy movement;

  public CaptureOnTargetMovement(RouteStrategy movement) {
    this.movement = movement;
  }

  @Override
  public MovementOutcome execute(Coordinate from, Coordinate target) {
    final MovementOutcome.MovementOutcomeBuilder result = MovementOutcome.builder();

    boolean controllable = existsRelation(from, target);
    result.executed(controllable);
    if (controllable) {
      result.position(target).capture(target);
    }

    return result.build();
  }

  @Override
  public boolean canCapture(Coordinate from, Coordinate target) {
    return existsRelation(from, target);
  }

  @Override
  public Set<Coordinate> calculatePossibleCaptures(Coordinate from) {
    return this.movement.calculateAvailable(from);
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
