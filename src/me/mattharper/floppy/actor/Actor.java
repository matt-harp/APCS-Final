package me.mattharper.floppy.actor;

import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public abstract class Actor {
    protected Vector2 position = Vector2.ZERO;

    public abstract void update();

    public abstract void render(Graphics2D g);

    public Vector2 getPosition() {
      return position;
    }

    public String toString() {
        return "[Actor at " + position + "]";
    }
}
