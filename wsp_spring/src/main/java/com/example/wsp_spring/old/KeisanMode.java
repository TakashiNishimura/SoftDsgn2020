package com.example.wsp_spring.old;

/**
 * 四則演算をするためのクラスです
 */
public class KeisanMode {

  private final float x;
  private final float y;
  private final String mode;

  public KeisanMode(float x, float y, String mode) {
    this.x = x;
    this.y = y;
    this.mode = mode;
  }

  public float getAns() {
    return switch (mode) {
      case "+" -> x + y;
      case "-" -> x - y;
      case "*" -> x * y;
      case "/" -> x / y;
      case "%" -> x % y;
      default -> 0.0F;
    };
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public String getMode() {
    return mode;
  }
}
