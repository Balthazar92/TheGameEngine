package engine.geometry.figures;

import engine.myutils.Matrix;

public interface Movable {
    void move(float dx, float dy);
    void move(Matrix dCoords);
    void rotate(float dAngle);
}
