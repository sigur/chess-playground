package com.github.sigur.chessplayground.cdk;

import java.util.Set;

public class CaptureAndMovementAdapter implements Movement {
  private final RouteStrategy capture;
  private final RouteStrategy movement;

  public CaptureAndMovementAdapter(RouteStrategy capture, RouteStrategy movement) {
    this.capture = capture;
    this.movement = movement;
  }

  @Override
  public MovementOutcome execute(Coordinate from, Coordinate target) {
    final var result = MovementOutcome.builder();

    boolean controllable = existsRelation(from, target);
    result.executed(controllable);
    if (controllable) {
      result.position(target);
    }

    if (capture.existsRelation(from, target)) {
      final Set<Coordinate> targets = calculatePossibleCaptures(target);
      result.capture(targets);
    }

    return result.build();
  }

  @Override
  public boolean canCapture(Coordinate from, Coordinate target) {
    return this.capture.existsRelation(from, target);
  }

  @Override
  public Set<Coordinate> calculatePossibleCaptures(Coordinate from) {
    return this.capture.calculateAvailable(from);
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
