package game.engine.geometry.figures;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.myutils.Matrix;

public class ConvexPolygon implements Drawable, Movable {
    public static final float doublePI = (float) (2.0 * Math.PI);
    protected int verticesCount;
    protected Matrix[] initialVertices;
    protected Matrix[] vertices;
    protected Matrix centerOfMass = Matrix.createCoords(0, 0);
    protected float angle;
    protected Matrix rightTopPoint = Matrix.createCoords(0, 0);
    protected Matrix leftBottomPoint = Matrix.createCoords(0, 0);

    public ConvexPolygon() {

    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public Matrix getRightTopPoint() {
        return new Matrix(rightTopPoint);
    }

    public Matrix getLeftBottomPoint() {
        return new Matrix(leftBottomPoint);
    }

    public ConvexPolygon(float[] x, float[] y, int verticesCount) {
        this.verticesCount = verticesCount;
        initialVertices = new Matrix[verticesCount];
        vertices = new Matrix[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            initialVertices[i] = Matrix.createCoords(x[i], y[i]);
            vertices[i] = Matrix.createCoords(x[i], y[i]);
        }
        calculateOuterRectangleBorders();
    }

    public void setCenterOfMass(float x, float y) {
        centerOfMass.setCoords(x, y);
    }

    public void setCenterOfMass(Matrix centerOfMass) {
        this.centerOfMass = new Matrix(centerOfMass);
    }

    public Matrix getCenterOfMass() {
        return new Matrix(centerOfMass);
    }

    public void setAngle(float angle) {

    }

    public float getAngle() {
        return angle;
    }

    public Matrix[] getRealCoords() {
        Matrix[] realCoords = new Matrix[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            realCoords[i] = getRealCoords(i);
        }
        return realCoords;
    }

    public Matrix getRealCoords(int vertexNumber) {
        return getRealCoords(vertices[vertexNumber]);
    }

    public Matrix getRealCoords(Matrix coords) {
        return Matrix.getLinearCombination(coords, centerOfMass, 1, 1);
    }

    public Matrix getCoords(int vertexNum) {
        return new Matrix(vertices[vertexNum]);
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

        if (angle < 0f) {
            angle += doublePI;
        } else if (angle > doublePI) {
            angle -= doublePI;
        }

        for (int i = 0; i < verticesCount; i++) {
            vertices[i] = Matrix.mul(Matrix.getRotateMatrix(angle), initialVertices[i]);
        }
        calculateOuterRectangleBorders();
    }

    protected void calculateOuterRectangleBorders() {
        Matrix rightTopPoint = Matrix.createCoords(Float.MIN_VALUE, Float.MIN_VALUE);
        Matrix leftBottomPoint = Matrix.createCoords(Float.MAX_VALUE, Float.MAX_VALUE);
        for (int i = 0; i < verticesCount; i++) {
            float x = vertices[i].getValue(0);
            float y = vertices[i].getValue(1);
            if (x > rightTopPoint.getValue(0)) {
                rightTopPoint.setValue(0, x);
            }
            if (y > rightTopPoint.getValue(1)) {
                rightTopPoint.setValue(1, y);
            }
            if (x < leftBottomPoint.getValue(0)) {
                leftBottomPoint.setValue(0, x);
            }
            if (y < leftBottomPoint.getValue(1)) {
                leftBottomPoint.setValue(1, y);
            }
        }
        this.rightTopPoint = rightTopPoint;
        this.leftBottomPoint = leftBottomPoint;
    }

    private void drawRectangle(DrawContext drawContext) {
        Matrix a = getRealCoords(leftBottomPoint);
        Matrix b = getRealCoords(rightTopPoint);
        drawContext.drawRect(a.getValue(0), a.getValue(1), b.getValue(0), b.getValue(1));
    }

    @Override
    public void draw(DrawContext drawContext) {

//        for (int i = 0; i < verticesCount; i++) {
//            Matrix realCoords = getRealCoords(i);
//            drawContext.drawCircle(realCoords.getValue(0), realCoords.getValue(1), 4);
//        }

        drawContext.drawPolygon(getRealCoords());
//        drawRectangle(drawContext);
    }
}
