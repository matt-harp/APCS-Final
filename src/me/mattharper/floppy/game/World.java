package me.mattharper.floppy.game;

import javafx.util.Pair;
import me.mattharper.floppy.actor.*;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    private static final List<Actor> actors = new ArrayList<>(); // [Rubric A] ArrayList, [Rubric A] static array
    private static Vector2 gravity = new Vector2(0, -9.81);

    public static void init() {
        spawn(new Pointer());
        spawn(new CameraController());
        spawn(new Box(new Vector2(0, 0), new Vector2(-3, -3), new Vector2(3, 3)));
        spawn(new BouncyBox(new Vector2(0, 0), new Vector2(-3, -3), new Vector2(3, 3)));
        System.out.println("World initialized");
    }

    public static void spawn(Actor actor) {
        actors.add(actor); // [Rubric A] add method (ArrayList)
    }

    public static boolean destroy(Actor actor) {
        return actors.remove(actor); // [Rubric A] remove method (ArrayList)
    }

    public static void update() {
        updatePhysics();
        resolveCollisions();
    }

    public static void updatePhysics() {
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

    public static void resolveCollisions() {
        List<Pair<PhysicsActor, PhysicsActor>> already = new ArrayList<>();
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
                    result.otherActor = other;
                    actor.onCollision(result);
                    result.otherActor = actor; //todo hack
                    other.onCollision(result);
                    already.add(new Pair<>(actor, other));
                }
            }
        }
    }

    private static List<PhysicsActor> getPhysicsActors() { // [Rubric B] helper method
        List<PhysicsActor> result = new ArrayList<>();
        for (Actor actor : actors) {
            if (actor instanceof PhysicsActor)
                result.add((PhysicsActor) actor);
        }
        return result;
    }

    public static void render(GraphicsContext g) {
        //render actors
        for (Actor actor : actors) {
            actor.render(g);
            g.setColor(Color.BLACK);
        }
        //render ui
    }
}
