package me.mattharper.floppy.physics;

import me.mattharper.floppy.actor.PhysicsActor;
import me.mattharper.floppy.component.Component;

public abstract class PhysicsConstraint extends Component {
    protected PhysicsActor parentActor;
    protected PhysicsActor constrainedActor;
    public PhysicsConstraint(PhysicsActor parent, PhysicsActor constrainedActor) {
        super(parent);
        this.parentActor = parent;
        this.constrainedActor = constrainedActor;
    }

    public PhysicsActor getConstrainedActor() {
        return constrainedActor;
    }
    public PhysicsActor getParentActor() {
        return parentActor;
    }
}
