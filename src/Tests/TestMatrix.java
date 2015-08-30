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

        System.out.println("Matrix m1");
        m1.printMatrix();

        System.out.println("\nMatrix m2");
        m2.printMatrix();

        System.out.println("\nMatrix m1 * m2");
        Matrix multiple = Matrix.multipl(m1, m2);
        multiple.printMatrix();

        m1.transpose();
        System.out.println("\nMatrix m1 transpose");
        m1.printMatrix();

        Matrix c = Matrix.getLinearCombination(m1, m2, 2.0f, 1.0f);
        System.out.println("\nMatrix c and m1");
        c.printMatrix();
        System.out.print("\n");
        m1.printMatrix();

        System.out.println("\nMatrix m1 linear");
        m1.getLinearCombination(m2, 2.0f, 1.0f);
        m1.printMatrix();

        Matrix identity = Matrix.getIdentityMatrix(2);
        System.out.println("\nMatrix identity");
        identity.printMatrix();

        System.out.println("\nMatrix rotate 90");
        Matrix rotate = Matrix.getRotateMatrix(30.0f);
        rotate.printMatrix();

        System.out.println("\nMatrix rotate 90 multiple 2");
        Matrix mult_rotate = Matrix.multipl(rotate, 2.0f);
        mult_rotate.printMatrix();

        System.out.println("\nMatrix rotate 90 too multiple 3");
        mult_rotate.multipl(3.0f);
        mult_rotate.printMatrix();
    }
}
