package com.github.sigur.chessplayground.cdk;

import java.util.Set;

/**
 * {@code RouteStrategy} is an interface which represents the algorithms related to how coordinates
 * collaborate and act each other. Ideologically it represents the behavior of how an action from
 * one point to another should behave.
 *
 * <p>Implementing this interface means defining which coordinates can be reached from a given
 * point.
 *
 * @author sigur
 */
public interface RouteStrategy {
  /**
   * Verify if {@code target} point is connected from a staring point
   *
   * @param from, staring point
   * @param target, coordinate to check if it's connected
   * @return {@code true} if it's related, {@code false} otherwise
   */
  boolean existsRelation(Coordinate from, Coordinate target);

  /**
   * Retrieve all the related coordinates which are connected from the specified parameter.
   *
   * @param from, starting coordinate to use as origin
   * @return {@code Set<Coordinate>} of all accessible ones, empty in case there are no related
   */
  Set<Coordinate> calculateAvailable(Coordinate from);
}
