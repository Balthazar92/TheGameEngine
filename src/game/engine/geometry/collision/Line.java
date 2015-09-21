package game.engine.geometry.collision;

import game.engine.myutils.Matrix;

public class Line {
    private float[] coeffs = new float[3];
    private float sqrt;

    public Line(Matrix point1, Matrix point2) {
        coeffs[0] = point1.get(1) - point2.get(1);
        coeffs[1] = point2.get(0) - point1.get(0);
        coeffs[2] = point1.get(0) * point2.get(1) - point1.get(1) * point2.get(0);
        sqrt = (float) Math.sqrt(coeffs[0] * coeffs[0] + coeffs[1] * coeffs[1]);
    }

    public float getValueOfExpression(Matrix point) {
        float answer = coeffs[2];
        for (int i = 0; i < 2; i++) {
            answer += coeffs[i] * point.get(i);
        }
        return answer;
    }

    public float getDistanceToPoint(Matrix point) {
        return Math.abs(getValueOfExpression(point)) / sqrt;
    }
}
