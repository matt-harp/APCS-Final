package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.Rectangle;
import me.mattharper.floppy.util.Vector2;

public class Box extends PhysicsActor {
    public Box(World world, Vector2 position, Vector2 minExtent, Vector2 maxExtent) { // [Rubric A] overloaded constructor
        super(world);
        this.position = position;
        this.collision = new Rectangle(minExtent, maxExtent);
        this.hasGravity = false;
        this.isKinematic = false;
        this.mass = 10000000;
        this.restitution = 0.94f;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.fillRect(position.copy().add(((Rectangle) collision).getMin()), position.copy().add(((Rectangle) collision).getMax()));
    }

    @Override
    public void onCollision(CollisionResult collision) {

    }
}
