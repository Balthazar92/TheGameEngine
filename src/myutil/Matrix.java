package myutil;

class MatrixException extends RuntimeException {

    public MatrixException() {}
    public MatrixException(String err) {
        super(err);
    }
}

public class Matrix implements Cloneable{
    private float [][] mass;
    private int row, col;
    private boolean transposed;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        transposed = false;
        mass = new float[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mass[i][j] = 0;
            }
        }
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        return (Matrix)super.clone();
    }

    public void transpose() {
        if (transposed) {
            transposed = false;
        } else {
            transposed = true;
        }
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
            return mass[y][x];
        } else {
            return mass[x][y];
        }
    }

    public void set(int x, int y, float value) throws MatrixException {
        if (x < 0 || y < 0 || x >= getRow() || y >= getCol()) {
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
        Matrix lincombMatrix = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < lincombMatrix.getRow(); i++) {
            for (int j = 0; j < lincombMatrix.getCol(); j++) {
                lincombMatrix.set(i, j, a.get(i, j) * c1 + b.get(i, j) * c2);
            }
        }
        return lincombMatrix;
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

    public static float scalarProduct(Matrix a, Matrix b) throws MatrixException {
        if((a.getRow() > 1 && a.getCol() > 1) || (b.getRow() > 1 && b.getCol() > 1)) {
            throw new MatrixException("Incorrect sizes");
        }
        try {
            Matrix vector1 = a.clone();
            Matrix vector2 = b.clone();
            if (a.getCol() == 1) {
                vector1.transpose();
            }
            if (b.getRow() == 1) {
                vector2.transpose();
            }
            return Matrix.multipl(vector1, vector2).get(0, 0);
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
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
