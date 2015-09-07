package game.engine.game.objects;

import game.engine.myutils.Matrix;
import java.util.ArrayList;

public class HardJoin extends GameObject {
    private Matrix polarCoordsOfParentJoinPoint;
    private ArrayList<Matrix> polarCoordsOfChildJoinPoints;
    private ArrayList<Float> anglesBetweenParentAndChild;

    HardJoin(GameObject parent) {
        this.parent = parent;
    }

    @Override
    protected void updateThisOne() {

    }
}
