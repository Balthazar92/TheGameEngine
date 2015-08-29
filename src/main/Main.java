package main;

import gamefield.GameField;
import gamefield.SimpleGameContextImpl;
import geometry.ConvexPolygon;

public class Main
{

    public static void main(String args[]) {
        SimpleGameContextImpl contextImp = new SimpleGameContextImpl();
        GameField gameField = new GameField(contextImp);
        gameField.addGameObject(new ConvexPolygon(50));
        Thread renderThread = new Thread(gameField);
        renderThread.start();
    }
}