package me.mattharper.floppy.physics;

import me.mattharper.floppy.util.Vector2;

public class Rectangle implements CollisionShape {
    private Vector2 min;
    private Vector2 max;

    public Rectangle(Vector2 min, Vector2 max) {
        this.min = min;
        this.max = max;
    }

    public CollisionResult testCollision(CollisionShape other, Vector2 relativeTo) {
        if (other instanceof Rectangle) {
            return testRect((Rectangle) other, relativeTo);
        }
        else if (other instanceof Point) {
            return testPoint((Point) other, relativeTo);
        }
        return new CollisionResult();
    }

    private CollisionResult testRect(Rectangle other, Vector2 relativeTo) {
        CollisionResult result = new CollisionResult();
        Vector2 otherMin = relativeTo.copy().add(other.min.x, other.min.y);
        Vector2 otherMax = relativeTo.copy().add(other.max.x, other.max.y);
        if (min.x < otherMax.x && max.x > otherMin.x && max.y > otherMin.y && min.y < otherMax.y) {
            double xOverlap = Math.min(max.x, otherMax.x) - Math.max(min.x, otherMin.x);
            double yOverlap = Math.min(max.y, otherMax.y) - Math.max(min.y, otherMin.y);
            Vector2 center = Vector2.getCenter(otherMin, otherMax);
            if(xOverlap > yOverlap) {
                if(center.y > 0) result.normal = new Vector2(0, 1);
                else result.normal = new Vector2(0, -1);
            }
            else {
                if(center.x > 0) result.normal = new Vector2(1, 0);
                else result.normal = new Vector2(-1, 0);
            }
            result.collision = true;
            result.penetration = new Vector2(xOverlap, yOverlap);
        }
        return result;
    }

    private CollisionResult testPoint(Point other, Vector2 relativeTo) {
        CollisionResult result = new CollisionResult();
        Vector2 point = relativeTo.copy().add(other.getOffset());
        if (point.x > min.x && point.x < max.x && point.y > min.y && point.y < max.y) {
            result.collision = true;
            result.normal = point.normalized();
        }
        return result;
    }

    public Vector2 getMin() {
        return min;
    }

    public Vector2 getMax() {
        return max;
    }
}