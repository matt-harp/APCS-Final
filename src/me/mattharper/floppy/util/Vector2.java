package me.mattharper.floppy.util;

public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0, 0);
    public double x;
    public double y;

    public Vector2() {
        this.x = this.y = 0.f;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other) {
        return this.x == other.x && this.y == other.y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalized() {
        double mag = magnitude();
        return new Vector2(this.x / mag, this.y / mag);
    }

    public Vector2 copy() {
      return new Vector2(this.x, this.y);
    }

    public Vector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 subtract(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2 multiply(double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }

    public Vector2 multiply(Vector2 other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public Vector2 divide(double factor) {
        this.x /= factor;
        this.y /= factor;
        return this;
    }

    public Vector2 divide(Vector2 other) {
        this.x /= other.x;
        this.y /= other.y;
        return this;
    }

    public String toString() {
      return "[" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + "]";
    }
}
