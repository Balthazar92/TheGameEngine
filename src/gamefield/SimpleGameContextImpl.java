package gamefield;

import javax.swing.*;
import java.awt.*;

public class SimpleGameContextImpl extends JPanel implements DrawContext {

    SimpleGameContextImpl() {
        JFrame frame = new JFrame();
        frame.add(this);
    }

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void drawLine() {

    }

    @Override
    public void drawPolygon() {

    }

    @Override
    public void drawCircle() {

    }
}
