package com.github.sigur.chessplayground.cdk;


public interface RouteAction extends RouteStrategy {
  RouteActionOutcome execute(Coordinate from, Coordinate target);
}
