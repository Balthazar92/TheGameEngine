package geometry;

import gamefield.DrawContext;
import gamefield.Drawable;
import myutils.Pair;

public class ConvexPolygon implements Drawable {
    private int verticesNumber;
    private Pair<Float, Float>[] vertices;
    private Pair<Float, Float> centerOfMass;

    public ConvexPolygon(float x, float y) {
        centerOfMass = new Pair<Float, Float>(x, y);
    }

    public void move(float dx, float dy) {
        centerOfMass.a += dx;
        centerOfMass.b += dy;
    }

    @Override
    public void draw(DrawContext drawContext) {
        drawContext.drawCircle(centerOfMass.a, centerOfMass.b, 20);
    }
}
