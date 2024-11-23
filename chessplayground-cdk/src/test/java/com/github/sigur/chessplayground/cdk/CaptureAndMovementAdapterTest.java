package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class CaptureAndMovementAdapterTest {

  @Test
  void executeNoCapture() {
    final RouteStrategy capture = new DummyRoute();
    final DummyRoute movement = new DummyRoute();
    movement.setControl(true);
    movement.setCoordinates(Collections.singleton(new Coordinate(5, 6)));

    final CaptureAndMovementAdapter move = new CaptureAndMovementAdapter(capture, movement);

    final MovementOutcome actual = move.execute(new Coordinate(2, 2), new Coordinate(3, 3));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getPosition()).hasValue(new Coordinate(3, 3));
    assertThat(actual.getCaptures()).isEmpty();
  }

  @Test
  void noCapture() {
    final DummyRoute capture = new DummyRoute();
    capture.setControl(false);
    final DummyRoute movement = new DummyRoute();
    movement.setControl(true);
    movement.setCoordinates(Collections.singleton(new Coordinate(5, 6)));

    final CaptureAndMovementAdapter move = new CaptureAndMovementAdapter(capture, movement);
    assertThat(move.canCapture(new Coordinate(2, 2), new Coordinate(3, 3))).isFalse();
    assertThat(move.calculatePossibleCaptures(new Coordinate(2, 2))).isEmpty();
  }

  @Test
  void pawnEnPassant() {

    final CaptureAndMovementAdapter enPassant =
        new CaptureAndMovementAdapter(
            new EnPassantRouteStrategy(5, 5, new Coordinate(0, 1), Direction.DESCENT),
            new DirectionalDeltaRouteStrategy(5, 5, new Coordinate(1, 1), Direction.ASCENT));

    final MovementOutcome actual = enPassant.execute(new Coordinate(2, 2), new Coordinate(3, 3));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getPosition()).hasValue(new Coordinate(3, 3));
    assertThat(actual.getCaptures()).containsExactly(new Coordinate(3, 2));
  }
}
