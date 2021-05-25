package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public class AABB extends CollisionShape {
  private Vector2 min;
  private Vector2 max;

  public AABB(Vector2 min, Vector2 max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean intersectsWith(CollisionShape other) {
    if(other instanceof AABB) {
      return testBoundingBoxCollision((AABB)other);
    }
    //todo
    return false;
  }

  private boolean testBoundingBoxCollision(AABB other) {
    return false;
  }

  public Vector2 getMin() {
    return min;
  }

  public Vector2 getMax() {
    return max;
  }
}