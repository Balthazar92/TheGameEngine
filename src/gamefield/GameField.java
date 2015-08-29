package gamefield;

import java.util.ArrayList;

public class GameField implements Runnable {
    private ArrayList<Drawable> objectsToDraw = new ArrayList<Drawable>();

    DrawContext drawContext = null;

    public GameField(DrawContext drawContext) {
        this.drawContext = drawContext;
    }

    public void addGameObject(Drawable gameObject) {
        objectsToDraw.add(gameObject);
    }

    public void render() {
        drawContext.startRendering();
        for (Drawable objectToDraw : objectsToDraw) {
            objectToDraw.draw(drawContext);
        }
        drawContext.endRendering();
    }

    @Override
    public void run() {
        while (true) {
            render();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
