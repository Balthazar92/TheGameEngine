package myutil;

class MatrixException extends RuntimeException {

    public MatrixException() {}
    public MatrixException(String err) {
        super(err);
    }
}

public class Matrix {
    private float [][] mass;
    private int w, h;
    private boolean transposed;

    public Matrix(int w, int h) {
        this.w = w;
        this.h = h;
        transposed = false;
        mass = new float[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                mass[i][j] = 0;
            }
        }
    }

    public void transpose() {
        if (transposed) {
            transposed = false;
        } else {
            transposed = true;
        }
    }

    public int getW() {
        if (transposed) {
            return h;
        } else {
            return w;
        }
    }

    public int getH() {
        if (transposed) {
            return w;
        } else {
            return h;
        }
    }

    public float get(int x, int y) throws MatrixException {
        if (x < 0 || y < 0 || x >= getH() || y >= getW()) {
            throw new MatrixException("Element not found");
        }
        if (transposed) {
            return mass[y][x];
        } else {
            return mass[x][y];
        }
    }

    public void set(int x, int y, float value) throws MatrixException {
        if (x < 0 || y < 0 || x >= getH() || y >= getW()) {
            throw new MatrixException("Wrong index");
        }
        if (transposed) {
            mass[y][x] = value;
        } else {
            mass[x][y] = value;
        }
    }

    public static Matrix getRotateMatrix(float angle) {
        Matrix rotateMatrix = new Matrix(2, 2);
        rotateMatrix.set(0, 0, (float) Math.cos(Math.toRadians(angle)));
        rotateMatrix.set(0, 1, (-1) * (float) Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 0, (float) Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 1, (float) Math.cos(Math.toRadians(angle)));
        return rotateMatrix;
    }

    public static Matrix multipl(Matrix a, Matrix b) throws MatrixException {
        if (a.getW() != b.getH()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        Matrix multMatrix = new Matrix(b.getW(), a.getH());
        for (int i = 0; i < multMatrix.getW(); i++) {
            for (int j = 0; j < multMatrix.getH(); j++) {
                float value = 0.0f;
                for (int k = 0; k < a.getW(); k++) {
                    value += a.get(i, k) * b.get(k, j);
                }
                multMatrix.set(i, j, value);
            }
        }
        return multMatrix;
    }

    public static Matrix getLinearCombination(Matrix a, Matrix b, float c1, float c2) throws MatrixException {
        if (a.getW() != b.getW() || a.getH() != b.getH()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        Matrix lincombMatrix = new Matrix(a.getW(), a.getH());
        for (int i = 0; i < lincombMatrix.getW(); i++) {
            for (int j = 0; j < lincombMatrix.getH(); j++) {
                lincombMatrix.set(i, j, a.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return lincombMatrix;
    }

    public static Matrix getIdentityMatrix(int size) {
        Matrix identityMatrix = new Matrix(size, size);
        for (int i = 0; i < identityMatrix.getW(); i++) {
            for (int j = 0; j < identityMatrix.getH(); j++) {
                if (i == j) {
                    identityMatrix.set(i, j, 1.0f);
                }
            }
        }
        return identityMatrix;
    }
}
