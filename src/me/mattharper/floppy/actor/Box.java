package me.mattharper.floppy.actor;

import me.mattharper.floppy.physics.Rectangle;
import me.mattharper.floppy.util.Vector2;
import me.mattharper.floppy.game.GameView;

import java.awt.*;

public class Box extends PhysicsActor {
    public Box(Vector2 position, Vector2 minExtent, Vector2 maxExtent) {
      this.position = position;
      this.collision = new Rectangle(minExtent, maxExtent);
    }
    @Override
    public void render(Graphics2D g) {
      Vector2 screenPos = GameView.worldToScreen(position);
      Vector2 min = ((Rectangle)collision).getMin();
      Vector2 max = ((Rectangle)collision).getMax();
      g.drawRect((int)min.x, (int)min.y, (int)(max.x-min.x), (int)(max.y-min.y));
    }

    public void onCollision(CollisionResult result) {
      
    }
}
