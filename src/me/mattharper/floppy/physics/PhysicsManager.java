package me.mattharper.floppy.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsManager {
    private static final List<PhysicsActor> actors = new ArrayList<>();
    public static void updatePhysics() {
        for(PhysicsActor actor : actors) {
            actor.update();
        }
    }
    public static void spawn(PhysicsActor actor) {
        actors.add(actor);
    }
    public static boolean destroy(PhysicsActor actor) {
        return actors.remove(actor);
    }
}
