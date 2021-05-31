package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public interface CollisionShape { // [Rubric C] interface
    CollisionResult testCollision(CollisionShape other, Vector2 relativeTo);
}
