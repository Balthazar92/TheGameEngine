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
        Matrix joinCoordsInParent = Matrix.createCoords(0.0f, 0.0f);
        float r = polarCoordsOfParentJoinPoint.getValue(0);
        float phi = polarCoordsOfParentJoinPoint.getValue(1);
        joinCoordsInParent.setCoords(r * (float) Math.cos(phi), r * (float) Math.sin(phi));
        ArrayList<Matrix> centerChildCoordsInParent = new ArrayList<Matrix>();
        int i = 0;
        for (Matrix childCoords : polarCoordsOfChildJoinPoints) {
            Matrix centerChildCoords = Matrix.createCoords(0.0f, 0.0f);
            float rChild = childCoords.getValue(0);
            float phiChild = childCoords.getValue(1);
            float xChild = rChild * (float) Math.cos(Math.PI - anglesBetweenParentAndChild.get(i));
            float yChild = rChild * (float) Math.sin(Math.PI - anglesBetweenParentAndChild.get(i));
            centerChildCoords.setCoords(xChild, yChild);
            centerChildCoordsInParent.add(Matrix.mul(Matrix.getRotateMatrix(phi), centerChildCoords).applyLinearCombination(joinCoordsInParent, 1.0f, 1.0f));
            i++;
        }
    }
}
