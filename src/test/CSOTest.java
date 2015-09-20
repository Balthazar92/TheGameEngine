package test;

import game.engine.gamefield.GameField;
import game.engine.geometry.collision.CSO;
import game.engine.geometry.figures.ConvexPolygon;
import game.engine.geometry.figures.ShapeFactory;

import java.util.ArrayList;
import java.util.List;

public class CSOTest {
    public static void main(String args[]) {
        List<ConvexPolygon> gameObjects = new ArrayList<ConvexPolygon>();

        float[] xs1 = {0f, 0f, 200f};
        float[] ys1 = {0f, 50f, 0f};

        float[] xs2 = {0f, 0f, 100f};
        float[] ys2 = {0f, 80f, 0f};

        gameObjects.add(new ConvexPolygon(xs1, ys1, 3));
        gameObjects.get(0).move(300f, 160f);
        gameObjects.add(new ConvexPolygon(xs2, ys2, 3));
        gameObjects.get(1).move(200f, 200f);
        gameObjects.get(0).rotate(0.5f);
        gameObjects.get(1).rotate(0.2f);
        gameObjects.add(new CSO(gameObjects.get(0), gameObjects.get(1)));

        SimpleGameContextImpl contextImp = new SimpleGameContextImpl();
        GameField gameField = new GameField(contextImp);
        gameField.setObjectsToDraw(gameObjects);

        Thread renderThread = new Thread(gameField);
        renderThread.start();

//        for (ConvexPolygon gameObject : gameObjects) {
//            gameObject.rotate(0.2f);
//        }

        while (true) {
            try {
//                gameObjects.get(0).rotate(-0.05f);
//                gameObjects.get(1).rotate(0.01f);
//                new CSO(gameObjects.get(0), gameObjects.get(1));
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
