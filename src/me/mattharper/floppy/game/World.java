package me.mattharper.floppy.game;

import me.mattharper.floppy.actor.*;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

public class World {
    private final List<Actor> actors = new ArrayList<>(); // [Rubric A] ArrayList
    public final Vector2 gravity = new Vector2(0, -9.81);

    public void init() {
        spawn(new Pointer(this));
        spawn(new CameraController(this));
        spawn(new DebugLog(this));
        spawn(new Box(this, new Vector2(0, 20), new Vector2(-3, -3), new Vector2(3, 3)));
        spawn(new Box(this, new Vector2(0, -10), new Vector2(-300, -300), new Vector2(300, 0)));
        spawn(new BouncyBox(this, new Vector2(0, 10), new Vector2(-3, -3), new Vector2(3, 3)));
        spawn(new BouncyBox(this, new Vector2(0, 0), new Vector2(-3, -3), new Vector2(3, 3)));

        for (Actor actor : actors) {
            actor.init();
        }
        System.out.println("World initialized");
    }

    public void spawn(Actor actor) {
        actors.add(actor); // [Rubric A] add method (ArrayList)
    }

    public boolean destroy(Actor actor) {
        return actors.remove(actor); // [Rubric A] remove method (ArrayList)
    }

    public void update() {
        updatePhysics();
        resolveCollisions();
    }

    public void updatePhysics() {
        for (Actor actor : actors) { // [Rubric B] enhanced for loop
            if (actor instanceof PhysicsActor) {
                PhysicsActor physicsActor = (PhysicsActor) actor;
                if (physicsActor.hasGravity()) {
                    physicsActor.applyForce(gravity.copy().multiply(physicsActor.getMass()));
                }
            }
            actor.update();
        }
    }

    public void resolveCollisions() {
        List<Map.Entry<PhysicsActor, PhysicsActor>> already = new ArrayList<>();
        for (PhysicsActor actor : getPhysicsActors()) {
            //todo if(!actor.collides()) continue;
            //todo broad phase bounding checks
            for (PhysicsActor other : getPhysicsActors()) { // [Rubric B] nested for loop
                if (actor == other) continue;
                if(already.stream().anyMatch(p -> (p.getKey() == actor && p.getValue() == other) || (p.getKey() == other && p.getValue() == actor))) continue;
                //todo if(!other.collides()) continue;
                if (!actor.canCollideWith(other)) continue;
                CollisionResult result = actor.getCollision().testCollision(other.getCollision(), other.getPosition().copy().subtract(actor.getPosition()));
                if (result.collision) {
                    resolveCollision(actor, other, result);
                    result.otherActor = other;
                    actor.onCollision(result);
                    result.otherActor = actor; //todo hack
                    other.onCollision(result);
                    already.add(new AbstractMap.SimpleEntry<>(actor, other));
                }
            }
        }
    }

    private void resolveCollision(PhysicsActor a, PhysicsActor b, CollisionResult result) {
        Vector2 rv = b.getVelocity().copy().subtract(a.getVelocity());
        double normalVelocity = rv.dotProduct(result.normal);
        if(normalVelocity > 0) return;
        float e = Math.min(a.restitution, b.restitution);
        double j = -(1+e) * normalVelocity;
        j /= (1/a.getMass()) + (1/b.getMass());
//        Vector2 impulse = result.normal.copy().multiply(j*2);
        Vector2 impulse = result.normal.copy().multiply(a.getMass() * a.getVelocity().magnitude());
        b.applyImpulse(impulse);
        impulse.multiply(-1);
        a.applyImpulse(impulse);

        // correct interpenetration of objects
        if(b.isKinematic)
            b.getPosition().add(result.normal.copy().multiply(result.penetration));

    }

    private List<PhysicsActor> getPhysicsActors() { // [Rubric B] helper method
        List<PhysicsActor> result = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor instanceof PhysicsActor)
                result.add((PhysicsActor) actor);
        }
        return result;
    }

    public void render(GraphicsContext g) {
        //render actors
        for (Actor actor : actors) {
            actor.render(g);
            g.setColor(Color.BLACK);
        }
        //render ui
    }
}
