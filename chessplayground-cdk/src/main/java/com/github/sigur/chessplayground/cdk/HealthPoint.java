package com.github.sigur.chessplayground.cdk;

/**
 * @param value
 * @author sigur
 */
public record HealthPoint(int value) implements Comparable<HealthPoint> {

  /**
   * @param value
   * @return
   */
  public HealthPoint add(int value) {
    return new HealthPoint(this.value + value);
  }

  /**
   * @param value
   * @return
   */
  public HealthPoint add(HealthPoint value) {
    return add(value.value);
  }

  /**
   * @param value
   * @return
   */
  public HealthPoint subtract(int value) {
    return add(-value);
  }

  /**
   * @param value
   * @return
   */
  public HealthPoint subtract(HealthPoint value) {
    return add(-value.value);
  }

  /**
   * @return
   */
  public HealthPoint abs() {
    return new HealthPoint(Math.abs(value));
  }

  /**
   * @param data
   * @return
   */
  public boolean isGreaterThan(HealthPoint data) {
    return compareTo(data) > 0;
  }

  /**
   * @param data
   * @return
   */
  public boolean isLessThan(HealthPoint data) {
    return compareTo(data) < 0;
  }

  /**
   * @param data
   * @return
   */
  public boolean isGreaterOrEqualThan(HealthPoint data) {
    return compareTo(data) > -1;
  }

  /**
   * @param data
   * @return
   */
  public boolean isLessOrEqualThan(HealthPoint data) {
    return compareTo(data) < 1;
  }

  /**
   * @param o the object to be compared.
   * @return
   */
  @Override
  public int compareTo(HealthPoint o) {
    return Integer.compare(value, o.value);
  }
}
