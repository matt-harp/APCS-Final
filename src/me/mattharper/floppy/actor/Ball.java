package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.GameView;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class Ball extends PhysicsActor {
    public Ball() {
    }

    public Ball(Vector2 position) {
         this.position = position;
         this.hasGravity = true;
    }

    @Override
    public void render(Graphics2D g) {
        //todo dont make actors responsible for world to screen
        Vector2 worldPos = GameView.worldToScreen(position);
        g.drawOval((int)worldPos.x - (15/2), (int)worldPos.y - (15/2), 15, 15);
        g.drawString(velocity.toString(), (int)worldPos.x, (int)worldPos.y);
    }
}
