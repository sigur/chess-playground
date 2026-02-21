package com.github.sigur.chessplayground.cdk.util;

public final class Guards {

  private Guards() {
    throw new UnsupportedOperationException(
        "Guards is an utility class. Instances are not allowed.");
  }

    public static void argument(boolean condition, String message, Object... messageArgs) {
        if(!condition) {
            throw new IllegalArgumentException(message.formatted(messageArgs));
        }
    }
}
