package com.github.sigur.chessplayground.cdk;

/**
 * @author sigur
 */
public interface Health {

  HealthPoint current();

  HealthPoint max();

  HealthPoint min();

  boolean update(HealthPoint data);

  /**
   * Processes the internal lifecycle logic of the health component.
   *
   * <p>This function acts as the "vital pulse" of the piece, typically it should be invoked by the
   * Game Engine at the end of every turn. It is responsible for handling autonomous state changes
   * that occur over time, for example:
   *
   * <ul>
   *   <li><b>Degradation:</b> Natural health decay or "starvation" mechanics.
   *   <li><b>Status Effects:</b> Applying damage over time (e.g., poison, bleed).
   *   <li><b>Regeneration:</b> Recovering health points based on specific modifiers.
   * </ul>
   *
   * By encapsulating these temporal changes here, the component remains decoupled from external
   * attack logic, allowing for dynamic and reactive health behaviors driven solely by the game's
   * progression.
   */
  void heartbeat();

  default boolean isDead() {
    return !isAlive();
  }

  default boolean isAlive() {
    return current().isGreaterThan(min());
  }
}
