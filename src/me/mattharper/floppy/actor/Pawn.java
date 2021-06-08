package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class Pawn extends Box { // [Rubric C] multi-level inheritance
    public Pawn(World world, Vector2 position, Vector2 min, Vector2 max) {
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
    }
}
