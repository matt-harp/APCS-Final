package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.SpringConstraint;
import me.mattharper.floppy.util.Vector2;

import java.awt.Color;

public class BouncyBox extends Box { // [Rubric C] multi-level inheritance
    SpringConstraint constraint;

    public BouncyBox(World world, Vector2 position, Vector2 min, Vector2 max) {
        super(world, position, min, max); // [Rubric B] super keyword
        this.hasGravity = true;
        this.isKinematic = true;
        this.mass = 10;
        this.restitution = 0.94f;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext g) {
        g.setColor(Color.RED);
        super.render(g);
    }

    @Override
    public void onCollision(CollisionResult result) {
        if(result.otherActor instanceof Pointer) return;
        velocity.multiply(1-friction);
//        float e = Math.min(restitution, result.otherActor.restitution);
//
//        Vector2 impulse = result.normal.copy().multiply(velocity.abs().add(velocity.abs().multiply(e))).multiply(mass);
//        applyImpulse(impulse);
//
//        float mu = Math.max(friction, result.otherActor.friction);
//        Vector2 friction = velocity.copy().multiply((impulse.magnitude() / Time.deltaSeconds ) * -mu);
//
//        applyForce(friction);


//        position.add(result.normal.copy().multiply(result.penetration));
    }
}
