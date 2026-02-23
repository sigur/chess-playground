package com.github.sigur.chessplayground.cdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HealthPointTest {

  @Test
  void shouldExecuteBasicArithmetics() {
    final HealthPoint point = new HealthPoint(0);
    Assertions.assertThat(point.add(0)).isEqualTo(new HealthPoint(0));
    Assertions.assertThat(point.add(8)).isEqualTo(new HealthPoint(8));
    Assertions.assertThat(point.add(new HealthPoint(3))).isEqualTo(new HealthPoint(3));
    Assertions.assertThat(point.subtract(new HealthPoint(3))).isEqualTo(new HealthPoint(-3));
    Assertions.assertThat(point.subtract(3)).isEqualTo(new HealthPoint(-3));
    Assertions.assertThat(point.subtract(new HealthPoint(-3))).isEqualTo(new HealthPoint(3));
  }

  @Test
  void shouldReturnAbsoluteValues() {
    Assertions.assertThat(new HealthPoint(0).abs()).isEqualTo(new HealthPoint(0));
    Assertions.assertThat(new HealthPoint(-10).abs()).isEqualTo(new HealthPoint(10));
    Assertions.assertThat(new HealthPoint(11).abs()).isEqualTo(new HealthPoint(11));
  }

  @Test
  void shouldBeComparable() {
    Assertions.assertThat(new HealthPoint(8)).isGreaterThan(new HealthPoint(7));
    Assertions.assertThat(new HealthPoint(2)).isGreaterThan(new HealthPoint(0));
    Assertions.assertThat(new HealthPoint(0)).isEqualTo(new HealthPoint(0));
    Assertions.assertThat(new HealthPoint(0)).isGreaterThanOrEqualTo(new HealthPoint(0));
    Assertions.assertThat(new HealthPoint(0)).isLessThanOrEqualTo(new HealthPoint(0));
    Assertions.assertThat(new HealthPoint(-1)).isLessThan(new HealthPoint(0));

    Assertions.assertThat(new HealthPoint(7).isGreaterThan(new HealthPoint(7))).isFalse();
    Assertions.assertThat(new HealthPoint(7).isGreaterThan(new HealthPoint(8))).isFalse();
    Assertions.assertThat(new HealthPoint(7).isGreaterThan(new HealthPoint(3))).isTrue();

    Assertions.assertThat(new HealthPoint(7).isGreaterOrEqualThan(new HealthPoint(7))).isTrue();
    Assertions.assertThat(new HealthPoint(7).isGreaterOrEqualThan(new HealthPoint(8))).isFalse();
    Assertions.assertThat(new HealthPoint(7).isGreaterOrEqualThan(new HealthPoint(3))).isTrue();

    Assertions.assertThat(new HealthPoint(7).isLessThan(new HealthPoint(7))).isFalse();
    Assertions.assertThat(new HealthPoint(7).isLessThan(new HealthPoint(8))).isTrue();
    Assertions.assertThat(new HealthPoint(7).isLessThan(new HealthPoint(3))).isFalse();

    Assertions.assertThat(new HealthPoint(7).isLessOrEqualThan(new HealthPoint(7))).isTrue();
    Assertions.assertThat(new HealthPoint(7).isLessOrEqualThan(new HealthPoint(8))).isTrue();
    Assertions.assertThat(new HealthPoint(7).isLessOrEqualThan(new HealthPoint(3))).isFalse();
  }
}
