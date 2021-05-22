package me.mattharper.floppy.game;

import me.mattharper.floppy.util.Vector2;
import me.mattharper.floppy.util.Time;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private static GameView instance;
    // pixels per meter
    static final int PX_SCALE = 10;
    private long lastFrameTime = System.currentTimeMillis();
    private Vector2 cameraOffset = new Vector2(10, 0);
    public GameView() {
        instance = this;
        setDoubleBuffered(true);
        World.init();
        Timer gameLoop = new Timer(2, e -> {
            Time.deltaSeconds = (System.currentTimeMillis() - lastFrameTime) / 1000D;
            World.update();
            repaint();
            lastFrameTime = System.currentTimeMillis();
        });
        //setup world view
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("DeltaSeconds: " + Time.deltaSeconds, 10, 10);
        g.drawString("FPS: " + (int)(1 / Time.deltaSeconds), 10, 40);
        World.render((Graphics2D) g);
    }

    public static GameView getInstance() {
        return instance;
    }

    public static Vector2 worldToScreen(Vector2 worldPos) {
        return new Vector2((worldPos.x * PX_SCALE) - instance.getCameraOffset().x, (instance.getHeight() - (worldPos.y * PX_SCALE)) - instance.getCameraOffset().y);
    }

//    public static Vector2 screenToWorld(Vector2 screenPos) {
////        return new Vector2(worldPos.x * PX_SCALE, (instance.getHeight() - (worldPos.y * PX_SCALE)));
//    }

    public Vector2 getCameraOffset() {
        return cameraOffset;
    }
}
