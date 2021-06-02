package me.mattharper.floppy.actor;

import me.mattharper.floppy.component.Component;
import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor { // [Rubric B] super class [Rubric C] abstract class
    protected final List<Component> components = new ArrayList<>();
    protected Vector2 position = new Vector2();
    protected final World world;

    public Actor(World world) {
        this.world = world;
    }

    public void init() { }

    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

    public void render(GraphicsContext g) {
        for (Component component : components) {
            component.render(g);
        }
    };

    public List<Component> getComponents() {
        return components;
    }

    public Vector2 getPosition() {
      return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String toString() {
        return "[Actor at " + position + "]";
    }
}
