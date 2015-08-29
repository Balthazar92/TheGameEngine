package geometry;

import gamefield.DrawContext;
import gamefield.Drawable;
import util.Pair;

public class ConvexPolygon implements Drawable{
    private int verticesNumber;
    private Pair<Float, Float>[] vertices;

    ConvexPolygon(int verticesNumber) {
        this.verticesNumber = verticesNumber;
    }

    @Override
    public void draw(DrawContext drawContext) {
        drawContext.drawLine();
    }
}
