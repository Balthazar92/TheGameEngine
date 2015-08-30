package Tests;

import myutil.Matrix;

public class TestMatrix {
    public void test(){
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);

        m1.set(0, 0, 2.0f);
        m1.set(0, 1, 1.0f);
        m1.set(1, 0, 1.5f);
        m1.set(1, 1, 2.5f);

        m2.set(0, 0, 1.0f);
        m2.set(0, 1, 1.0f);
        m2.set(1, 0, 0.5f);
        m2.set(1, 1, 3.5f);

        System.out.println("Matrix m1\n");
        m1.printMatrix();
        m1.transpose();
        System.out.println("\nMatrix m1 transpose\n");
        m1.printMatrix();

        Matrix c = Matrix.getLinearCombination(m1, m2, 2.0f, 1.0f);
        System.out.println("Matrix c and m1\n");
        c.printMatrix();
        System.out.print("\n");
        m1.printMatrix();

        System.out.println("Matrix m1 linear\n");
        m1.getLinearCombination(m2, 2.0f, 1.0f);
        m1.printMatrix();

        Matrix identity = Matrix.getIdentityMatrix(2);
        System.out.println("\nMatrix identity\n");
        identity.printMatrix();
    }
}
