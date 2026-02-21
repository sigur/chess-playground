package com.github.sigur.chessplayground.cdk;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoundingBoxTest {
  @Test
  void shouldCreateNewInstance() {
    assertThatNoException()
        .isThrownBy(() -> new BoundingBox(new Coordinate(0, 0), new Coordinate(0, 2)));
  }

  @Test
  void shouldThrowException() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new BoundingBox(new Coordinate(2, 0), new Coordinate(0, 2)))
            .withMessage("Bottom should be lower or equal than top");
  }

  @Test
  void shouldBeEnclosed() {
    final var box = new BoundingBox(new Coordinate(3, 8), new Coordinate(10, 25));
    assertThat(box.encloses(new Coordinate(3, 8))).isTrue();
    assertThat(box.encloses(new Coordinate(3, 25))).isTrue();
    assertThat(box.encloses(new Coordinate(10, 8))).isTrue();
    assertThat(box.encloses(new Coordinate(7, 12))).isTrue();
  }

  @Test
  void shouldNotBeEnclosed() {
    final var box = new BoundingBox(new Coordinate(0, 0), new Coordinate(7, 7));
    assertThat(box.encloses(new Coordinate(0, 8))).isFalse();
    assertThat(box.encloses(new Coordinate(8, 0))).isFalse();
    assertThat(box.encloses(new Coordinate(8, 8))).isFalse();
    assertThat(box.encloses(new Coordinate(-1, 0))).isFalse();
  }
}
