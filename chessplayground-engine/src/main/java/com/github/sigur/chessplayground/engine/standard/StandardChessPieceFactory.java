package com.github.sigur.chessplayground.engine.standard;

import com.github.sigur.chessplayground.cdk.*;
import com.github.sigur.chessplayground.engine.PieceFactory;

public class StandardChessPieceFactory implements PieceFactory {
  private static final Coordinate ONE_STEP = new Coordinate(0, 1);
  private static final Coordinate TWO_STEPS = new Coordinate(0, 2);
  private static final Coordinate ZERO = new Coordinate(0, 0);
  private static final PieceName PAWN_VALUE = PieceName.PAWN;
  private final Coordinate size;
  private final Color color;

  public StandardChessPieceFactory(int width, int height, Color color) {
    this.size = new Coordinate(width, height);
    this.color = color;
  }

  @Override
  public Piece createPawn(Coordinate position, Direction direction) {
    final RouteStrategy pawnMovements =
        new MultipleRouteStrategy(
            new DirectionalDeltaRouteStrategy(size.x(), size.y(), ONE_STEP, direction),
            new DirectionalDeltaRouteStrategy(size.x(), size.y(), TWO_STEPS, direction));
    return Piece.builder()
        .movement(new CapturelessMovement(pawnMovements))
        //.attack(new PawnRouteStrategy(ZERO, size, new Coordinate(1, 1), direction))
        .type(PAWN_VALUE)
        .position(position)
        .color(color)
        .build();
  }

  public int getWidth() {
    return size.x();
  }

  public int getHeight() {
    return size.y();
  }
}
