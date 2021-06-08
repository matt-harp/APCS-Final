package me.mattharper.floppy.game;

import me.mattharper.floppy.actor.*;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class World {
    private final List<Actor> actors = new ArrayList<>(); // [Rubric A] ArrayList
    public final Vector2 gravity = new Vector2(0, -9.81);

    public void init() {
        spawn(new Pointer(this));
        spawn(new CameraController(this));
        spawn(new DebugLog(this));
        spawn(new GameManager(this));
        spawn(new Box(this, new Vector2(0, 0), new Vector2(-100, -100), new Vector2(100, -90)));
        spawn(new Box(this, new Vector2(0, 0), new Vector2(-100, -100), new Vector2(-90, 100)));
        spawn(new Box(this, new Vector2(0, 0), new Vector2(-100, 90), new Vector2(100, 100)));
        spawn(new Box(this, new Vector2(0, 0), new Vector2(90, -100), new Vector2(100, 100)));

        for (Actor actor : actors) {
            actor.init();
        }
        System.out.println("World initialized");
    }

    public void spawn(Actor actor) {
        GameView.endOfFrame.add(() -> {
            actors.add(actor); // [Rubric A] add method (ArrayList)
        });
    }

    public void destroy(Actor actor) {
        GameView.endOfFrame.add(() -> {
            actors.remove(actor); // [Rubric A] remove method (ArrayList)
        });
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
            for (PhysicsActor other : getPhysicsActors()) { // [Rubric B] nested for loop
                if (actor == other) continue;
                if (already.stream().anyMatch(p -> (p.getKey() == actor && p.getValue() == other) || (p.getKey() == other && p.getValue() == actor)))
                    continue;
                if (!actor.canCollideWith(other)) continue;
                CollisionResult result = actor.getCollision().testCollision(other.getCollision(), other.getPosition().copy().subtract(actor.getPosition()));
                if (result.collision) {
                    resolveCollision(actor, other, result);
                    result.otherActor = other;
                    actor.onCollision(result);
                    result.otherActor = actor;
                    other.onCollision(result);
                    already.add(new AbstractMap.SimpleEntry<>(actor, other));
                }
            }
        }
    }

    private void resolveCollision(PhysicsActor a, PhysicsActor b, CollisionResult result) {
        if(a.overlapOnly(b) || b.overlapOnly(a)) return;

        double e = Math.min(a.restitution, b.restitution);
        Vector2 ab = a.getVelocity().copy().subtract(b.getVelocity());
        double J = -(1+e)*ab.dotProduct(result.normal);
        J /= (1 / a.getMass() + 1 / b.getMass());
        a.applyImpulse(result.normal.copy().multiply(J));
        b.applyImpulse(result.normal.copy().multiply(-J));

        // correct interpenetration of objects
        if (b.isKinematic)
            b.getPosition().add(result.normal.copy().multiply(result.penetration));
        if(a.isKinematic)
            a.getPosition().add(result.normal.copy().multiply(-1).multiply(result.penetration));
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
