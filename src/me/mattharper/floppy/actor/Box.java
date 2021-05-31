package me.mattharper.floppy.actor;

import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.Rectangle;
import me.mattharper.floppy.util.Vector2;

public class Box extends PhysicsActor {
    public Box(Vector2 position, Vector2 minExtent, Vector2 maxExtent) { // [Rubric A] overloaded constructor
        this.position = position;
        this.collision = new Rectangle(minExtent, maxExtent);
        this.hasGravity = false;
        this.mass = 1000;
    }

    @Override
    public void update() {
        super.update();
        double airResistance = velocity.magnitude();
        applyForce(velocity.normalized().multiply(-airResistance));
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.fillRect(position.copy().add(((Rectangle) collision).getMin()), position.copy().add(((Rectangle) collision).getMax()));
        g.drawPoint(position);
    }

    @Override
    public void onCollision(CollisionResult collision) {

    }
}
