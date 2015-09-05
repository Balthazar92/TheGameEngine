package test;

import game.engine.gamefield.DrawContext;
import game.engine.myutils.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class SimpleGameContextImpl extends JFrame implements DrawContext {

    private Graphics currGraphics = null;
    private BufferStrategy bf = null;

    public SimpleGameContextImpl() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        createBufferStrategy(2);
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        currGraphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public void drawPolygon(Matrix coords) {
        int coordsCount = coords.getColumnCount();
        int[] xPoints = new int[coordsCount];
        int[] yPoints = new int[coordsCount];
        for (int i = 0; i < coordsCount; i++) {
            xPoints[i] = (int) coords.getValue(0, i);
            yPoints[i] = (int) coords.getValue(1, i);
        }
        currGraphics.drawPolygon(xPoints, yPoints, 4);
    }

    @Override
    public void drawCircle(float x, float y, float r) {
        currGraphics.drawOval((int) (x - r), (int) (y - r), (int) (2.0 * r), (int) (2.0 * r));
    }

    @Override
    public void startRendering() {
        bf = getBufferStrategy();
        currGraphics = bf.getDrawGraphics();
        currGraphics.setColor(new Color(255, 255, 255));
        currGraphics.fillRect(0, 0, getWidth(), getHeight());
        currGraphics.setColor(new Color(0, 0, 0));
    }

    @Override
    public void endRendering() {
        currGraphics.dispose();
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }
}
