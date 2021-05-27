package me.mattharper.floppy.game;

import me.mattharper.floppy.actor.Actor;
import me.mattharper.floppy.actor.Ball;
import me.mattharper.floppy.actor.PhysicsActor;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    private static final List<Actor> actors = new ArrayList<>();
    private static Vector2 gravity = new Vector2(0, -9.81);

    public static void init() {
        spawn(new Ball(new Vector2(5, 25)));
        System.out.println("World initialized");
    }

    public static void spawn(Actor actor) {
        actors.add(actor);
    }

    public static boolean destroy(Actor actor) {
        return actors.remove(actor);
    }

    public static void update() {
        updatePhysics();
        resolveCollisions();
    }

    public static void updatePhysics() {
        for(Actor actor : actors) {
            if(actor instanceof PhysicsActor) {
                PhysicsActor physicsActor = (PhysicsActor) actor;
                if(physicsActor.hasGravity()) {
                    physicsActor.applyForce(gravity.copy().multiply(physicsActor.getMass()));
                }
            }
            actor.update();
        }
    }

    public static void resolveCollisions() {
      for(PhysicsActor actor : getPhysicsActors()) {
        //todo if(!actor.collides()) continue;
        //todo broad phase bounding checks
        for(PhysicsActor other : getPhysicsActors()) {
          if(actor == other) continue;
          //todo if(!other.collides()) continue;
          CollisionResult result = actor.getCollision().testCollision(other.getCollision(), other.getPosition());
          if(result.collision) {
            actor.onCollision(collision);
          }
        }
      }
    }

    private static List<PhysicsActor> getPhysicsActors() {
      List<PhysicsActor> result = new ArrayList<>();
      for(Actor actor : actors) {
        if(actor instanceof PhysicsActor)
          result.add((PhysicsActor)actor);
      }
      return result;
    }

    public static void render(Graphics2D g) {
        //render actors
        for (Actor actor : actors) {
            actor.render(g);
        }
        //render ui
    }
}
