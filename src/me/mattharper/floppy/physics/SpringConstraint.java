package me.mattharper.floppy.physics;

import me.mattharper.floppy.actor.PhysicsActor;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.util.Vector2;

public class SpringConstraint extends PhysicsConstraint {
    protected float springConstant = 150;
    protected float springLength = 15f;

    public SpringConstraint(PhysicsActor owner, PhysicsActor constrainedActor) {
        super(owner, constrainedActor);
    }

    @Override
    public void update() {
        double displacement = constrainedActor.getPosition().distance(owner.getPosition()) - springLength;
        Vector2 direction = owner.getPosition().copy().subtract(constrainedActor.getPosition()).normalized();
        double force = springConstant*displacement;
        constrainedActor.applyForce(direction.multiply(force));
        parentActor.applyForce(direction.multiply(force).multiply(-1));
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawLine(owner.getPosition(), constrainedActor.getPosition());
    }
}
