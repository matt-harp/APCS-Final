package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public class Rectangle implements CollisionShape {
  private Vector2 min;
  private Vector2 max;

  public Rectangle(Vector2 min, Vector2 max) {
    this.min = min;
    this.max = max;
  }

  public CollisionResult testCollision(CollisionShape other, Vector2 relativeTo) {
    if(other instanceof Rectangle) {
      return testRect((Rectangle)other, relativeTo);
    }
    //todo
    return new CollisionResult();
  }

  private CollisionResult testRect(Rectangle other, Vector2 relativeTo) {
    CollisionResult result = new CollisionResult();
    Vector2 otherMin = relativeTo.copy().add(other.min.x, other.min.y);
    Vector2 otherMax = relativeTo.copy().add(other.max.x, other.max.y);
    if(min.x < otherMax.x && max.x > otherMin.x && max.y > otherMin.y && min.y < otherMax.y) {
      result.collision = true;
    }
    return result;
  }

  public Vector2 getMin() {
    return min;
  }

  public Vector2 getMax() {
    return max;
  }
}