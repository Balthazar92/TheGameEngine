package engine.myutils;

class MatrixException extends RuntimeException {

    public MatrixException() {
    }

    public MatrixException(String err) {
        super(err);
    }
}

public class Matrix implements Cloneable {
    private float[][] values;
    private int row, column;
    private boolean transposed;

    public Matrix(int row, int column) {
        if (row < 1 || column < 1) {
            throw MatrixException("Initial matrix sizes is wrong");
        }

        this.row = row;
        this.column = column;
        transposed = false;
        values = new float[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                values[i][j] = 0;
            }
        }
    }

    public Matrix(Matrix matrix) {
        values = matrix.values.clone();
        row = matrix.row;
        column = matrix.column;
        transposed = matrix.transposed;
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        Matrix matrix = (Matrix) super.clone();
        matrix.values = values.clone();
        return matrix;
    }

    public void transpose() {
        transposed = !transposed;
    }

    public int getRow() {
        if (transposed) {
            return column;
        } else {
            return row;
        }
    }

    public int getColumn() {
        if (transposed) {
            return row;
        } else {
            return column;
        }
    }

    public float get(int x, int y) throws MatrixException {
        if (x < 0 || y < 0 || x >= getRow() || y >= getColumn()) {
            throw new MatrixException("Element not found");
        }
        if (transposed) {
            return values[y][x];
        } else {
            return values[x][y];
        }
    }

    public void set(int x, int y, float value) throws MatrixException {
        if (x < 0 || y < 0 || x >= getRow() || y >= getColumn()) {
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
        rotateMatrix.set(0, 1, (float) -Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 0, (float) Math.sin(Math.toRadians(angle)));
        rotateMatrix.set(1, 1, (float) Math.cos(Math.toRadians(angle)));
        return rotateMatrix;
    }

    public static Matrix multipl(Matrix a, Matrix b) throws MatrixException {
        if (a.getColumn() != b.getRow()) {
            throw new MatrixException("Incorrect matrix sizes");
        }

        Matrix multMatrix = new Matrix(a.getRow(), b.getColumn());
        for (int i = 0; i < multMatrix.getRow(); i++) {
            for (int j = 0; j < multMatrix.getColumn(); j++) {
                float value = 0.0f;
                for (int k = 0; k < a.getColumn(); k++) {
                    value += a.get(i, k) * b.get(k, j);
                }
                multMatrix.set(i, j, value);
            }
        }
        return multMatrix;
    }

    public static Matrix multipl(Matrix a, float c) {
        Matrix multMatrix = new Matrix(a.getRow(), a.getColumn());
        for (int i = 0; i < multMatrix.getRow(); i++) {
            for (int j = 0; j < multMatrix.getColumn(); j++) {
                multMatrix.set(i, j, a.get(i, j) * c);
            }
        }
        return multMatrix;
    }

    public Matrix multipl(float c) {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                this.set(i, j, this.get(i, j) * c);
            }
        }
        return this;
    }

    public static Matrix getLinearCombination(Matrix a, Matrix b, float c1, float c2) throws MatrixException {
        if (a.getRow() != b.getRow() || a.getColumn() != b.getColumn()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        Matrix linCombMatrix = new Matrix(a.getRow(), a.getColumn());
        for (int i = 0; i < linCombMatrix.getRow(); i++) {
            for (int j = 0; j < linCombMatrix.getColumn(); j++) {
                linCombMatrix.set(i, j, a.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return linCombMatrix;
    }

    public Matrix applyLinearCombination(Matrix b, float c1, float c2) throws MatrixException {
        if (this.getRow() != b.getRow() || this.getColumn() != b.getColumn()) {
            throw new MatrixException("Incorrect matrix sizes");
        }
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                this.set(i, j, this.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return this;
    }

    public float get(int index) {
        if (!isVector()) {
            throw new MatrixException("It isn't a vector");
        } else if (Math.max(column, row) <= index) {
            throw new MatrixException("Wrong index");
        }

        if (row >= column) {
            return values[index][0];
        } else {
            return values[0][index];
        }
    }

    public void set(int index, float value) {
        if (!isVector()) {
            throw new MatrixException("It isn't a vector");
        } else if (Math.max(column, row) <= index) {
            throw new MatrixException("Wrong index");
        }

        if (row >= column) {
            values[index][0] = value;
        } else {
            values[0][index] = value;
        }
    }

    private boolean isVector() {
        if (getRow() == 1 || getColumn() == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCoords() {
        if (isVector() && maxOfSizes() == 2) {
            return true;
        }
        return false;
    }

    public void setCoords(float coord1, float coord2) {
        if (!isCoords()) {
            throw new MatrixException("It isn't coordinates");
        }
        set(0, coord1);
        set(1, coord2);
    }

    public static Matrix createCoords(float coord1, float coord2) {
        Matrix coords = new Matrix(2, 1);
        coords.setCoords(coord1, coord2);
        return coords;
    }

    public int maxOfSizes() {
        return Math.max(column, row);
    }

    public static float getScalarProduct(Matrix a, Matrix b) throws MatrixException {
        if (!a.isVector() || !b.isVector()) {
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
            for (int j = 0; j < identityMatrix.getColumn(); j++) {
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
            for (int j = 0; j < this.getColumn(); j++) {
                matrix_out += this.get(i, j) + " ";
            }
            matrix_out += "\n";
        }
        return matrix_out;
    }
}
