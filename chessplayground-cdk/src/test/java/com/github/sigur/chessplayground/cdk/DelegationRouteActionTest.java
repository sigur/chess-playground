package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

class DelegationRouteActionTest {

  @Test
  void execute() {
    final MockedRouteStrategy movement = new MockedRouteStrategy();
    movement.setControl(true);
    movement.setCoordinates(Collections.singleton(new Coordinate(1, 1)));

    final DelegationRouteAction move = new DelegationRouteAction(movement);
    final RouteActionOutcome actual = move.execute(new Coordinate(0, 0), new Coordinate(1, 1));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getCaptures()).containsExactly(new Coordinate(1, 1));
    assertThat(actual.getPosition()).hasValue(new Coordinate(1, 1));
  }

  @Test
  void existsRelation() {
    final SimplyRouteStrategy movement = new SimplyRouteStrategy();

    final Set<Coordinate> valid = new TreeSet<>();
    valid.add(new Coordinate(3, 2));
    valid.add(new Coordinate(7, 7));
    movement.setCoordinates(valid);

    final DelegationRouteAction move = new DelegationRouteAction(movement);

    assertThat(move.existsRelation(new Coordinate(0, 0), new Coordinate(1, 1)))
        .withFailMessage("%s should not be controlled", new Coordinate(1, 1))
        .isFalse();

    assertThat(move.existsRelation(new Coordinate(0, 0), new Coordinate(3, 2)))
        .withFailMessage("%s should be controlled", new Coordinate(3, 2))
        .isTrue();
  }

  @Test
  void pawnExistsRelation() {
    final DirectionalDeltaRouteStrategy movement =
        new DirectionalDeltaRouteStrategy(5, 5, new Coordinate(0, 2), Direction.DESCENT);

    final DelegationRouteAction move = new DelegationRouteAction(movement);
    assertThat(move.existsRelation(new Coordinate(3, 5), new Coordinate(3, 3)))
        .withFailMessage(
            "%s should be available for pawn from %s with %s as movement and %s as direction",
            new Coordinate(3, 3), new Coordinate(3, 5), new Coordinate(0, 2), Direction.DESCENT)
        .isTrue();
  }

  @Test
  void calculateAvailable() {
    final SimplyRouteStrategy movement = new SimplyRouteStrategy();

    final Set<Coordinate> valid = new TreeSet<>();
    valid.add(new Coordinate(3, 2));
    valid.add(new Coordinate(7, 7));
    movement.setCoordinates(valid);

    final DelegationRouteAction move = new DelegationRouteAction(movement);

    assertThat(move.calculateAvailable(new Coordinate(0, 0)))
        .containsExactlyInAnyOrder(new Coordinate(7, 7), new Coordinate(3, 2));
  }

  @Test
  void pawnCalculateAvailable() {
    final DirectionalDeltaRouteStrategy movement =
        new DirectionalDeltaRouteStrategy(5, 5, new Coordinate(1, 1), Direction.DESCENT);

    final DelegationRouteAction move = new DelegationRouteAction(movement);
    assertThat(move.calculateAvailable(new Coordinate(3, 5)))
        .containsExactlyInAnyOrder(new Coordinate(2, 4), new Coordinate(4, 4));
  }

}
