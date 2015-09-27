package game.engine.geometry.collision;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.geometry.figures.ConvexPolygon;
import game.engine.myutils.Matrix;
import game.engine.myutils.Pair;

import java.util.List;

public class Collision implements Drawable {
    private boolean objectsArePenetrated = false;
    private float penetrationDepth;
    private Matrix normal;
    private Matrix point;
    private CSO cso;

    public Collision() {

    }

    public Collision(ConvexPolygon p1, ConvexPolygon p2) throws Exception {
        calculateCollision(p1, p2);
    }

    public float getPenetrationDepth() {
        return penetrationDepth;
    }

    public boolean isCollision() {
        return objectsArePenetrated;
    }

    public boolean checkBroadPhase(ConvexPolygon p1, ConvexPolygon p2) {
        return true;
    }

    public void calculateCollision(ConvexPolygon p1, ConvexPolygon p2) throws Exception {
        objectsArePenetrated = false;

        if (!checkBroadPhase(p1, p2)) {
            return;
        }

        cso = new CSO(p1, p2);
        point = p2.getCenterOfMass().applyLinearCombination(p1.getCenterOfMass(), 1f, -1f);

        int theNearestVertexNumber = 0;
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

        List<Pair<Integer, Integer>> csoEdge = cso.getCSOEdge(theNearestVertexNumber);

        switch (csoEdge.size()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                throw new Exception("CSOEdge must contains one or two edges from convex polygons.");
        }

        System.out.println(theNearestVertexNumber);
        System.out.println(normal.get(0) + " " + normal.get(1));
    }

    private void calculateEdgeToEdgeContact() {

    }

    private void calculateEdgeToPointContact() {

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
