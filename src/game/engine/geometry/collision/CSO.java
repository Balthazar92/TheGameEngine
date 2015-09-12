package game.engine.geometry.collision;

import game.engine.geometry.figures.ConvexPolygon;
import game.engine.myutils.Matrix;

import java.util.Set;

class CSO extends ConvexPolygon {
    CSO(ConvexPolygon p1, ConvexPolygon p2) {
        Set<CSOEdge> edges;
        ConvexPolygon[] ps = {p1, p2};
        for (int polygonNumber = 0; polygonNumber < ps.length; polygonNumber++) {
            for (int vertexNumber = 0; vertexNumber < p1.getVerticesCount(); vertexNumber++) {

            }
        }
    }

    private static class CSOEdge implements Comparable<CSOEdge> {
        Matrix vectorCoords;
        int polygonNumber;
        int vertexNumber;
        CSOEdge(float a, float b, int polygonNumber, int vertexNumber) {
            vectorCoords = Matrix.createCoords(a, b);
            this.polygonNumber = polygonNumber;
            this.vertexNumber = vertexNumber;
        }

        public Matrix getVectorCoords() {
            return vectorCoords;
        }

        public int getPolygonNumber() {
            return polygonNumber;
        }

        public int getVertexNumber() {
            return vertexNumber;
        }

        @Override
        public int compareTo(CSOEdge csoEdge) {
            return 0;
        }
    }
}
