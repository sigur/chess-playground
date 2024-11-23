package com.github.sigur.chessplayground.cdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ColorTest {
  @Test
  void opposite() {
    Assertions.assertThat(Color.WHITE.opposite()).isEqualTo(Color.BLACK);
    Assertions.assertThat(Color.BLACK.opposite()).isEqualTo(Color.WHITE);
  }
}
