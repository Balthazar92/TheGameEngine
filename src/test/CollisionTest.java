package test;

import game.engine.gamefield.Drawable;
import game.engine.gamefield.GameField;
import game.engine.geometry.collision.CSO;
import game.engine.geometry.collision.Collision;
import game.engine.geometry.figures.ConvexPolygon;
import game.engine.geometry.figures.ShapeFactory;

import java.util.ArrayList;
import java.util.List;

public class CollisionTest {
    public static void main(String args[]) {
        List<Drawable> gameObjects = new ArrayList<Drawable>();

        float[] xs1 = {0f, -40f, 40f};
        float[] ys1 = {50f, -50f, -50f};

        float[] xs2 = {0f, -20f, 40f};
        float[] ys2 = {50f, -50f, -50f};

        gameObjects.add(new ConvexPolygon(xs1, ys1, 3));
        ((ConvexPolygon) gameObjects.get(0)).rotate(0.3f);
        gameObjects.add(new ConvexPolygon(xs2, ys2, 3));
        ((ConvexPolygon) gameObjects.get(1)).rotate(-0.2f);
        ((ConvexPolygon) gameObjects.get(0)).move(200f, 200f);
        ((ConvexPolygon) gameObjects.get(1)).move(200f, 290f);
        gameObjects.add(new Collision((ConvexPolygon) gameObjects.get(0), (ConvexPolygon) gameObjects.get(1)));



        SimpleGameContextImpl contextImp = new SimpleGameContextImpl();
        GameField gameField = new GameField(contextImp);
        gameField.setObjectsToDraw(gameObjects);

        Thread renderThread = new Thread(gameField);
        renderThread.start();
    }
}
