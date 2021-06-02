package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DebugLog extends Actor {
    public static final List<DebugMessage> messages = new ArrayList<>();
    public DebugLog(World world) {
        super(world);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        Vector2 pos = new Vector2(150, 10);
        for (DebugMessage message : messages) {
            g.setColor(message.color);
            g.drawString(message.message, pos);
            pos.add(0, 15);
        }
        messages.removeIf(m -> m.lifetime < System.currentTimeMillis());
    }

    public static class DebugMessage {
        public Color color;
        public String message;
        public long lifetime;

        public DebugMessage(String message) {
            this.message = message;
            this.color = Color.BLACK;
            this.lifetime = System.currentTimeMillis() + 5000;
        }

        public DebugMessage(String message, Color color, int secondsToDisplay) {
            this.message = message;
            this.color = color;
            this.lifetime = System.currentTimeMillis() + secondsToDisplay * 1000L;
        }
    }
}
