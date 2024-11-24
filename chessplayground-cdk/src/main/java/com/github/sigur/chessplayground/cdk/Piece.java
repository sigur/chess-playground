package com.github.sigur.chessplayground.cdk;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Piece {
  private final Set<Coordinate> stuck;
  private final PieceName value;
  private final Color color;
  private Movement movement;
  private Coordinate position;
  private int movesCounter;
  private int capturesCounter;
  private boolean alive;

  private Piece(Movement walk, PieceName value, Coordinate position, Color color) {
    this.movement = walk;
    this.value = value;
    this.position = position;
    this.color = color;

    stuck = new TreeSet<>();
  }

  public static PieceBuilder builder() {
    return new PieceBuilder();
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public Coordinate getPosition() {
    return position;
  }

  public void updatePosition(Coordinate position) {
    this.position = position;
  }

  public Color getColor() {
    return color;
  }

  public PieceName getValue() {
    return value;
  }

  public int getCaptureCounter() {
    return capturesCounter;
  }

  public boolean isStuck() {
    return !isNotStuck();
  }

  public boolean isNotStuck() {
    return stuck.isEmpty();
  }

  public void markAsStuck(Coordinate from) {
    this.stuck.add(from);
  }

  public void markAsStuck(Collection<Coordinate> from) {
    this.stuck.addAll(from);
  }

  public boolean clearStuck(Coordinate from) {
    return this.stuck.remove(from);
  }

  public boolean clearStuck(Collection<Coordinate> from) {
    return this.stuck.removeAll(from);
  }

  public MovementOutcome move(Coordinate target) {
    final MovementOutcome answer = checkMoveTo(target);
    if (answer.isValid()) {
      answer.getPosition().ifPresent(this::updatePosition);
    }

    return answer;
  }

  public MovementOutcome checkMoveTo(Coordinate target) {
    MovementOutcome answer = MovementOutcome.invalid();
    final MovementOutcome action = movement.execute(position, target);
    if (action.isValid()) {
      final boolean captureResult =
          action.getCaptures().stream()
              .reduce(false, (partial, c) -> this.canCapture(c) || partial, Boolean::logicalOr);
      if (captureResult || isNotStuck()) {
        answer = action;
      }
    }
    return answer;
  }

  public Set<Coordinate> retrieveMovableCoordinates() {
    return movement.calculateAvailable(position);
  }

  public void sumToCapturesCounter(int value) {
    this.capturesCounter += value;
  }

  public void sumToMovesCounter(int value) {
    this.movesCounter += value;
  }

  private boolean canCapture(Coordinate target) {
    final boolean wasStuck = isStuck() && stuck.size() == 1 && stuck.remove(target);

    final boolean answer = isNotStuck();
    if (!answer && wasStuck) {
      markAsStuck(target);
    }

    return answer;
  }

  public Set<Coordinate> retrieveAttackingCoordinates() {
    return movement.calculatePossibleCaptures(position);
  }

  public int getMovesCounter() {
    return movesCounter;
  }

  public boolean hasMoves() {
    return movesCounter > 0;
  }

  public boolean checkColor(Color color) {
    return this.color == color;
  }

  public void changeMovement(Movement movement) {
    this.movement = movement;
  }

  public static class PieceBuilder {
    private PieceName value;
    private Color color;
    private Movement movement;
    private Coordinate position;

    private PieceBuilder() {}

    public PieceBuilder movement(Movement walk) {
      this.movement = walk;
      return this;
    }

    public PieceBuilder type(PieceName value) {
      this.value = value;
      return this;
    }

    public PieceBuilder position(Coordinate position) {
      this.position = position;
      return this;
    }

    public PieceBuilder color(Color color) {
      this.color = color;
      return this;
    }

    public Piece build() {
      return new Piece(movement, value, position, color);
    }
  }
}
