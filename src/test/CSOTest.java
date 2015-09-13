package test;

import game.engine.game.objects.GameObject;
import game.engine.gamefield.GameField;
import game.engine.geometry.collision.CSO;
import game.engine.geometry.figures.ConvexPolygon;
import game.engine.geometry.figures.ShapeFactory;

import java.util.ArrayList;
import java.util.List;

public class CSOTest
{
    public static void main(String args[]) {
        List<ConvexPolygon> gameObjects = new ArrayList<ConvexPolygon>();
        gameObjects.add(ShapeFactory.createRectangle(20, 300, 200, 200, 0));
        gameObjects.add(ShapeFactory.createRectangle(20, 300, 300, 300, 0));

        SimpleGameContextImpl contextImp = new SimpleGameContextImpl();
        GameField gameField = new GameField(contextImp);
        gameField.setObjectsToDraw(gameObjects);

        Thread renderThread = new Thread(gameField);
        renderThread.start();

        new CSO(gameObjects.get(0), gameObjects.get(1));

//        while(true) {
//            try {
//                for (ConvexPolygon gameObject : gameObjects) {
//                    gameObject.rotate(0.01f);
//                }
//                new CSO(gameObjects.get(0), gameObjects.get(1));
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
