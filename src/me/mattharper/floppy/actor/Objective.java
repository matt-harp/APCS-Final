package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.Rectangle;
import me.mattharper.floppy.util.Vector2;

public class Objective extends PhysicsActor {
    private GameManager gameManager;

    public Objective(World world, GameManager gameManager) {
        super(world);
        this.gameManager = gameManager;
        this.isKinematic = false;
        this.hasGravity = false;
        collision = new Rectangle(new Vector2(-4,  -4), new Vector2(4, 4));
    }

    @Override
    public void onCollision(CollisionResult result) {
        if(result.otherActor instanceof Pointer) return;
        Rectangle rect = (Rectangle) collision;
        Vector2 min = getPosition().copy().add(rect.getMin());
        Vector2 max = getPosition().copy().add(rect.getMax());
        Vector2 pos = result.otherActor.getPosition();
        if(pos.x > min.x && pos.x < max.x
        && pos.y > min.y && pos.y < max.y) {
            gameManager.next();
        }
    }

    @Override
    public boolean overlapOnly(PhysicsActor other) {
        return true;
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawRect(position.copy().add(((Rectangle) collision).getMin()), position.copy().add(((Rectangle) collision).getMax()));
    }
}
