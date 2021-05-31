package me.mattharper.floppy.physics;

import me.mattharper.floppy.actor.PhysicsActor;
import me.mattharper.floppy.util.Vector2;

public class CollisionResult {
  public PhysicsActor otherActor;
  public boolean collision = false;
  public Vector2 direction = new Vector2();
  public Vector2 penetration = new Vector2();
}