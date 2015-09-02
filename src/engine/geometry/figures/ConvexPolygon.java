package engine.geometry.figures;

import engine.gamefield.DrawContext;
import engine.gamefield.Drawable;
import engine.myutils.Matrix;
import engine.myutils.Pair;

public class ConvexPolygon implements Drawable, Movable {

    protected int verticesNumber = 0;
    protected Matrix[] vertices;
    protected Matrix centerOfMass;
    protected float angle;

    public ConvexPolygon() {

    }

    public ConvexPolygon(float x, float y) {
        centerOfMass = Matrix.createCoords(x, y);
    }

    @Override
    public void move(float dx, float dy) {
        centerOfMass.applyLinearCombination(Matrix.createCoords(dx, dy), 1, 1);
    }

    @Override
    public void move(Matrix dCoords) {
        centerOfMass.applyLinearCombination(dCoords, 1, 1);
    }

    @Override
    public void rotate(float dAngle) {

    }

    @Override
    public void draw(DrawContext drawContext) {
        drawContext.drawCircle(centerOfMass.get(0), centerOfMass.get(1), 20);
    }
}
