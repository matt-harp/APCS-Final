package me.mattharper.floppy.actor;

import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.CollisionShape;
import me.mattharper.floppy.util.Time;
import me.mattharper.floppy.util.Vector2;

public abstract class PhysicsActor extends Actor {

    protected Vector2 velocity = new Vector2();
    protected double mass = 1;
    protected boolean hasGravity = true; // [Rubric A] boolean variable
    protected boolean isKinematic = true;
    protected CollisionShape collision; //todo component

    @Override
    public void update() {
        super.update();
        if(isKinematic)
            position.add(velocity.copy().multiply(Time.deltaSeconds));
    }

    public void applyForce(Vector2 force) {
        Vector2 delta = force.divide(mass).multiply(Time.deltaSeconds);
        velocity.add(delta);
    }

    public void applyForceInstant(Vector2 force) {
        Vector2 delta = force.divide(mass);
        velocity.add(delta);
    }

    protected void setVelocity(Vector2 velocity) {
        double dx = velocity.x - this.velocity.x;
        double dy = velocity.y - this.velocity.y;
        applyForceInstant(new Vector2(dx*mass, dy*mass));
    }

    public boolean canCollideWith(PhysicsActor other) {
        return true;
    }

    public abstract void onCollision(CollisionResult collision);

    public boolean hasGravity() {
        return hasGravity;
    }

    public double getMass() {
        return mass;
    }

    public CollisionShape getCollision() {
      return collision;
    }

    public String toString() {
        return "[PhysicsActor of mass " + mass + " at " + position + "]";
    }
}
