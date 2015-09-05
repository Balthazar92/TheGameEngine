package game.engine.geometry.figures;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.myutils.Matrix;

public class ConvexPolygon implements Drawable, Movable {

    protected int verticesCount;
    protected Matrix[] vertices;
    protected Matrix centerOfMass = Matrix.createCoords(0, 0);
    protected float angle;

    public ConvexPolygon() {

    }

    public ConvexPolygon(float[] x, float[] y, int verticesCount) {
        this.verticesCount = verticesCount;
        vertices = new Matrix[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            vertices[i] = Matrix.createCoords(x[i], y[i]);
        }
    }

    public void setCenterOfMass(float x, float y) {
        centerOfMass.setCoords(x, y);
    }

    public void setAngle(float angle) {

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
        for (int i = 0; i < verticesCount; i++) {
            vertices[i] = Matrix.multipl(Matrix.getRotateMatrix(dAngle), vertices[i]);
        }
    }

    @Override
    public void draw(DrawContext drawContext) {
        drawContext.drawPolygon(vertices);
    }
}
