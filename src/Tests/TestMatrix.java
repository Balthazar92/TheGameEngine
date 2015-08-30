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
        System.out.println(m1);

        System.out.println("\nMatrix m2");
        System.out.println(m2);

        System.out.println("\nMatrix m1 * m2");
        Matrix multiple = Matrix.multipl(m1, m2);
        System.out.println(multiple);

        m1.transpose();
        System.out.println("\nMatrix m1 transpose");
        System.out.println(m1);

        Matrix c = Matrix.getLinearCombination(m1, m2, 2.0f, 1.0f);
        System.out.println("\nMatrix c and m1");
        System.out.println(c);
        System.out.print("\n");
        System.out.println(m1);

        System.out.println("\nMatrix m1 linear");
        m1.getLinearCombination(m2, 2.0f, 1.0f);
        System.out.println(m1);

        Matrix identity = Matrix.getIdentityMatrix(2);
        System.out.println("\nMatrix identity");
        System.out.println(identity);

        System.out.println("\nMatrix rotate 30");
        Matrix rotate = Matrix.getRotateMatrix(30.0f);
        System.out.println(rotate);

        System.out.println("\nMatrix rotate 90 multiple 2");
        Matrix mult_rotate = Matrix.multipl(rotate, 2.0f);
        System.out.println(mult_rotate);

        System.out.println("\nMatrix rotate 90 too multiple 3");
        mult_rotate.multipl(3.0f);
        System.out.println(mult_rotate);

        Matrix vector1 = new Matrix(2, 1);
        Matrix vector2 = new Matrix(1, 2);
        vector1.set(0, 0, 2.0f);
        vector1.set(1, 0, 1.0f);
        vector2.set(0, 0, 1.5f);
        vector2.set(0, 1, 4.0f);

        System.out.println("\nVector 1");
        System.out.println(vector1);
        System.out.println("\nVector2");
        System.out.println(vector2);
        System.out.println("\nScalar product");
        System.out.println(Matrix.scalarProduct(vector1, vector2));
    }
}
