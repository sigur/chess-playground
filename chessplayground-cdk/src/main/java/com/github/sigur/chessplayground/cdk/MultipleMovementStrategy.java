package com.github.sigur.chessplayground.cdk;

import java.util.*;

public class MultipleMovementStrategy implements Movement {
  private final Collection<Movement> strategies;

  public MultipleMovementStrategy(Movement... strategies) {
    this.strategies = Arrays.asList(strategies);
  }

  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    return findAnyWhichCanControl(from, target).isPresent();
  }

  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();
    for (Movement delegate : strategies) {
      answer.addAll(delegate.calculateAvailable(from));
    }
    return answer;
  }

  @Override
  public MovementOutcome execute(Coordinate from, Coordinate target) {
    final Optional<Movement> delegate = findAnyWhichCanControl(from, target);
    return delegate.map(e -> e.execute(from, target)).orElseGet(MovementOutcome::invalid);
  }

  @Override
  public boolean canCapture(Coordinate from, Coordinate target) {
    return findAnyWhichCanCapture(from, target).isPresent();
  }

  @Override
  public Set<Coordinate> calculatePossibleCaptures(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();
    for (Movement delegate : strategies) {
      answer.addAll(delegate.calculatePossibleCaptures(from));
    }
    return answer;
  }

  private Optional<Movement> findAnyWhichCanControl(Coordinate from, Coordinate target) {
    return strategies.stream().filter(i -> i.existsRelation(from, target)).findAny();
  }

  private Optional<Movement> findAnyWhichCanCapture(Coordinate from, Coordinate target) {
    return strategies.stream().filter(i -> i.canCapture(from, target)).findAny();
  }
}
