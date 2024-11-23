package com.github.sigur.chessplayground.cdk;

import java.util.Set;

public interface Movement extends RouteStrategy {
  MovementOutcome execute(Coordinate from, Coordinate target);

  boolean canCapture(Coordinate from, Coordinate target);

  Set<Coordinate> calculatePossibleCaptures(Coordinate from);
}
