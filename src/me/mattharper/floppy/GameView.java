package me.mattharper.floppy;

import me.mattharper.floppy.physics.PhysicsManager;
import me.mattharper.floppy.physics.Time;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private long lastFrameTime = 0;
    private int c;
    public GameView() {
        Timer gameLoop = new Timer(8, e -> {
            Time.deltaSeconds = (System.currentTimeMillis() - lastFrameTime) / 1000D;
            PhysicsManager.updatePhysics();
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
        g.drawOval(50+c++, 50, 50, 50);
    }
}
