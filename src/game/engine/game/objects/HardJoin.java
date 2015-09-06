package game.engine.game.objects;

import game.engine.myutils.Matrix;
import java.util.ArrayList;

public class HardJoin extends GameObject {

    private Matrix polarCoordsOfParentJoinPoint;
    private ArrayList<Matrix> polarCoordsOfChildJoinPoint;
    private ArrayList<Float> anglesBetweenParentAndChild;

    HardJoin(GameObject parent) {
        this.parent = parent;
    }
}
