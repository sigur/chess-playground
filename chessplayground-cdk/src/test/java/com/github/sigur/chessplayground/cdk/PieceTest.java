package com.github.sigur.chessplayground.cdk;



class PieceTest {
  private final int low = 7;
  private final int top = 7;

  private DirectionalDeltaRouteStrategy pawnWalking(Direction direction) {
    return new DirectionalDeltaRouteStrategy(low, top, new Coordinate(0, 1), direction);
  }

  private DirectionalDeltaRouteStrategy pawnAttack(Direction direction) {
    return new DirectionalDeltaRouteStrategy(low, top, new Coordinate(1, 1), direction);
  }

 /* @Test
  void checkMove() {
    final CapturelessMovement movement =
        new CapturelessMovement(pawnWalking(Direction.ASCENT), pawnAttack(Direction.ASCENT));
    final Piece pawn =
        Piece.builder()
            .movement(movement)
            .type(PieceName.PAWN)
            .position(new Coordinate(4, 3))
            .color(Color.WHITE)
            .build();

    ActionOutcome invalid = pawn.checkMoveTo(new Coordinate(3, 2));
    assertThat(invalid.isValid()).isFalse();
    assertThat(invalid.getPosition()).isEmpty();
    assertThat(pawn.getPosition()).isEqualTo(new Coordinate(4, 3));

    ActionOutcome valid = pawn.checkMoveTo(new Coordinate(4, 4));
    assertThat(valid.isValid()).isTrue();
    assertThat(valid.getPosition()).hasValue(new Coordinate(4, 4));
    assertThat(pawn.getPosition()).isEqualTo(new Coordinate(4, 3));
  }

  @Test
  void validMove() {
    final CapturelessMovement movement =
        new CapturelessMovement(pawnWalking(Direction.DESCENT), pawnAttack(Direction.DESCENT));
    final Piece pawn =
        Piece.builder()
            .movement(movement)
            .type(PieceName.PAWN)
            .position(new Coordinate(3, 3))
            .color(Color.WHITE)
            .build();

    ActionOutcome actual = pawn.move(new Coordinate(3, 2));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getPosition()).hasValue(new Coordinate(3, 2));
    assertThat(actual.getCaptures()).isEmpty();
    assertThat(pawn.getPosition()).isEqualTo(new Coordinate(3, 2));

    assertThat(pawn.getMovesCounter()).isEqualTo(0);
    assertThat(pawn.hasMoves()).isFalse();
    assertThat(pawn.isNotStuck()).isTrue();
    assertThat(pawn.isStuck()).isFalse();
  }

  @Test
  void validMoveButItsStuck() {
    final CapturelessMovement movement =
        new CapturelessMovement(pawnWalking(Direction.DESCENT), pawnAttack(Direction.DESCENT));
    final Piece pawn =
        Piece.builder()
            .movement(movement)
            .type(PieceName.PAWN)
            .position(new Coordinate(3, 3))
            .color(Color.BLACK)
            .build();

    pawn.markAsStuck(new Coordinate(6, 6));

    ActionOutcome actual = pawn.move(new Coordinate(3, 2));
    assertThat(actual.isValid()).isFalse();
    assertThat(actual.getPosition()).isEmpty();
    assertThat(actual.getCaptures()).isEmpty();

    assertThat(pawn.getMovesCounter()).isEqualTo(0);
    assertThat(pawn.hasMoves()).isFalse();
    assertThat(pawn.isNotStuck()).isFalse();
    assertThat(pawn.isStuck()).isTrue();
    assertThat(pawn.getPosition()).isEqualTo(new Coordinate(3, 3));
    /*   Assertions.assertThat(pawn.move(new Coordinate(3, 4)).isValid()).isFalse();

      pawn.markAsStuck(Collections.singleton(top));
      Assertions.assertThat(pawn.move(new Coordinate(3, 1)).getPosition()).isEmpty();

      Assertions.assertThat(pawn.getMovesCounter()).isEqualTo(0);
      Assertions.assertThat(pawn.hasMoves()).isFalse();

  }

  @Test
  void captureTest() {
    final PawnAttackMovement movement =
        new PawnAttackMovement(
            pawnAttack(Direction.ASCENT),
            new PawnRouteStrategy(low, top, new Coordinate(0, 0), Direction.ASCENT));
    final Piece pawn =
        Piece.builder()
            .movement(movement)
            .type(PieceName.PAWN)
            .position(new Coordinate(3, 3))
            .color(Color.WHITE)
            .build();

    ActionOutcome actual = pawn.move(new Coordinate(4, 4));
    assertThat(actual.isValid()).isTrue();
    assertThat(actual.getPosition()).hasValue(new Coordinate(4, 4));
    assertThat(actual.getCaptures()).containsExactly(new Coordinate(4, 4));
  }


  @Test
  void enPassant() {
    final Piece actual =
        Piece.builder()
            .movement(
                new PawnRouteStrategy(
                    low,
                    new Coordinate(5, 5),
                    new Coordinate(0, 1),
                    Direction.ASCENT))
            .attack(
                new PawnRouteStrategy(
                    low,
                    new Coordinate(5, 5),
                    new Coordinate(1, 1),
                    Direction.ASCENT.opposite()))
            .type(PieceName.PAWN)
            .position(new Coordinate(3, 3))
            .color(Color.WHITE)
            .build();

    Assertions.assertThat(actual.capture(new Coordinate(2, 2))).isTrue();
    Assertions.assertThat(actual.retrieveAttackingCoordinates())
        .contains(new Coordinate(2, 2), new Coordinate(4, 2));
  }

  @Test
  void enPassant2() {
    final Piece actual =
        Piece.builder()
            .movement(
                new PawnRouteStrategy(
                    low,
                    new Coordinate(5, 5),
                    new Coordinate(0, 1),
                    Direction.DESCENT))
            .attack(
                new PawnRouteStrategy(
                    low,
                    new Coordinate(5, 5),
                    new Coordinate(1, 1),
                    Direction.DESCENT.opposite()))
            .type(PieceName.PAWN)
            .position(new Coordinate(4, 3))
            .color(Color.BLACK)
            .build();
    Assertions.assertThat(actual.capture(new Coordinate(4, 4))).isFalse();
    Assertions.assertThat(actual.retrieveAttackingCoordinates())
        .contains(new Coordinate(5, 4), new Coordinate(3, 4));
  }
   */
}
