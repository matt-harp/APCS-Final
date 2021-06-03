package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.GameView;
import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.input.Input;
import me.mattharper.floppy.input.InputAxis;
import me.mattharper.floppy.input.InputBind;
import me.mattharper.floppy.util.Vector2;

public class CameraController extends Actor {
    private static final float SENSITIVITY = 3f;

    public CameraController(World world) {
        super(world);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void update() {
        if(Input.isInputHeld(InputBind.MOUSE_MIDDLE))
            GameView.getInstance().getCameraOffset().add(Input.getAxis(InputAxis.MOUSE_X) * -SENSITIVITY * (1D/GameView.PX_SCALE), Input.getAxis(InputAxis.MOUSE_Y) * SENSITIVITY * (1D/GameView.PX_SCALE));
        if(Input.getAxis(InputAxis.MOUSE_WHEEL) > 0) {
            Vector2 before = GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY));
            GameView.PX_SCALE--;
            if(GameView.PX_SCALE <= 0)
                GameView.PX_SCALE = 1;
            else {
                Vector2 after = GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY));
                double dx = after.x - before.x;
                double dy = after.y - before.y;
                GameView.getInstance().getCameraOffset().add(-dx, -dy);
            }
        }
        if(Input.getAxis(InputAxis.MOUSE_WHEEL) < 0) {
            Vector2 before = GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY));
            GameView.PX_SCALE++;
            Vector2 after = GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY));
            double dx = after.x - before.x;
            double dy = after.y - before.y;
            GameView.getInstance().getCameraOffset().add(-dx, -dy);
        }
        if(Input.isInputHeld(InputBind.CAMERA_LEFT)) {
            GameView.getInstance().getCameraOffset().add(-1, 0);
        }
        if(Input.isInputHeld(InputBind.CAMERA_RIGHT)) {
            GameView.getInstance().getCameraOffset().add(1, 0);
        }
    }

    @Override
    public void render(GraphicsContext g) {
        g.drawString(new Vector2(Input.mouseX, Input.mouseY).toString(), new Vector2(10, 70));
        g.drawString(GameView.screenToWorld(new Vector2(Input.mouseX, Input.mouseY)).toString(), new Vector2(10, 85));
        g.drawString("SCALE: " + GameView.PX_SCALE, new Vector2(10, 100));
    }
}
