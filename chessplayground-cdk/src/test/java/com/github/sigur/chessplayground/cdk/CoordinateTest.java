package com.github.sigur.chessplayground.cdk;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateTest {
    @Test
    void verifyEqual() {
        final Coordinate actual = new Coordinate(1,2);
        final Coordinate expected = new Coordinate(1,2);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
