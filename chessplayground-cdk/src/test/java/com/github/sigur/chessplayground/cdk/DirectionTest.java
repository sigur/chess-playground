package com.github.sigur.chessplayground.cdk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DirectionTest {

  @Test
  void opposite() {
    assertThat(Direction.DESCENT.opposite()).isEqualTo(Direction.ASCENT);
    assertThat(Direction.ASCENT.opposite()).isEqualTo(Direction.DESCENT);
  }

  @Test
  void apply() {
    assertThat(Direction.DESCENT.apply(1)).isEqualTo(-1);
    assertThat(Direction.DESCENT.apply(-1)).isEqualTo(1);
    assertThat(Direction.ASCENT.apply(1)).isEqualTo(1);
    assertThat(Direction.ASCENT.apply(-1)).isEqualTo(-1);
  }
}
