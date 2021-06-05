package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.CollisionShape;
import me.mattharper.floppy.util.Time;
import me.mattharper.floppy.util.Vector2;

public abstract class PhysicsActor extends Actor {

    protected Vector2 velocity = new Vector2();
    protected double mass = 1;
    public float restitution = 1;
    protected float friction = 0.01f;
    protected boolean hasGravity = true; // [Rubric A] boolean variable
    public boolean isKinematic = true;
    protected CollisionShape collision;
    protected boolean canCollide = true;

    public PhysicsActor(World world) {
        super(world);
    }

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

    public void applyImpulse(Vector2 impulse) {
        Vector2 delta = impulse.copy().divide(mass);
        velocity.add(delta);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    protected void setVelocity(Vector2 velocity) {
        double dx = velocity.x - this.velocity.x;
        double dy = velocity.y - this.velocity.y;
        applyImpulse(new Vector2(dx*mass, dy*mass));
    }

    public boolean canCollide() {
        return canCollide;
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
