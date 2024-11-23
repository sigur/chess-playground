package com.github.sigur.chessplayground.cdk;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.TreeSet;

public class MovementOutcome {
  private final boolean valid;
  private final Coordinate position;
  private final Collection<Coordinate> captures;

  private MovementOutcome(boolean valid, Coordinate position, Collection<Coordinate> captures) {
    this.valid = valid;
    this.position = position;
    this.captures = captures;
  }

  public static MovementOutcomeBuilder builder() {
    return new MovementOutcomeBuilder();
  }

  public static MovementOutcome invalid() {
    return new MovementOutcomeBuilder().executed(false).build();
  }

  public Optional<Coordinate> getPosition() {
    return Optional.ofNullable(position);
  }

  public Collection<Coordinate> getCaptures() {
    return Collections.unmodifiableCollection(captures);
  }

  public boolean isValid() {
    return valid;
  }

  public static class MovementOutcomeBuilder {

    private final Collection<Coordinate> captures;
    private boolean executed;
    private Coordinate position;

    private MovementOutcomeBuilder() {
      this.captures = new TreeSet<>();
    }

    public MovementOutcomeBuilder executed(boolean executed) {
      this.executed = executed;
      return this;
    }

    public MovementOutcomeBuilder position(Coordinate position) {
      this.position = position;
      return this;
    }

    public MovementOutcomeBuilder capture(Coordinate capture) {
      this.captures.add(capture);
      return this;
    }

    public MovementOutcomeBuilder capture(Collection<Coordinate> captures) {
      this.captures.addAll(captures);
      return this;
    }

    public MovementOutcome build() {
      return new MovementOutcome(executed, position, captures);
    }
  }
}
