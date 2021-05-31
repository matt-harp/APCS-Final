package me.mattharper.floppy.actor;

import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.util.Vector2;

public class Ball extends PhysicsActor {
    public Ball() {
    }

    public Ball(Vector2 position) {
         this.position = position;
         this.hasGravity = true;
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawCircle(position, 5D);
        g.drawStringWorld(velocity.toString(), position);
    }

    @Override
    public void onCollision(CollisionResult collision) {

    }
}
