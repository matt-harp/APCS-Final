package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public class Rectangle implements CollisionShape {
  private Vector2 min;
  private Vector2 max;

  public Rectangle(Vector2 min, Vector2 max) {
    this.min = min;
    this.max = max;
  }

  public CollisionResult testCollision(CollisionShape other) {
    if(other instanceof Rectangle) {
      return testRect((Rectangle)other);
    }
    //todo
    return false;
  }

  private CollisionResult testRect(Rectangle other) {
    CollisionResult result = new CollisionResult();
    if()
    return result;
  }

  public Vector2 getMin() {
    return min;
  }

  public Vector2 getMax() {
    return max;
  }
}