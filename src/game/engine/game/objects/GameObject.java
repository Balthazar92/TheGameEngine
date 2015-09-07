package game.engine.game.objects;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.geometry.figures.ConvexPolygon;

import java.util.AbstractList;
import java.util.ArrayList;

public class GameObject implements Drawable {
    protected ConvexPolygon shape = null;

    protected GameObject parent = null;
    protected AbstractList<GameObject> children = null;

    GameObject() {
    }

    GameObject(ConvexPolygon shape, GameObject parent) {
        this.shape = shape;
        this.parent = parent;
    }

    public ConvexPolygon getShape() {
        return shape;
    }

    protected void updateThisOne() {

    }

    public void update() {
        updateThisOne();
        for (GameObject gameObject : children) {
            gameObject.update();
        }
    }

    public void addChild(GameObject child) {
        if (children == null) {
            children = new ArrayList<GameObject>();
        }
        children.add(child);
    }

    protected void drawThisOne(DrawContext drawContext) {
        if (shape != null) {
            shape.draw(drawContext);
        }
    }

    @Override
    public void draw(DrawContext drawContext) {
        drawThisOne(drawContext);
        for (GameObject gameObject : children) {
            gameObject.draw(drawContext);
        }
    }
}
