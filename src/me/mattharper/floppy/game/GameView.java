package me.mattharper.floppy.game;

import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.input.Input;
import me.mattharper.floppy.util.FrameListener;
import me.mattharper.floppy.util.Time;
import me.mattharper.floppy.util.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameView extends JPanel {
    private static GameView instance;
    // pixels per meter
    public static int PX_SCALE = 5;
    private long lastFrameTime = System.currentTimeMillis();
    private final Vector2 cameraOffset = new Vector2();
    private final Input input;
    private World currentWorld;
    public static List<FrameListener> endOfFrame = new ArrayList<>(); // [Rubric A] static array
    public GameView() {
        instance = this;
        setDoubleBuffered(true);

        input = new Input();
        addMouseListener(input);
        addMouseMotionListener(input);
        addMouseWheelListener(input);
        input.setupKeybindings(this);

        //setup world view
        currentWorld = new World();
        currentWorld.init();
        Timer gameLoop = new Timer(8, e -> {
            Time.deltaSeconds = (System.currentTimeMillis() - lastFrameTime) / 1000D;
            Time.deltaSeconds *= Time.TIME_SCALE;
            currentWorld.update();
            repaint();
            lastFrameTime = System.currentTimeMillis();
            for(FrameListener listener : endOfFrame) {
                listener.onNewFrame();
            }
            endOfFrame.clear();
        });
        gameLoop.start();

        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "blank");
        setCursor(blank);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsContext context = new GraphicsContext(g);
        context.height = getHeight();
        context.width = getWidth();
        g.drawString("DeltaSeconds: " + Time.deltaSeconds, 10, 10);
        g.drawString("FPS: " + (int)(1 / Time.deltaSeconds), 10, 40);
        currentWorld.render(context);
    }

    public static GameView getInstance() {
        return instance;
    }

    public static Vector2 worldToScreen(Vector2 worldPos) {
        return new Vector2((instance.getWidth() / 2D) + ((worldPos.x - instance.cameraOffset.x) * PX_SCALE), (instance.getHeight() / 2D) - ((worldPos.y - instance.cameraOffset.y) * PX_SCALE));
    }

    public static Vector2 screenToWorld(Vector2 screenPos) {
        return new Vector2(((screenPos.x - (instance.getWidth() / 2D)) / PX_SCALE) + instance.cameraOffset.x, ((-screenPos.y + (instance.getHeight() / 2D)) / PX_SCALE) + instance.cameraOffset.y);
    }

    public Vector2 getCameraOffset() {
        return cameraOffset;
    }
}
