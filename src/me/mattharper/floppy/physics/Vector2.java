package me.mattharper.floppy.physics;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = this.y = 0.f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other) {
        return this.x == other.x && this.y == other.y;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double angle(Vector2 other) {
        return 0;
    }

    public double dotProduct(Vector2 other) {
        return 0;
    }

    public Vector2 multiply(double factor) {
        return new Vector2(this.x * (float)factor, this.y * (float)factor);
    }
}
