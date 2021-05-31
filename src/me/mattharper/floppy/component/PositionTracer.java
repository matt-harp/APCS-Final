package me.mattharper.floppy.component;

import me.mattharper.floppy.actor.Actor;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.util.CircularBuffer;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class PositionTracer extends Component {
    CircularBuffer<Vector2> history;

    public PositionTracer(Actor owner, int size) {
        super(owner);
        history = new CircularBuffer<>(size);
    }

    @Override
    public void update() {
        history.push(owner.getPosition().copy());
    }

    @Override
    public void render(GraphicsContext g) {
        g.setColor(Color.RED);
        for(Vector2 pos : history) {
            g.drawPoint(pos);
        }
    }
}
