package me.mattharper.floppy.actor;

import me.mattharper.floppy.component.PositionTracer;
import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.physics.CollisionResult;
import me.mattharper.floppy.physics.SpringConstraint;
import me.mattharper.floppy.util.Vector2;

public class BouncyBox extends Box { // [Rubric C] multi-level inheritance
    SpringConstraint constraint;
    PositionTracer tracer;

    public BouncyBox(World world, Vector2 position, Vector2 min, Vector2 max) {
        super(world, position, min, max); // [Rubric B] super keyword
        this.hasGravity = true;
        this.mass = 10;
        this.velocity = new Vector2(0, -10);
        Box anchor = new Box(world, position.copy().add(0, 10), min, max);
        world.spawn(anchor);
        constraint = new SpringConstraint(anchor, this);
        tracer = new PositionTracer(this, 250);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawLine(position, constraint.getOwner().getPosition());
    }

    @Override
    public void onCollision(CollisionResult result) {
        if(result.otherActor instanceof Pointer) return;
        position.add(result.direction.copy().multiply(result.penetration));
        this.applyForceInstant(result.direction.multiply(1));
    }
}
