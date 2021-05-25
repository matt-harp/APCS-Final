package me.mattharper.floppy.actor;

import me.mattharper.floppy.physics.AABB;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class Box extends PhysicsActor {
    public Box() {
        this.boundingBox = new AABB(new Vector2(1, 1), new Vector2(1.5, 1.5));
    }
    @Override
    public void render(Graphics2D g) {

    }
}
