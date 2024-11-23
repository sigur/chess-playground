package com.github.sigur.chessplayground.cdk;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class is a specialization of {@code DirectionalDeltaRouteStrategy} that defines the behavior
 * between coordinates for the 'en passant' move.
 *
 * <p>The 'en passant move' in chess is a special pawn capture that can occur when a pawn moves two
 * squares forward from its starting position and lands beside an opponent's pawn. This capture must
 * happen immediately after the two-square move, or the opportunity is lost.
 *
 * <p>Example: - White pawn on e5. - On black turn, black pawn is moved from d7 to d5 (two squares
 * forward). - White has option to capture the black pawn en passant, moving pawn from e5 to d6,
 * capturing the black pawn as if it had moved only one square forward.
 *
 * @author sigur
 * @see DirectionalDeltaRouteStrategy
 * @see Direction
 */
public class EnPassantRouteStrategy extends DirectionalDeltaRouteStrategy {
  public EnPassantRouteStrategy(int width, int height, Coordinate delta, Direction direction) {
    super(width, height, delta, direction);
  }

  /**
   * @param from
   * @param target
   * @return
   */
  @Override
  public boolean existsRelation(Coordinate from, Coordinate target) {
    final Coordinate captureTarget = createNewTargetWithDelta(target);
    return super.existsRelation(target, captureTarget);
  }

  /**
   * @param from
   * @return
   */
  @Override
  public Set<Coordinate> calculateAvailable(Coordinate from) {
    final Set<Coordinate> answer = new TreeSet<>();

    final Coordinate target = createNewTargetWithDelta(from);
    if (verifyCoordinateIsInside(from) && verifyCoordinateIsInside(target)) {
      answer.add(target);
    }

    return answer;
  }

  /**
   * @param target
   * @return
   */
  private Coordinate createNewTargetWithDelta(Coordinate target) {
    return new Coordinate(target.x() + delta.x(), target.y() + direction.apply(delta.y()));
  }
}
