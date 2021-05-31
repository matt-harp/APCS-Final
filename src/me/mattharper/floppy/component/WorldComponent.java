package me.mattharper.floppy.component;

import me.mattharper.floppy.actor.Actor;
import me.mattharper.floppy.util.Vector2;

public class WorldComponent extends Component {
    private Vector2 position = new Vector2();

    public WorldComponent(Actor owner) {
        super(owner);
    }

    public WorldComponent(Actor owner, Vector2 position) {
        super(owner);
        this.position = position;
    }

    @Override
    public void update() {
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
