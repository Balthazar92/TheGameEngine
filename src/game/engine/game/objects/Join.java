package game.engine.game.objects;

public class Join extends GameObject {
    private GameObject parent = null;
    private GameObject child = null;

    Join(GameObject parent, GameObject child) {
        this.parent = parent;
        this.child = child;
    }
}
