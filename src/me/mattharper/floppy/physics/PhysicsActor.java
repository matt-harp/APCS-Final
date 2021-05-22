package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Time;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public abstract class PhysicsActor {
    protected Vector2 position = Vector2.ZERO;
    protected Vector2 velocity = Vector2.ZERO;
    protected double mass = 1;
    protected boolean hasGravity = true;

    public void update() {
        position = position.add(velocity.multiply(Time.deltaSeconds));
    }

    public void applyForce(Vector2 force) {
        Vector2 delta = force.divide(mass).multiply(Time.deltaSeconds);
        velocity = velocity.add(delta);
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    public double getMass() {
        return mass;
    }

    public abstract void render(Graphics2D g);
}
