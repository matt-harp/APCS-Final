package me.mattharper.floppy.physics;

public abstract class PhysicsActor {
    private Vector2 position;
    private Vector2 velocity;
    private float mass;

    public void update() {
        position = position.add(velocity.multiply(Time.deltaSeconds));
    }
}
