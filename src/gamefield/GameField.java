package gamefield;

import java.util.ArrayList;

public class GameField {
    private ArrayList<Drawable> objectsToDraw;

    DrawContext drawContext;

    GameField(DrawContext drawContext) {
        this.drawContext = drawContext;
    }

    public void render() {
        beforeRender();
        for (Drawable objectToDraw : objectsToDraw) {
            objectToDraw.draw(drawContext);
        }
        afterRender();
    }

    private void afterRender() {
    }

    private void beforeRender() {

    }
}
