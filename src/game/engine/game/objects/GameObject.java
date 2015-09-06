package game.engine.game.objects;

import game.engine.gamefield.DrawContext;
import game.engine.gamefield.Drawable;
import game.engine.geometry.figures.ConvexPolygon;

import java.util.ArrayList;
import java.util.Collection;

public class GameObject implements Drawable {
    private ConvexPolygon shape = null;
    private Collection<GameObject> childs = null;

    GameObject() {

    }

    GameObject(ConvexPolygon shape) {
        this.shape = shape;
    }

    public void addChild(GameObject join, GameObject gameObject) {
        if (childs == null) {
            childs = new ArrayList<GameObject>();
        }
    }

    @Override
    public void draw(DrawContext drawContext) {
        if (shape != null) {
            shape.draw(drawContext);
        }

        for (GameObject gameObject : childs) {
            gameObject.draw(drawContext);
        }
    }
}
