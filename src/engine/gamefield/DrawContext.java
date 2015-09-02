package engine.gamefield;

public interface DrawContext {
    void drawLine(float x1, float y1, float x2, float y2);

    void drawPolygon();

    void drawCircle(float x, float y, float r);

    void startRendering();

    void endRendering();
}
