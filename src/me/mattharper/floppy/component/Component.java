package me.mattharper.floppy.component;


import me.mattharper.floppy.actor.Actor;
import me.mattharper.floppy.graphics.GraphicsContext;

public abstract class Component {
    protected final Actor owner;

    public Component(Actor owner) {
        this.owner = owner;
        owner.getComponents().add(this);
    }

    public abstract void update();

    public void render(GraphicsContext g) { }

    public Actor getOwner() {
        return owner;
    }
}
