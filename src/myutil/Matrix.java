package myutil;

class MatrixError extends Exception{

    public MatrixError(){}
    public MatrixError(String err){
        super(err);
    }
}

public class Matrix {
    private float [][] mass;
    private int w, h;
    private boolean transposed;

    public Matrix(int w, int h){
        this.w = w;
        this.h = h;
        transposed = false;
        mass = new float[w][h];
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                mass[i][j] = 0;
    }

    public void transpose(){
        if (transposed) transposed = false;
        else transposed = true;
    }

    public int getW(){
        if (transposed) return h;
        else return w;
    }

    public int getH(){
        if (transposed) return w;
        else return h;
    }

    public float get(int x, int y) throws MatrixError{
        if (transposed) {
            if (x < 0 || y < 0 || x >= h || y >= w)
                throw new MatrixError("Element not found");
            return mass[y][x];
        }
        else {
            if (x < 0 || y < 0 || x >= w || y >= h)
                throw new MatrixError("Element not found");
            else return mass[x][y];
        }
    }

    public void set(int x, int y, float value) throws MatrixError{
        if (transposed) {
            if (x < 0 || y < 0 || x >= h || y >= w)
                throw new MatrixError("Wrong index");
            mass[y][x] = value;
        }
        else {
            if (x < 0 || y < 0 || x >= w || y >= h)
                throw new MatrixError("Wring index");
            mass[x][y] = value;
        }
    }

    public static Matrix getRotateMatrix(float angle){
        Matrix rotate = new Matrix(2, 2);
        try {
            rotate.set(0, 0, (float) Math.cos(Math.toRadians(angle)));
            rotate.set(0, 1, (-1) * (float) Math.sin(Math.toRadians(angle)));
            rotate.set(1, 0, (float) Math.sin(Math.toRadians(angle)));
            rotate.set(1, 1, (float) Math.cos(Math.toRadians(angle)));
        }
        catch(MatrixError e){
            e.printStackTrace();
        }
        return rotate;
    }

}
