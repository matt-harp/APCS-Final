package me.mattharper.floppy.physics;

public abstract class CollisionShape {
    public abstract boolean intersectsWith(CollisionShape other);
}
