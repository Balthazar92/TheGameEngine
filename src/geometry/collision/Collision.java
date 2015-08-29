package geometry.collision;

import geometry.figures.ConvexPolygon;

public class Collision {

    private boolean objectArePenetrated = false;

    public Collision() {

    }

    public boolean isCollision() {
        return objectArePenetrated;
    }

    public void calculateCollision(ConvexPolygon p1, ConvexPolygon p2) {

    }
}
