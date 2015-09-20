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
        gameObjects.add(ShapeFactory.createRectangle(90, 90, 150, 120, 0));
        gameObjects.add(ShapeFactory.createRectangle(80, 60, 150, 200, 0));
        gameObjects.get(0).rotate(0.2f);
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

        while(true) {
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
