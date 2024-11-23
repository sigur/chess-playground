package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class CapturelessMovementTest {

  @Test
  void calculatePossibleCaptures() {
    final DummyRoute movement = new DummyRoute();
    movement.setCoordinates(Collections.singleton(new Coordinate(3, 5)));

    final CapturelessMovement actual = new CapturelessMovement(movement);
    actual.calculatePossibleCaptures(new Coordinate(3, 3)).isEmpty();
  }

  @Test
  void executeControllable() {
    final DummyRoute movement = new DummyRoute();
    movement.setCoordinates(Collections.singleton(new Coordinate(3, 5)));
    movement.setControl(true);

    final CapturelessMovement move = new CapturelessMovement(movement);
    final MovementOutcome actual = move.execute(new Coordinate(3, 3), new Coordinate(3, 5));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getCaptures()).isEmpty();
    assertThat(actual.getPosition()).hasValue(new Coordinate(3, 5));
  }

  @Test
  void executeNotControllable() {
    final DummyRoute movement = new DummyRoute();
    movement.setCoordinates(Collections.singleton(new Coordinate(3, 5)));
    movement.setControl(false);

    final CapturelessMovement move = new CapturelessMovement(movement);
    final MovementOutcome actual = move.execute(new Coordinate(3, 3), new Coordinate(3, 5));
    assertThat(actual.isValid()).isFalse();
    assertThat(actual.getCaptures()).isEmpty();
    assertThat(actual.getPosition()).isEmpty();
  }

  @Test
  void controllable() {
    final DummyRoute movement = new DummyRoute();
    movement.setControl(true);

    final CapturelessMovement move = new CapturelessMovement(movement);
    final boolean actual = move.existsRelation(new Coordinate(3, 3), new Coordinate(3, 5));
    assertThat(actual).isTrue();
  }

  @Test
  void notControllable() {
    final DummyRoute movement = new DummyRoute();
    movement.setControl(false);

    final CapturelessMovement move = new CapturelessMovement(movement);
    final boolean actual = move.existsRelation(new Coordinate(3, 3), new Coordinate(3, 5));
    assertThat(actual).isFalse();
  }

  @Test
  void pawnMovement() {
    final DirectionalDeltaRouteStrategy movement =
        new DirectionalDeltaRouteStrategy(7, 7, new Coordinate(0, 1), Direction.ASCENT);

    final CapturelessMovement move = new CapturelessMovement(movement);
    final MovementOutcome actual = move.execute(new Coordinate(2, 3), new Coordinate(2, 4));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getCaptures()).isEmpty();
    assertThat(actual.getPosition()).hasValue(new Coordinate(2, 4));
  }

  @Test
  void canCapture() {
    final SimplyRouteStrategy movement = new SimplyRouteStrategy();
    movement.setCoordinates(Collections.singleton(new Coordinate(3, 2)));

    final CapturelessMovement move = new CapturelessMovement(movement);
    final boolean actual = move.canCapture(new Coordinate(3, 2), new Coordinate(3, 2));
    assertThat(actual).isFalse();
  }
}
