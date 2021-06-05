package me.mattharper.floppy.util;

public class Vector2 {
    public double x;
    public double y;

    public Vector2() {
        this.x = this.y = 0f;
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

    public double angle(Vector2 other) {
        return Math.acos(dotProduct(other));
    }

    public double dotProduct(Vector2 other) {
        Vector2 v1 = normalized();
        Vector2 v2 = other.normalized();
        return (v1.x * v2.x) + (v1.y * v2.y);
    }

    public double distance(Vector2 other) {
        return Math.sqrt(Math.pow(other.x-this.x, 2) + Math.pow(other.y-this.y, 2));
    }

    public static Vector2 getCenter(Vector2 v1, Vector2 v2) {
        return new Vector2((v1.x + v2.x)/2, (v1.y + v2.y)/2);
    }

    public Vector2 copy() {
      return new Vector2(this.x, this.y);
    }

    public Vector2 abs() {
        return new Vector2(Math.abs(this.x), Math.abs(this.y));
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

    public Vector2 multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
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

    public String toString() { // [Rubric B] toString method
      return "[" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + "]";
    }
}
