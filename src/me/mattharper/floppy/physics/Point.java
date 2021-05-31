package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public class Point implements CollisionShape {
    private Vector2 offset;

    public Point(Vector2 offset) {
        this.offset = offset;
    }

    public Point() {
        this.offset = new Vector2();
    }

    @Override
    public CollisionResult testCollision(CollisionShape other, Vector2 relativeTo) {
        return new CollisionResult();
    }

    public Vector2 getOffset() {
        return offset;
    }
}
