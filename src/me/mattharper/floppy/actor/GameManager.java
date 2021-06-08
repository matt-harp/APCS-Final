package me.mattharper.floppy.actor;

import me.mattharper.floppy.game.World;
import me.mattharper.floppy.graphics.GraphicsContext;
import me.mattharper.floppy.util.Vector2;

import java.awt.*;

public class GameManager extends Actor {
    Objective objective;
    Pawn pawn;
    private boolean started;
    private boolean done;
    private long timeEnd;
    private int points;
    public GameManager(World world) {
        super(world);
        objective = new Objective(world, this);
        pawn = new Pawn(world, new Vector2(15, 0), new Vector2(-3, -3), new Vector2(3, 3));
        world.spawn(objective);
        world.spawn(pawn);
    }

    public void next() {
        if(!started) {
            started = true;
            timeEnd = System.currentTimeMillis() + (15 * 1000);
        }
        objective.position = new Vector2((Math.random() * 180) - 90, (Math.random() * 180) - 90);
        if(!done)
            points++;
    }

    @Override
    public void update() {
        super.update();
        if(started && System.currentTimeMillis() > timeEnd) {
            done = true;
        }
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        if(!started) {
            g.drawStringWorld("Get as many points as you can in 15 seconds!", new Vector2(-25, 15));
            g.drawStringWorld("Collect points by overlapping the red box with the black outlines!", new Vector2(-35, 10));
        }
        else if(!done) {
            long timeLeft = timeEnd - System.currentTimeMillis();
            if(timeLeft < 5000) g.setColor(Color.RED);
            g.drawString("Time Left: " + String.format("%.2f", timeLeft / 1000f), new Vector2(g.width/2f - 10, 80));
            g.setColor(Color.BLACK);
            g.drawString("Points: " + points, new Vector2(g.width/2f - 5, 95));
        }
        else {
            g.drawString("Time's up!", new Vector2(g.width/2f - 10, g.height/2f));
            g.drawString("Your points: " + points, new Vector2(g.width/2f - 20, g.height/2f + 10));
        }
    }
}
