package me.mattharper.floppy.actor;

import me.mattharper.floppy.physics.AABB;
import me.mattharper.floppy.util.Time;
import me.mattharper.floppy.util.Vector2;

/**
 * Physics actors are Actors that manage their own physics
 */
public abstract class PhysicsActor extends Actor {

    protected Vector2 velocity = Vector2.ZERO;
    protected double mass = 1;
    protected boolean hasGravity = true;
    protected boolean isKinematic = true;
    // respective to the position of the actor
    protected AABB boundingBox;

    @Override
    public void update() {
        if(isKinematic)
            position.add(velocity.copy().multiply(Time.deltaSeconds));
        if(position.y < 0)
            applyForceInstant(velocity.copy().multiply(-2*mass));
    }

    public void applyForce(Vector2 force) {
        Vector2 delta = force.divide(mass).multiply(Time.deltaSeconds);
        velocity.add(delta);
    }

    public void applyForceInstant(Vector2 force) {
        Vector2 delta = force.divide(mass);
        velocity.add(delta);
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    public double getMass() {
        return mass;
    }

    public String toString() {
        return "[PhysicsActor of mass " + mass + " at " + position + "]";
    }
}