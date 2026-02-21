package com.github.sigur.chessplayground.cdk.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GuardsTest {
  @Test
  void shouldRaiseIllegalArgumentException() {
    Assertions.assertThatIllegalArgumentException()
        .isThrownBy(
            () ->
                Guards.argument(
                    false, "This is an %s test and this case is %s", "error", "not valid"))
        .withMessage("This is an error test and this case is not valid");
  }

  @Test
  void shouldRaiseIllegalArgumentExceptionWithSimpleMessage() {
    Assertions.assertThatIllegalArgumentException()
            .isThrownBy(
                    () ->
                            Guards.argument(
                                    false, "This is simple message for this error case"))
            .withMessage("This is simple message for this error case");
  }

  @Test
  void shouldNotRaiseIllegalArgumentException() {
    Assertions.assertThatNoException()
        .isThrownBy(
            () ->
                Guards.argument(
                    true, "This is an %s test and this case is %s", "success", "valid"));
  }
}
