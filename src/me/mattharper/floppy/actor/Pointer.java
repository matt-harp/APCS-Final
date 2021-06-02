package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.GameView;
import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.input.Input;
import me.mattharper.floppy.input.InputBind;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.Point;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class Pointer extends PhysicsActor {
    private PhysicsActor draggedActor;
    public Pointer(World world) {
        super(world);
        this.hasGravity = false;
        this.isKinematic = false;
        collision = new Point();
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.setColor(Color.BLACK);
        g.drawPoint(position);
    }

    @Override
    public void update() {
        super.update();
        this.position = GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY));
        if(Input.isInputJustReleased(InputBind.MOUSE_LEFT)) {
            draggedActor = null;
        }
        if(draggedActor != null) {
            Vector2 direction = draggedActor.getPosition().copy().subtract(position).multiply(-1).normalized();
            double distance = draggedActor.getPosition().distance(position);
            draggedActor.setVelocity(direction.multiply(distance*5));
        }
    }

    @Override
    public boolean canCollideWith(PhysicsActor other) {
        return true;
    }

    @Override
    public void onCollision(CollisionResult collision) {
        if(Input.isInputHeld(InputBind.MOUSE_LEFT) && draggedActor == null) {
            draggedActor = collision.otherActor;
        }
    }
}
