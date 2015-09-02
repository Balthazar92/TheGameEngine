package engine.geometry.collision;

import engine.geometry.figures.ConvexPolygon;

public class Collision {

    private boolean objectArePenetrated = false;
    float penetrationDepth;

    public Collision() {

    }

    public float getPenetrationDepth() {
        return penetrationDepth;
    }

    public boolean isCollision() {
        return objectArePenetrated;
    }

    public void calculateCollision(ConvexPolygon p1, ConvexPolygon p2) {

    }
}
