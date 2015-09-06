package game.engine.game.objects;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.geometry.figures.ConvexPolygon;

import java.util.AbstractList;
import java.util.ArrayList;

public class GameObject implements Drawable {
    private ConvexPolygon shape = null;

    protected GameObject parent = null;
    protected AbstractList<GameObject> child = null;

    GameObject() {

    }

    GameObject(ConvexPolygon shape, GameObject parent) {
        this.shape = shape;
        this.parent = parent;
    }

    public void addChild(GameObject join, GameObject gameObject) {
        if (child == null) {
            child = new ArrayList<GameObject>();
        }
    }

    @Override
    public void draw(DrawContext drawContext) {
        if (shape != null) {
            shape.draw(drawContext);
        }

        for (GameObject gameObject : child) {
            gameObject.draw(drawContext);
        }
    }
}
