package game.engine.geometry.figures;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.myutils.Matrix;

public class ConvexPolygon implements Drawable, Movable {
    protected int verticesCount;
    protected Matrix initialVertices;
    protected Matrix vertices;
    protected Matrix centerOfMass = Matrix.createCoords(0, 0);
    protected float angle;

    public ConvexPolygon() {

    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public ConvexPolygon(float[] x, float[] y, int verticesCount) {
        this.verticesCount = verticesCount;
        initialVertices = new Matrix(2, verticesCount);
        for (int i = 0; i < verticesCount; i++) {
            initialVertices.setValue(0, i, x[i]);
            initialVertices.setValue(1, i, y[i]);
        }

        try {
            vertices = initialVertices.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setCenterOfMass(float x, float y) {
        centerOfMass.setCoords(x, y);
    }

    public void setAngle(float angle) {

    }

    public Matrix getRealCoords() {
        Matrix realCoords = new Matrix(vertices.getRowCount(), vertices.getColumnCount());
        for (int i = 0; i < vertices.getRowCount(); i++) {
            for (int j = 0; j < vertices.getColumnCount(); j++) {
                realCoords.setValue(i, j, vertices.getValue(i, j) + centerOfMass.getValue(i));
            }
        }
        return realCoords;
    }

    public Matrix getRealCoords(int index) {
        return getCoords(index).applyLinearCombination(centerOfMass, 1, 1);
    }

    public Matrix getCoords(int vertexNum) {
        return Matrix.createCoords(vertices.getValue(0, vertexNum), vertices.getValue(1, vertexNum));
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
        angle += dAngle;
        for (int i = 0; i < verticesCount; i++) {
            vertices = Matrix.mul(Matrix.getRotateMatrix(angle), initialVertices);
        }
    }

    @Override
    public void draw(DrawContext drawContext) {
        Matrix realCoords = getRealCoords();
        drawContext.drawPolygon(realCoords);
    }
}
