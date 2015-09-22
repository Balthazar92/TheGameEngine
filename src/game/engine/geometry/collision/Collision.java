package game.engine.geometry.collision;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.geometry.figures.ConvexPolygon;
import game.engine.myutils.Matrix;

public class Collision implements Drawable {
    private boolean objectsArePenetrated = false;
    private float penetrationDepth;
    private Matrix normal;
    private Matrix point;
    private CSO cso;

    public Collision(ConvexPolygon p1, ConvexPolygon p2) {
        calculateCollision(p1, p2);
    }

    public float getPenetrationDepth() {
        return penetrationDepth;
    }

    public boolean isCollision() {
        return objectsArePenetrated;
    }

    private void calculateCollision(ConvexPolygon p1, ConvexPolygon p2) {
        cso = new CSO(p1, p2);
        point = p1.getCenterOfMass().applyLinearCombination(p2.getCenterOfMass(), -1f, 1f);

        int theNearestVertexNumber = 0;
        objectsArePenetrated = false;
        penetrationDepth = cso.getLine(0).getDistanceToPoint(point);

        for (int i = 0; i < cso.getVerticesCount(); i++) {
            float distance = cso.getLine(i).getDistanceToPoint(point);
            if (distance < 0) {
                return;
            } else if (penetrationDepth > distance) {
                penetrationDepth = distance;
                theNearestVertexNumber = i;
            }
        }

        objectsArePenetrated = true;
        normal = cso.getLine(theNearestVertexNumber).getNormal();


    }

    @Override
    public void draw(DrawContext drawContext) {
        Matrix d = Matrix.createCoords(400f, 300f);
        cso.move(d.get(0), d.get(1));

        cso.draw(drawContext);

        cso.move(-d.get(0), -d.get(1));

        Matrix shiftPoint = Matrix.getLinearCombination(point, d, 1f, 1f);
        drawContext.drawCircle(shiftPoint.get(0), shiftPoint.get(1), 4f);
    }
}
