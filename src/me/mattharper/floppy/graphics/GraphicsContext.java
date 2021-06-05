package me.mattharper.floppy.graphics;

import me.mattharper.floppy.game.GameView;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class GraphicsContext {
    private final Graphics2D g2d;
    public GraphicsContext(Graphics g) {
        g2d = (Graphics2D) g;
    }

    public Graphics2D getG2D() {
        return g2d;
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }

    public void drawPoint(Vector2 worldPos) {
        Vector2 screen = GameView.worldToScreen(worldPos);
        g2d.fillOval((int)screen.x, (int)screen.y, 5, 5);
    }

    public void drawPointScreen(Vector2 position) {
        g2d.fillOval((int)position.x, (int)position.y, 3, 3);
    }

    public void drawCircle(Vector2 position, double radius) {
        Vector2 screen = GameView.worldToScreen(position);
        g2d.drawOval((int)screen.x, (int)screen.y, (int)radius / GameView.PX_SCALE, (int)radius / GameView.PX_SCALE);
    }

    public void fillCircle(Vector2 position, double radius) {
        Vector2 screen = GameView.worldToScreen(position);
        g2d.fillOval((int)screen.x, (int)screen.y, (int)radius / GameView.PX_SCALE, (int)radius / GameView.PX_SCALE);
    }

    public void drawOval(Vector2 position, double width, double height) {
        Vector2 screen = GameView.worldToScreen(position);
        g2d.drawOval((int)screen.x, (int)screen.y, (int)width / GameView.PX_SCALE, (int)height / GameView.PX_SCALE);
    }

    public void fillOval(Vector2 position, double width, double height) {
        Vector2 screen = GameView.worldToScreen(position);
        g2d.fillOval((int)screen.x, (int)screen.y, (int)width / GameView.PX_SCALE, (int)height / GameView.PX_SCALE);
    }

    public void drawStringWorld(String string, Vector2 position) {
        Vector2 screen = GameView.worldToScreen(position);
        g2d.drawString(string, (int)screen.x, (int)screen.y);
    }

    public void drawString(String string, Vector2 position) {
        g2d.drawString(string, (int)position.x, (int)position.y);
    }

    public void fillRect(Vector2 min, Vector2 max) {
        Vector2 minScreen = GameView.worldToScreen(min);
        Vector2 maxScreen = GameView.worldToScreen(max);
        int minX = (int) Math.min(minScreen.x, maxScreen.x);
        int minY = (int) Math.min(minScreen.y, maxScreen.y);
        int maxX = (int) Math.max(minScreen.x, maxScreen.x);
        int maxY = (int) Math.max(minScreen.y, maxScreen.y);
        g2d.fillRect(minX, minY, maxX-minX, maxY-minY);
    }

    public void drawLine(Vector2 position1, Vector2 position2) {
        Vector2 screen1 = GameView.worldToScreen(position1);
        Vector2 screen2 = GameView.worldToScreen(position2);
        g2d.drawLine((int)screen1.x, (int)screen1.y, (int)screen2.x, (int)screen2.y);
    }
}
