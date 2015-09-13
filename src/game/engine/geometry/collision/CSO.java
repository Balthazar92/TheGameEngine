package game.engine.geometry.collision;

import game.engine.geometry.figures.ConvexPolygon;
import game.engine.myutils.Matrix;
import game.engine.myutils.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CSO {
    public CSO(ConvexPolygon p1, ConvexPolygon p2) {
        Map<Angle, CSOEdge> edges = new TreeMap<Angle, CSOEdge>();
        ConvexPolygon[] ps = {p1, p2};
        for (int polygonNumber = 0; polygonNumber < ps.length; polygonNumber++) {
            for (int vertexNumber = 0; vertexNumber < ps[polygonNumber].getVerticesCount(); vertexNumber++) {
                int firstVertex = vertexNumber;
                int secondVertex = firstVertex + 1;
                if (secondVertex == ps[polygonNumber].getVerticesCount()) {
                    secondVertex = 0;
                }
                Matrix vectorCoords = ps[polygonNumber].getCoords(secondVertex)
                        .applyLinearCombination(ps[polygonNumber].getCoords(firstVertex), 1, -1);
                Angle angle = new Angle(vectorCoords);
                CSOEdge csoEdge = edges.get(angle);
                if (csoEdge == null) {
                    edges.put(angle, new CSOEdge(vectorCoords, polygonNumber, vertexNumber));
                } else {
                    csoEdge.addEdge(vectorCoords, polygonNumber, vertexNumber);
                }
            }
        }
    }

    private static class Angle implements Comparable<Angle> {
        private float value;

        public Angle(Matrix matrix) {
            value = (float) Math.atan2(matrix.getValue(1), matrix.getValue(0));
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof Angle)) {
                return false;
            }

            Angle angle = (Angle) object;
            float diff = angle.value - value;
            if (Math.abs(diff) < 0.01) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Angle angle) {
            float diff = angle.value - value;
            if (Math.abs(diff) < 0.01) {
                return 0;
            } else if (diff < 0) {
                return -1;
            }
            return 1;
        }
    }

    private static class CSOEdge {
        private Matrix vectorCoords = Matrix.createCoords(0, 0);
        List<Pair<Integer, Integer>> edges = new LinkedList<Pair<Integer, Integer>>();

        public CSOEdge(Matrix vectorCoords, int polygonNumber, int vertexNumber) {
            addEdge(vectorCoords, polygonNumber, vertexNumber);
        }

        public void addEdge(Matrix vectorCoords, int polygonNumber, int vertexNumber) {
            this.vectorCoords.applyLinearCombination(vectorCoords, 1, 1);
            edges.add(new Pair<Integer, Integer>(polygonNumber, vertexNumber));
        }

        public Matrix getVectorCoords() {
            return vectorCoords;
        }
    }
}
