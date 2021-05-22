package me.mattharper.floppy.game;

import me.mattharper.floppy.physics.Ball;
import me.mattharper.floppy.physics.PhysicsActor;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    private static final List<PhysicsActor> actors = new ArrayList<>();
    private static Vector2 gravity = new Vector2(0, -10);

    public static void init() {
        spawn(new Ball(new Vector2(0, 0)));
        spawn(new Ball(new Vector2(10, 10)));
        spawn(new Ball(new Vector2(20, 20)));
        spawn(new Ball(new Vector2(30, 30)));
        spawn(new Ball(new Vector2(40, 40)));
    }

    public static void updatePhysics() {
        for(PhysicsActor actor : actors) {
            if(actor.hasGravity()) {
                actor.applyForce(gravity.multiply(actor.getMass()));
            }
            actor.update();
        }
    }
    public static void spawn(PhysicsActor actor) {
        actors.add(actor);
    }
    public static boolean destroy(PhysicsActor actor) {
        return actors.remove(actor);
    }

    public static void update() {
        updatePhysics();
    }

    public static void render(Graphics2D g) {
        //render actors
        for (PhysicsActor actor : actors) {
            actor.render(g);
        }
        //render ui
    }
}
