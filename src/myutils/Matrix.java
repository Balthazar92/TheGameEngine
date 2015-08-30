package myutils;

class MatrixException extends RuntimeException {

    public MatrixException() {}
    public MatrixException(String err) {
        super(err);
    }
}

public class Matrix implements Cloneable{
    private float[][] values;
    private int row, col;
    private boolean transposed;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        transposed = false;
        values = new float[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                values[i][j] = 0;
            }
        }
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        Matrix matrix = (Matrix)super.clone();
        matrix.values = values.clone();
        return matrix;
    }

    public void transpose() {
        transposed = !transposed;
    }

    public int getRow() {
        if (transposed) {
            return col;
        } else {
            return row;
        }
    }

    public int getCol() {
        if (transposed) {
            return row;
        } else {
            return col;
        }
    }

    public float get(int x, int y) throws MatrixException {
        if (x < 0 || y < 0 || x >= getRow() || y >= getCol()) {
            throw new MatrixException("Element not found");
        }
        if (transposed) {
            return values[y][x];
        } else {
            return values[x][y];
        }
    }

    public void set(int x, int y, float value) throws MatrixException {
        if (x < 0 || y < 0 || x >= getRow() || y >= getCol()) {
            throw new MatrixException("Wrong index");
        }
        if (transposed) {
            values[y][x] = value;
        } else {
            values[x][y] = value;
        }
    }

    public static Matrix getRotateMatrix(float angle) {
        Matrix rotateMatrix = new Matrix(2, 2);
        rotateMatrix.set(0, 0, (float) Math.cos(Math.toRadians(angle)));
        rotateMatrix.set(0, 1, (float) - Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 0, (float) Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 1, (float) Math.cos(Math.toRadians(angle)));
        return rotateMatrix;
    }

    public static Matrix multipl(Matrix a, Matrix b) throws MatrixException {
        if (a.getCol() != b.getRow()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        Matrix multMatrix = new Matrix(a.getRow(), b.getCol());
        for (int i = 0; i < multMatrix.getRow(); i++) {
            for (int j = 0; j < multMatrix.getCol(); j++) {
                float value = 0.0f;
                for (int k = 0; k < a.getCol(); k++) {
                    value += a.get(i, k) * b.get(k, j);
                }
                multMatrix.set(i, j, value);
            }
        }
        return multMatrix;
    }

    public static Matrix multipl(Matrix a, float c) {
        Matrix multMatrix = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < multMatrix.getRow(); i++) {
            for (int j = 0; j < multMatrix.getCol(); j++) {
                multMatrix.set(i, j, a.get(i, j) * c);
            }
        }
        return multMatrix;
    }

    public Matrix multipl(float c) {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                this.set(i, j, this.get(i, j) * c);
            }
        }
        return this;
    }

    public static Matrix getLinearCombination(Matrix a, Matrix b, float c1, float c2) throws MatrixException {
        if (a.getRow() != b.getRow() || a.getCol() != b.getCol()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        Matrix linCombMatrix = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < linCombMatrix.getRow(); i++) {
            for (int j = 0; j < linCombMatrix.getCol(); j++) {
                linCombMatrix.set(i, j, a.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return linCombMatrix;
    }

    public Matrix getLinearCombination(Matrix b, float c1, float c2) throws MatrixException {
        if (this.getRow() != b.getRow() || this.getCol() != b.getCol()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                this.set(i, j, this.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return this;
    }

    public float get(int index) {
        if (!isVector()) {
            throw new MatrixException("It isn't a vector");
        } else if (Math.max(col, row) <= index) {
            throw new MatrixException("Wrong index");
        }

        if (row > 1) {
            return values[index][0];
        } else {
            return values[0][index];
        }
    }

    public boolean isVector() {
        if (getRow() == 1 || getCol() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int maxOfSizes() {
        return Math.max(col, row);
    }

    public static float getScalarProduct(Matrix a, Matrix b) throws MatrixException {
        if(!a.isVector() || !b.isVector()) {
            throw new MatrixException("One of participants isn't a vector");
        } else if (a.maxOfSizes() != b.maxOfSizes()) {
            throw new MatrixException("Incorrect vector sizes");
        }

        float scalarProduct = 0f;
        for (int i = 0; i < a.maxOfSizes(); i++) {
            scalarProduct += a.get(i) * b.get(i);
        }

        return scalarProduct;
    }

    public static Matrix getIdentityMatrix(int size) {
        Matrix identityMatrix = new Matrix(size, size);
        for (int i = 0; i < identityMatrix.getRow(); i++) {
            for (int j = 0; j < identityMatrix.getCol(); j++) {
                if (i == j) {
                    identityMatrix.set(i, j, 1.0f);
                }
            }
        }
        return identityMatrix;
    }

    @Override
    public String toString() {
        String matrix_out = "";
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                matrix_out += this.get(i, j) + " ";
            }
            matrix_out += "\n";
        }
        return matrix_out;
    }
}
