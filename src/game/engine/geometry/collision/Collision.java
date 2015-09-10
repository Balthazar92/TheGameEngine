package game.engine.geometry.collision;

import game.engine.geometry.figures.ConvexPolygon;

public class Collision {

    private boolean objectsArePenetrated = false;
    private float penetrationDepth;

    public Collision() {

    }

    public float getPenetrationDepth() {
        return penetrationDepth;
    }

    public boolean isCollision() {
        return objectsArePenetrated;
    }

    public void calculateCollision(ConvexPolygon p1, ConvexPolygon p2) {

    }
}
